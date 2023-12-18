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

    private int id;
    private String title, Note;
    private boolean Urgent, DayBefore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskshow);

        // Intent
        Intent intentMain = new Intent(getApplicationContext(), MainActivity.class);
        // Screen Objects
        Button btnEdit = (Button) findViewById(R.id.btnexitsetting);
        Button btnclose = (Button) findViewById(R.id.btnclose);
        Button btndelete = (Button) findViewById(R.id.btndelete);
        TextView vTitle = (TextView) findViewById(R.id.viewtitle);
        TextView vurgent = (TextView) findViewById(R.id.viewurgent);
        TextView vDate = (TextView) findViewById(R.id.viewdate);
        TextView vNote = (TextView) findViewById(R.id.viewnote);
        // close button
        android.view.View.OnClickListener closelistener = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO: create alarm and set in storage
                Log.d("BUTTONS", "User tapped the CLose button");
                finish();
                startActivity(intentMain);
            }
        };
        btnclose.setOnClickListener(closelistener);

        // vTitle.setText(this.title);
        // vNote.setText(this.Note);
        // TODO: later add edit and delete functions
    }

}