import mysql.connector
import os, glob,csv
import os.path
import json, requests ,configparser



mysql_con = None
config = configparser.ConfigParser()
config.read('secret.ini')
secret_key = config['DEFAULT']['kakao_key']

def getAddressInfo(address, place_name):
    base_url = "https://dapi.kakao.com/v2/local/search/keyword.json?query="
    # address_anme : 강동구, place_name : 홈마트
    headers = {'Content-Type': 'application/json; charset=utf-8', 'Authorization': "KakaoAK " + secret_key}
    response = requests.get(base_url + address + " " + place_name, headers=headers) 
    result = {"x": 0, "y": 0}
    rsp = json.loads(response.text)
    if response.status_code == 200 and rsp["meta"]["total_count"] != 0:
        result["result"] = True
        result["address_name"] = rsp["documents"][0]["address_name"]
        result["road_address_name"] = rsp["documents"][0]["road_address_name"]
        result['x'] = rsp["documents"][0]["x"]
        result['y'] = rsp["documents"][0]["y"]
    else:
        result["result"] = False
        result["response"] = rsp
    
    return result

# def query_executor(cursor, param1, param2):
#     sql = "select * from food where name = %s or name = %s ;"
#     cursor.execute(sql, (param1, param2))

def getFiles(targetdir):
    files = os.listdir(targetdir)
    return files

if __name__ == "__main__":


    try:
        files = getFiles("./csv/")
        mysql_con = mysql.connector.connect(host='localhost', port='3306', database='walker_db', user='master', password='qwe123')
        mysql_cursor = mysql_con.cursor(dictionary=True)
        sql = """insert into tbl_accident(place_name,address_name,road_address_name,x,y,accident_cnt,dead_cnt,year)
                 values (%s, %s, %s, %s, %s, %s, %s, %s)"""
        print("connection success")
        
        for file in files:
            print(file)
            year, city = file.split("_")
            cities = {"gangnam":"강남구","seocho":"서초구","seongpa":"송파구","gangdong":"강동구"}
            city = cities[city[:-4]]
            
            f = open('./csv/'+file,'r',encoding='utf-8')
            rdr = csv.reader(f)
            for line in rdr:
                place_name,accident_cnt,dead_cnt = line[0],line[1],line[2]
                address_info = getAddressInfo(city,place_name)
                if address_info['result'] == False:
                    continue
                y,x,address_name,road_address_name = address_info['y'], address_info['x'], address_info['address_name'] , address_info['road_address_name']
                
                mysql_cursor.execute(sql,(place_name,address_name,road_address_name,x,y,accident_cnt,dead_cnt,year))
            f.close()
        # query_executor(mysql_cursor, 'sushi', 'mcdonalds')

#         for row in mysql_cursor:
#             print('price is: '+str(row['price']))

        # mysql_cursor.close()
        mysql_con.commit()



    except Exception as e:
        print(e.message)


    finally:
        if mysql_con is not None:
            mysql_con.close()
            
