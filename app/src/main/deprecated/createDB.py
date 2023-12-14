import sqlite3

#connection
connection =sqlite3.connect("database.db") #if database doesnt exist a new one will be created

# cursor
cursor=connection.cursor()

#create table fill this in with what the tables need
command1 ="""CREATE TABLE IF NOT EXISTS 
tasks(title TEXT  PRIMARY KEY , urgent TEXT,date TEXT, remind_before TEXT, note TEXT )"""
cursor.execute(command1)
connection.commit()