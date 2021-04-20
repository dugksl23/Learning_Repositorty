# -*- coding: utf-8 -*-

# import mysql.connector as mariadb
import pymysql

# 전역변수 선언부
conn = None

# 메인코드
conn = pymysql.connect(host="localhost", port=3306, user="root", password="1234",
                       charset="utf8", db="scrapy_db")  # 접속정보a
try:
    with conn.cursor() as cursor:  # cursor 생성

        sql = '''SELECT * FROM user'''  # 실행할 sql문
        cursor.execute(sql)  # sql문 실행

        result = cursor.fetchall()
        print(result)
    conn.commit()
    print("총 ow", cursor.rownumber)


    with conn.cursor() as cursor:
        sql = '''SELECT * FROM user WHERE id= %s'''
        cursor.execute(sql, ('test',))

        result = cursor.fetchone()
        print(result)
    conn.commit()

finally:

    conn.close()
