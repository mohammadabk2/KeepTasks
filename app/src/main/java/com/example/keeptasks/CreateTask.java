package com.example.keeptasks;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.util.Log;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;

import java.io.IOException;

import com.example.keeptasks.DataBaseHelper;
import com.example.keeptasks.MainActivity;
import com.example.keeptasks.TaskObj;

public class CreateTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createtask);
        // Intent
        Intent intent = new Intent(getApplicationContext(), MainActivity.class); // return to Home Page
        // On Screen Objects
        Button btndn = (Button) findViewById(R.id.btnalltask); // The Finish button
        Button btncln = (Button) findViewById(R.id.btncancel); // The Finish button
        EditText txtName = (EditText) findViewById(R.id.taskfiled);// The Title Field
        Switch urgentS = (Switch) findViewById(R.id.switchurgent);// The Urgent Switch
        EditText txtNote = (EditText) findViewById(R.id.notefiled);// The Note Field
        Switch dayBeforeS = (Switch) findViewById(R.id.switchremind);// The Day Before Switch
        EditText txtDate = (EditText) findViewById(R.id.dateinput);// The Text Field
        // Listeners
        android.view.View.OnClickListener donelistener = new View.OnClickListener() { // Finish Button Listener
            public void onClick(View v) {
                // TODO: create Task and save it
                Log.d("BUTTONS", "User tapped the Finish button");
                String Name = txtName.getText().toString();// Get the Title
                String Note = txtNote.getText().toString();// Get the Note
                String Date = txtDate.getText().toString();// Get the Date (Might change this to someother type late)
                Boolean DateBefroe = dayBeforeS.isChecked();// Get Day Before status
                Boolean urgent = urgentS.isChecked();// Get Urgent status

                Boolean DateisRight = false;

                if (Name.matches("")) {// if Title is empty
                    Toast.makeText(getApplicationContext(), "Name is empty", Toast.LENGTH_SHORT).show();
                } else {
                    // TODO: check if the current data is valid
                    // TODO: Date check if valid if empty and add it as an alarm and if so add to
                    // DataBase
                    DataBaseHelper dbHelper = new DataBaseHelper(getApplicationContext());
                    TaskObj task = new TaskObj(Name, Date, urgent, DateisRight, Note);
                    boolean success = dbHelper.addOne(task);
                    // Toast.makeText(getApplicationContext(), "Success = "+success, Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                }
            }
        };
        btndn.setOnClickListener(donelistener);

        android.view.View.OnClickListener cancellistener = new View.OnClickListener() { // Finish Button Listener
            public void onClick(View v) {
                // TODO: create Task and save it
                Log.d("BUTTONS", "User tapped the cancel button");
                startActivity(intent);
                finish();
            }
        };
        btncln.setOnClickListener(cancellistener);
    }
}
