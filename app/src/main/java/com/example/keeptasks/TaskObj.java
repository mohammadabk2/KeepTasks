package com.example.keeptasks;

public class TaskObj {

    private int id;
    private String title, date, time, note;
    private boolean Urgent, DayBefore;

    public TaskObj(int id, String title, String date, Boolean Urgent, Boolean DayBefore, String note, String time) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.time = time;
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

    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        String fin = this.title + "   ";
        if (this.Urgent) {
            fin += "!!!Urgent!!!";
        }
        fin += "\n" + this.date + " " + this.time;
        if (this.DayBefore) {
            fin += "\n" + " Will remind you the day before ";
        }
        fin += "\n" + this.note;
        // fin += "\n" + "\n" + this.id;
        return fin;
    }
}