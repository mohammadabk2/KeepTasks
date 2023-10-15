package com.example.keeptasks;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.util.Log;
import android.content.Intent;
import java.io.File;
import java.io.FileOutputStream;
import android.content.Context;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class CreateTask extends AppCompatActivity {
    int count1 = 0;
    int count2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createtask);

        // move between pages
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        // Screen Objects
        Button btndn = (Button) findViewById(R.id.btndone);
        EditText txtName = (EditText) findViewById(R.id.taskfiled);
        Switch urgent = (Switch) findViewById(R.id.switchurgent);
        EditText txtNote = (EditText) findViewById(R.id.notefiled);
        Switch dayBefore = (Switch) findViewById(R.id.switchremind);

        // Listeners
        android.view.View.OnClickListener donelistener = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO: create Task and save it
                Log.d("BUTTONS", "User tapped the Finish button");
                String Name = txtName.getText().toString();
                String Note = txtNote.getText().toString();

                if (Name.matches("")) {// if Title is empty
                    Toast.makeText(getApplicationContext(), "Name is empty", Toast.LENGTH_SHORT).show();
                }

                if (urgent.isChecked() && count1 == 0) {// for Urgent checkbox
                    count1++;
                    Name = "!!! " + Name + " !!!";
                    Log.d("BUTTONS", "Switch Works is on");
                } else {
                    Log.d("BUTTONS", "Switch Works is Off");
                }

                // TODO: Date check if valid if empty and add it as an alarm
                Boolean DateisRight = true;// change later
                if (dayBefore.isChecked() && count2 == 0) {// for Urgent checkbox
                    count2++;
                    // create another alarm the day before
                }

                makeFile(Name);// creats new file with the Task name
                writeToFile(Name, Name);// adds the Name to the File
                // writeToFile(Name, date);// adds the Date to the File
                if (!Note.matches("")) {// add note content
                    writeToFile(Name, "\n" + "\n" + Note);// adds the Notes to the File
                }

                if (!Name.matches("") && DateisRight) {

                    startActivity(intent);
                }
            }
        };
        btndn.setOnClickListener(donelistener);
    }

    public void makeFile(String fileName) {
        File f = new File(fileName + ".txt");// add path
        try {
            f.createNewFile();
        } catch (Exception e) {
            Log.d("BUTTONS", "Failed to create New File in makeFile");
        }
    }

    public void writeToFile(String fileName, String content) {
        // File path = getApplicationContext().getFilesDir();
        File path = getFilesDir();

        try {
            FileOutputStream writer = new FileOutputStream(new File(path, fileName));
            writer.write(content.getBytes());
            writer.close();
            Log.d("BUTTONS", "Wrote to File");
        } catch (Exception e) {
            Log.d("BUTTONS", "Failed to create New FileOutputStream");
        }

    }
}
