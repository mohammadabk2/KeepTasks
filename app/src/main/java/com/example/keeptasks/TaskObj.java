package com.example.keeptasks;

public class TaskObj {

    public String title, date, note;
    public boolean Urgent, DayBefore;

    public TaskObj(String title, String date, Boolean Urgent, Boolean DayBefore, String note) {
        this.title = title;
        this.date = date;
        this.Urgent = Urgent;
        this.DayBefore = DayBefore;
        this.note = note;
    }

    @Override
    public String toString() {
        String fin = "Title: " + this.title + this.date + "\n";
        if (this.Urgent) {
            fin += "!!!Urgent!!!";
        }
        if (this.DayBefore) {
            fin += "\n" + " Will remind you the day before ";
        }
        fin += this.note;
        return fin;
    }
}