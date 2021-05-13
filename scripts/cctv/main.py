import mysql.connector
import csv


mydb = mysql.connector.connect(host='localhost', port='3306', database='walker_db', user='master', password='qwe123')
mycursor = mydb.cursor()

csv_list = "./csv/songpa_cctv.csv ./csv/seocho_cctv.csv ./csv/gangnam_cctv.csv".split()

for csv_file_name in csv_list:
    with open(csv_file_name, 'r', encoding='utf-8-sig') as f: 
        reader = csv.reader(f, delimiter=",") 

        for row in reader:
            
            # address_name, road_address_name, storage_period, y, x
            sql = "INSERT INTO tbl_cctv (address_name, road_address_name, storage_period, y, x) VALUES (%s, %s, %s, %s, %s)"
            print(row)
            val = (row[0], row[1], int(row[2]), row[3], row[4])
            mycursor.execute(sql, val)

        mydb.commit()


    print(mycursor.rowcount, "record inserted.")

