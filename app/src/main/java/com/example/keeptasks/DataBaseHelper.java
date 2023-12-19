package com.example.keeptasks;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import com.example.keeptasks.TaskObj;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    // main table
    public static final String table_name = "Tasks";
    public static final String COLUMN_id = "Id";
    public static final String COLUMN_title = "Title";
    public static final String COLUMN_urgent = "Urgent";
    public static final String COLUMN_date = "Date";
    public static final String COLUMN_dayBefore = "DayBefore";
    public static final String COLUMN_note = "Note";
    // history table
    public static final String table_history_name = "History";
    public static final String COLUMN_History_id = "Id";

    public DataBaseHelper(Context context) {
        super(context, "TasksDataBase.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_First_Table = "CREATE TABLE IF NOT EXISTS " + table_name
                + " (" + COLUMN_id + " INTEGER  PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_title + " TEXT, " +
                COLUMN_urgent + " TEXT," +
                COLUMN_date + " TEXT, "
                + COLUMN_dayBefore + " TEXT, "
                + COLUMN_note + " TEXT )";
        String create_Second_Table = "CREATE TABLE IF NOT EXISTS " + table_history_name
                + " (" + COLUMN_History_id + " INTEGER  PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_title + " TEXT, " +
                COLUMN_urgent + " TEXT," +
                COLUMN_date + " TEXT, "
                + COLUMN_dayBefore + " TEXT, "
                + COLUMN_note + " TEXT )";
        db.execSQL(create_First_Table);
        db.execSQL(create_Second_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(TaskObj task, String table) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_title, task.getTitle());
        cv.put(COLUMN_urgent, task.getUrgent());
        cv.put(COLUMN_date, task.getDate());
        cv.put(COLUMN_dayBefore, task.getBefore());
        cv.put(COLUMN_note, task.getNote());

        db.insert(table, null, cv);
        db.close();
        return true;
    }

    public List<TaskObj> getEverything(String table_name) {
        List<com.example.keeptasks.TaskObj> list = new ArrayList<>();
        // get data from database
        String read_Table = "SELECT * FROM " + table_name;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(read_Table, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                boolean urgent = cursor.getInt(2) == 1 ? true : false;
                String date = cursor.getString(3);
                boolean daybefore = cursor.getInt(4) == 1 ? true : false;
                String note = cursor.getString(5);
                TaskObj task = new TaskObj(id, title, date, urgent, daybefore, note);
                list.add(task);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    public boolean delete_Task(TaskObj task) {
        // add the task to history along side status
        addOne(task, table_history_name);
        // if found in database it will be deleted
        SQLiteDatabase db = this.getWritableDatabase();
        String delete_Task = "DELETE FROM " + table_name + " WHERE " + COLUMN_id + " = " + task.getId();
        Cursor cursor = db.rawQuery(delete_Task, null);
        if (cursor.moveToFirst()) {
            return true;
        }
        return false;
    }

    public boolean clear_History() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + table_history_name);
        return true;
    }
}