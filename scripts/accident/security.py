import mysql.connector
import os,glob,csv
import os.path

mysql_con = None

if __name__ == "__main__":
    try:
        files = ["gangdong.csv","gangnam.csv","seocho.csv"]
        mysql_con = mysql.connector.connect(host='localhost', port='3306', database='', user='', password='')
        mysql_cursor = mysql_con.cursor(dictionary=True)
        sql = """insert into tbl_lamp(x,y)
                 values (%s, %s)"""
        
        print("connection success")

        for file in files:
            print(file)
            f = open('./csv/'+file,'r',encoding='utf-8')
            rdr = csv.reader(f)
            cnt = False
            for line in rdr:
                if cnt == False:
                    cnt = True
                    continue
                y,x = line[4],line[5]
                if len(y) == 0 or len(x) == 0:
                    continue
                mysql_cursor.execute(sql,(x,y))
            f.close()
        
        mysql_con.commit()
    except Exception as e:
        print(e.message)
    
    finally:
        if mysql_con is not None:
            mysql_con.close()