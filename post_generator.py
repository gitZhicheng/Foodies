#!/usr/bin/env python
import os
import MySQLdb as db
from random import randint
import datetime

conn = db.connect('127.0.0.1','root', 'xuzhicheng','Foodies',use_unicode=True, charset='utf8')
cursor = conn.cursor()

insert_stmt = "INSERT INTO Posts VALUES(%s,%s,%s,%s,%s,%s,%s);"

def insert_posts(year, number):
	for i in range(number+randint(0,900)):
		date = datetime.datetime(year, randint(1,12), randint(1,28))
		date_str = date.strftime('%Y-%m-%d %H:%M:%S')

		cursor.execute(insert_stmt, (0, "tittle", "content", "path", 100, date_str, 100008))
	conn.commit()



insert_posts(2008, 3000)
insert_posts(2009, 4000)
insert_posts(2010, 6000)
insert_posts(2011, 6000)
insert_posts(2012, 9000)
insert_posts(2013, 10000)
insert_posts(2014, 8000)
insert_posts(2015, 9000)
insert_posts(2016, 9000)