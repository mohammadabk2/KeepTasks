import sqlite3
import sys

#connection
connection = sqlite3.connect("database.db")
# cursor
cursor=connection.cursor()
#insert into table
command2 ="SELECT * FROM tasks WHERE title=?"
values=[sys.argv[1]]
cursor.execute(command2,values)
str=[]
for row in cursor:
    str=" ".join(row)
print(str)