package com.example.keeptasks;

import android.content.Context;

import com.example.keeptasks.TaskObj;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String table_name = "Tasks";
    public static final String COLUMN_id = "Id";
    public static final String COLUMN_title = "Title";
    public static final String COLUMN_urgent = "Urgent";
    public static final String COLUMN_date = "Date";
    public static final String COLUMN_dayBefore = "DayBefore";
    public static final String COLUMN_note = "Note";

    public DataBaseHelper(Context context) {
        super(context, "TasksDataBase.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_Table = "CREATE TABLE IF NOT EXISTS " + table_name
                + " (" + COLUMN_id + " INTEGER  PRIMARY KEY AUTOINCREMENT, " + COLUMN_title
                + " TEXT, " + COLUMN_urgent + " TEXT," + COLUMN_date + " TEXT, " + COLUMN_dayBefore
                + " TEXT, " + COLUMN_note + " TEXT )";
        db.execSQL(create_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(TaskObj task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_title, task.title);
        cv.put(COLUMN_urgent, task.Urgent);
        cv.put(COLUMN_date, task.date);
        cv.put(COLUMN_dayBefore, task.DayBefore);
        cv.put(COLUMN_note, task.note);

        db.insert(table_name, null, cv);
        db.close();
        return true;
    }

    public List<TaskObj> getEverything() {
        List<com.example.keeptasks.TaskObj> list = new ArrayList<>();
        // get data from database
        String read_Table = "SELECT * FROM " + table_name ; // add where id =
        SQLiteDatabase db = this.getReadableDatabase();
        if (db.rawQuery(read_Table,null).moveToFirst()) {
            do {
                int id = db.rawQuery(read_Table,null).getInt(0);
                String title = db.rawQuery(read_Table,null).getString(1);
                boolean urgent = db.rawQuery(read_Table,null).getInt(2) == 1 ? true : false;
                String date = db.rawQuery(read_Table,null).getString(3);
                boolean daybefore = db.rawQuery(read_Table,null).getInt(4) == 1 ? true : false;
                String note = db.rawQuery(read_Table,null).getString(5);
                TaskObj task = new TaskObj(title, date, urgent, daybefore, note);
                list.add(task);
            } while (db.rawQuery(read_Table,null).moveToFirst());
        }
        db.rawQuery(read_Table,null).close();
        db.close();
        return list;
    }

}