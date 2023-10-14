package com.example.keeptasks;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.util.Log;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Switch;

public class CreateTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createtask);
        // move between pages
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        // screen Objects
        Button btndn = (Button) findViewById(R.id.btndone);
        EditText txtName = (EditText) findViewById(R.id.taskfiled);
        Switch urgent = (Switch) findViewById(R.id.switchurgent);

        EditText txtNote = (EditText) findViewById(R.id.notefiled);
        // Listeners
        android.view.View.OnClickListener donelistener = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO: create Task and save it
                Log.d("BUTTONS", "User tapped the Finish button");
                String Name = txtName.getText().toString();
                String Note = txtNote.getText().toString();
                Boolean DateisRight=true;//chagne later
                if (Name == "") {
                    
                    // TODO: add pop up message to tell them name is empty
                }
                if (urgent.isChecked()) {
                    Name = "!!!" + Name;
                    Log.d("BUTTONS", "Switch Works is on");
                }else{
                    Log.d("BUTTONS", "Switch Works is Off");
                }


                if (Name != "" && DateisRight) {
                    startActivity(intent);
                }
            }
        };
        btndn.setOnClickListener(donelistener);
    }
}
