package com.example.keeptasks;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.util.Log;
import android.content.Intent;
import android.content.Context;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class CreateTask extends AppCompatActivity {
    private int count1 = 0;
    private int count2 = 0;
    private static final int CREATE_CODE = 40;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createtask);

        // Intents and Activities
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        // Screen Objects
        Button btndn = (Button) findViewById(R.id.btndone);
        EditText txtName = (EditText) findViewById(R.id.taskfiled);
        Switch urgent = (Switch) findViewById(R.id.switchurgent);
        EditText txtNote = (EditText) findViewById(R.id.notefiled);
        Switch dayBefore = (Switch) findViewById(R.id.switchremind);
        EditText txtDate = (EditText) findViewById(R.id.dateinput);

        // Listeners
        android.view.View.OnClickListener donelistener = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO: create Task and save it
                Log.d("BUTTONS", "User tapped the Finish button");
                String Name = txtName.getText().toString();
                String Note = txtNote.getText().toString();
                String Date = txtDate.getText().toString();
                Boolean DateBefroe = dayBefore.isChecked();
                Boolean DateisRight = true;// change later

                if (Name.matches("")) {// if Title is empty
                    Toast.makeText(getApplicationContext(), "Name is empty", Toast.LENGTH_SHORT).show();
                }

                if (urgent.isChecked() && count1 == 0) {// for Urgent checkbox
                    count1++;
                    Name = "!!! " + Name + " !!!";// remove this and add it to read if urgent is checked
                }

                // TODO: Date check if valid if empty and add it as an alarm
                if (DateBefroe && count2 == 0) {// for dayBefore checkbox
                    count2++;
                    FileMaker daybeforere = new FileMaker(Name + "Day Before Reminder", urgent.isChecked(), false,
                            Note);// create new file
                    // create another notifaction the day before
                }

                if (!Name.matches("") && DateisRight) {
                    FileMaker daybeforere = new FileMaker(Name, urgent.isChecked(), DateBefroe, Note);
                    startActivity(intent);
                }
            }
        };
        btndn.setOnClickListener(donelistener);
    }
}
