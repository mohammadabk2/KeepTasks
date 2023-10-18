package com.example.keeptasks;

import java.io.File;
import java.io.PrintWriter;
//import android.content.Context;

public class FileMaker {
    private File file;
    private String title, Note;
    private Boolean Urgent, DayBefore;
    private final String path="/data/data/com.example.keeptasks/files/";

    public FileMaker(String TaskName, Boolean Urgent, Boolean DayBefore, String Note) {
        this.title = TaskName;
        this.Urgent = Urgent;
        this.DayBefore = DayBefore;
        this.Note = Note;
        this.file = new File(this.path+TaskName + ".txt");
        newTaskFile(TaskName, Urgent, DayBefore, Note);
    }

    private void newTaskFile(String TaskName, Boolean Urgent, Boolean DayBefore, String Note) {
        try {
            if (!this.file.exists()) {
                this.file.createNewFile();
            }
            PrintWriter pw = new PrintWriter(this.file);
            pw.println(TaskName);
            if (this.Urgent) {
                pw.println("Urgent");
            } else {
                pw.println("Normal");
            }

            if (DayBefore) {
                pw.println("Remind Day Before");
            } else {
                pw.println("Dont Remind Day Before");
            }
            pw.println(Note);
            pw.close();
        } catch (Exception e) {

        }
    }

    private void update() {
        deleteFile();
        newTaskFile(this.title, this.Urgent, this.DayBefore, this.Note);
    }

    public void editTitle(String newTitle) {
        this.title = newTitle;
        update();
    }

    public void editUrgent(Boolean newUrgent) {
        this.Urgent = newUrgent;
        update();
    }

    public void editDayBefore(Boolean newDayBefore) {
        this.DayBefore = newDayBefore;
        update();
    }

    public void EditNotes(String newNotes) {
        this.Note = newNotes;
        update();
    }

    public void deleteFile(){
        this.file.delete();
    }
}
