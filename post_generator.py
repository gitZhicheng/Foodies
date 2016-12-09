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


def generate_posts():
	insert_posts(2008, 3000)
	insert_posts(2009, 4000)
	insert_posts(2010, 6000)
	insert_posts(2011, 6000)
	insert_posts(2012, 9000)
	insert_posts(2013, 10000)
	insert_posts(2014, 8000)
	insert_posts(2015, 9000)
	insert_posts(2016, 9000)


def update_comment_content():
	select_stmt = "SELECT CommentId FROM Foodies.comments;"
	update_stmt = "UPDATE Foodies.comments SET Content = %s WHERE CommentId = %s"
	comment1 = "I would recommend NOT making alterations to this soup because the purpose of the set recipe is to lose weight. I did lose weight when I made this (it was for a vacation), and it tasted great!! I checked with a local hospital, and they said this recipe is used for overweight patients to get them to lose weight rapidly to prepare for surgery. I would recommend this recipe to anyone!"
	comment2 = "This is a very good for you and tasty soup, however, when I used to make this some time ago, I also thought that it lacked something or needed a little more flavor. To give it the flavor it needs, I decided an old trick my grandmother use to use in her vegetable soup.....add a jar or can of sauerkraut in place of some of the cabbage and it gives it just the zing it needs. Other than that, I left the recipe pretty much as is, I just didn't do a whole lot of measuring, just threw it all together and let it cook. Enjoy!"
	comment3 = "The amount of cabbage in this recipe is overpowering. I like cabbage, but this is too much! If I make this again I will likely only use 1/4 the amount of cabbage and add other healthy veggies like squash and zucchini to make up for it. I will also cut the recipe in half as it does cook entirely too much... The thing that makes this soup a fat burner is you can eat ten bowls in one day and still be below 1000 calories."
	comment4 = "SUPERB beef stew! I took the great base recipe, and tweaked it with a compilation of reviewer recommendations. The end result is a winner, to be sure. Here is what I did (and I won't change a thing the next time I make it): 1. Increase flour to 1/3 cup and substitute seasoned salt for regular salt. Put flour mixture into gallon-sized, zippered bag."
	comment5 = "I have this exact recipe in the recipe booklet that came with my slow cooker! I make it all of the time and my family loves it. I use a packet of dry onion soup mix in place of all the spices, only because it's an easy and convenient way to toss it together."
	comment_list = [comment1,comment2,comment3,comment4,comment5]
	cursor.execute(select_stmt)
	for commendId in cursor:
		comment = comment_list[randint(0,4)]
		cursor.execute(update_stmt, (comment, commendId[0]))

	conn.commit()
update_comment_content()