import sqlite3

#connection
connection =sqlite3.connect("database.db") #if databasename doesnt exist a new one will be created

# cursor
cursor=connection.cursor()

#create table fill this in with what the tables need
command1 ="""CREATE TABLE IF NOT EXISTS 
stores(store_id INTEGER PRIMARY KEY ,location TEXT)"""
cursor.execute(command1)