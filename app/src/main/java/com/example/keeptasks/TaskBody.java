package com.example.keeptasks;
//probaibly delete too
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

import android.content.Intent;
import android.widget.Toast;
import android.util.Log;

public class TaskBody extends AppCompatActivity {

    private String title, Urgent, DayBefore, Note;
    private final String path = "/data/data/com.example.keeptasks/files/";
    //ArrayList<String> tempList = MainActivity.getList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskshow);

        try {
            File file = new File(this.path + this.title + ".txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                this.title = sc.nextLine();
                this.Urgent = sc.nextLine();
                this.DayBefore = sc.nextLine();
                this.Note = sc.nextLine();
            }
            sc.close();

        } catch (Exception e) {
            Log.d("BUTTONS", "Exception in FileRead try_Catch");
        }

        // TODO:add method that gets the name of the file or something
        //FileRead fr = new FileRead(null);

        // Intent
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        // Screen Objects
        TextView vTitle = (TextView) findViewById(R.id.viewtitle);
        vTitle.setText(this.title);

        TextView vDate = (TextView) findViewById(R.id.viewtitle);
        TextView vNote = (TextView) findViewById(R.id.viewnote);
        Button btnEdit = (Button) findViewById(R.id.btedit);
        //
        android.view.View.OnClickListener editlistener = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO:add create go to All screen
                Log.d("BUTTONS", "User tapped the Edit button");
                startActivity(intent);
                finish();
            }
        };
        btnEdit.setOnClickListener(editlistener);
        // vTitle.setText(this.title);
        // vNote.setText(this.Note);
        // add date
    }

    // public String getName(){
    //     return this.title;
    // }

    // public String getUrgent(){
    //     return this.Urgent;
    // }

    // public String getDateBefroe(){
    //     return this.DayBefore;
    // }

    // public String getNote(){
    //     return this.Note;
    // }
}

// path it gets saved in /data/data/com.example.keeptasks/files
