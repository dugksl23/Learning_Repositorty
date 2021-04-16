# -*- coding: utf-8 -*-

# import mysql.connector as mariadb
import pymysql

# 전역변수 선언부
conn = None

# 메인코드
conn = pymysql.connect(host="localhost", port=3306, user="root", password="1234",
                       charset="utf8")  # 접속정보a
try:
    with conn.cursor() as cursor:
        sql = 'create database scrapy_db'
        cursor.execute(sql)
    conn.commit()
finally:
    conn.close()