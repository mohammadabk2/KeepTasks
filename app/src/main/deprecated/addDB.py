import sqlite3
import sys

#connection
connection =sqlite3.connect("database.db") #if database doesnt exist a new one will be created

# cursor
cursor=connection.cursor()

#insert to table
command2 ="INSERT INTO tasks (title,urgent,date,remind_before,note) VALUES (?,?,?,?,?)"
values= (sys.argv[1],sys.argv[2],sys.argv[3],sys.argv[4],sys.argv[5])
cursor.execute(command2,values)
connection.commit()