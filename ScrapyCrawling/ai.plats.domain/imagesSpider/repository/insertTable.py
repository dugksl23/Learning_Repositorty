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

        sql = '''INSERT INTO user (id, contents) VALUES (%s, %s)'''  # 실행할 sql문
        cursor.execute(sql, ('1111222', 'test1'))  # sql문 실행

        conn.commit()

finally:

    conn.close()
