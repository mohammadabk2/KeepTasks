package com.example.keeptasks;

import java.io.File;
import java.io.FileOutputStream;
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
    public static final String tableName = "Tasks";
    public static final String COLUMNId = "Id";
    public static final String COLUMNTitle = "Title";
    public static final String COLUMNUrgent = "Urgent";
    public static final String COLUMNDate = "Date";
    public static final String COLUMNDayBefore = "DayBefore";
    public static final String COLUMNNote = "Note";
    public static final String COLUMNTime = "Time";
    // history table
    public static final String tableHistoryName = "History";
    public static final String COLUMNHistoryId = "Id";
    // List Table
    public static final String TableListName = "Lists";
    public static final String COLUMNLISTS = "List";
    public static final String COLUMNLISTID = "Id";

    private TaskObj task;

    public DataBaseHelper(Context context) {
        super(context, constants.dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createAllTable = "CREATE TABLE IF NOT EXISTS " + tableName
                + " (" + COLUMNId + " INTEGER  PRIMARY KEY AUTOINCREMENT, " +
                COLUMNTitle + " TEXT, " +
                COLUMNUrgent + " TEXT," +
                COLUMNDate + " TEXT, "
                + COLUMNDayBefore + " TEXT, "
                + COLUMNNote + " TEXT, "
                + COLUMNTime + " TEXT )";
        String createHistoryTable = "CREATE TABLE IF NOT EXISTS " + tableHistoryName
                + " (" + COLUMNHistoryId + " INTEGER  PRIMARY KEY AUTOINCREMENT, " +
                COLUMNTitle + " TEXT, " +
                COLUMNUrgent + " TEXT," +
                COLUMNDate + " TEXT, "
                + COLUMNDayBefore + " TEXT, "
                + COLUMNNote + " TEXT, "
                + COLUMNTime + " TEXT )";
        String createListTable = "CREATE TABLE IF NOT EXISTS " + TableListName
                + " (" + COLUMNLISTID + "  INTEGER  PRIMARY KEY AUTOINCREMENT,  " +
                COLUMNLISTS + " TEXT )";
        db.execSQL(createAllTable);
        db.execSQL(createHistoryTable);
        db.execSQL(createListTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(TaskObj task, String table) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMNTitle, task.getTitle());
        cv.put(COLUMNUrgent, task.getUrgent());
        cv.put(COLUMNDate, task.getDate());
        cv.put(COLUMNDayBefore, task.getBefore());
        cv.put(COLUMNNote, task.getNote());
        cv.put(COLUMNTime, task.getTime());

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
                String time = cursor.getString(6);
                TaskObj task = new TaskObj(id, title, date, urgent, daybefore, note, time);
                list.add(task);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    public List<TaskObj>[] getSorted() {
        List<TaskObj>[] list_final = new List[2];
        List<com.example.keeptasks.TaskObj> list_urgent = new ArrayList<>();
        List<com.example.keeptasks.TaskObj> list_normal = new ArrayList<>();
        List<com.example.keeptasks.TaskObj> list_all = getEverything(tableName);
        int size = list_all.size();
        for (int i = 0; i < size; i++) {
            if (list_all.get(i).getUrgent()) {
                list_urgent.add(list_all.get(i));
            } else {
                list_normal.add(list_all.get(i));
            }
        }
        list_final[0] = list_urgent;
        list_final[1] = list_normal;
        return list_final;
    }

    public boolean completeTask(TaskObj task) {
        // add the task to history along side status
        addOne(task, tableHistoryName);
        // if found in database it will be deleted
        SQLiteDatabase db = this.getWritableDatabase();
        String delete_Task = "DELETE FROM " + tableName + " WHERE " + COLUMNId + " = " + task.getId();
        Cursor cursor = db.rawQuery(delete_Task, null);
        if (cursor.moveToFirst()) {
            return true;
        }
        return false;
    }

    public boolean clearTable(String Table_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + Table_name);
        return true;
    }

    public List<TaskObj> search(String query) {
        List<com.example.keeptasks.TaskObj> list_searched = new ArrayList<>();
        List<com.example.keeptasks.TaskObj> list_all = getEverything(tableName);
        int size = list_all.size();
        for (int i = 0; i < size; i++) { // go over each task and check if it is a match
            if (list_all.get(i).toString().contains(query)) // check the title
                list_searched.add(list_all.get(i));
        }
        return list_searched;
    }

    public boolean addToList(String listToAdd) {
        ArrayList list = getList();
        for(int i =0;i<list.size();i++){
            if(listToAdd.equals(list.get(i).toString())){
                return false;
            }
        }
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMNLISTS, listToAdd);
        db.insert(TableListName, null, cv);
        db.close();
        return true;
    }

    public ArrayList<String> getList() {
        ArrayList listOfLists = new ArrayList<String>();
        String read_Table = "SELECT * FROM " + TableListName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(read_Table, null);
        if (cursor.moveToFirst()) {
            do {
                listOfLists.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listOfLists;
    }

    public boolean removeFromList(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String delete_Task = "DELETE FROM " + TableListName + " WHERE " + COLUMNLISTS + " = " + name;
        Cursor cursor = db.rawQuery(delete_Task, null);
        if (cursor.moveToFirst()) {
            return true;
        }
        return false;
    }
}