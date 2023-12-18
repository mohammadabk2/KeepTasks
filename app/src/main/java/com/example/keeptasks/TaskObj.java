package com.example.keeptasks;

public class TaskObj {

    private int id;
    private String title, date, note;
    private boolean Urgent, DayBefore;

    public TaskObj(int id, String title, String date, Boolean Urgent, Boolean DayBefore, String note) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.Urgent = Urgent;
        this.DayBefore = DayBefore;
        this.note = note;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDate() {
        return this.date;
    }

    public String getNote() {
        return this.note;
    }

    public boolean getUrgent() {
        return this.Urgent;
    }

    public boolean getBefore() {
        return this.DayBefore;
    }

    @Override
    public String toString() {
        String fin = "Title: " + this.title + "   " + this.date + "\n";
        if (this.Urgent) {
            fin += "!!!Urgent!!!";
        }
        if (this.DayBefore) {
            fin += "\n" + " Will remind you the day before ";
        }
        fin += this.note;
        fin += "\n" + this.id;
        return fin;
    }
}