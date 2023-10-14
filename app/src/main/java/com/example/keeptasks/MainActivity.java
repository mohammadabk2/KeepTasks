package com.example.keeptasks;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.util.Log;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // move between pages
        Intent intentTask = new Intent(getApplicationContext(), CreateTask.class);
        Intent intentSettings = new Intent(getApplicationContext(), Settings.class);

         // screen Objects
        Button btnadd = (Button) findViewById(R.id.btnadd);
        Button btnurgent = (Button) findViewById(R.id.btngourgent);
        Button btnnormal = (Button) findViewById(R.id.btngonormal);
        Button btnAll = (Button) findViewById(R.id.btndone);
        Button btngosetting = (Button) findViewById(R.id.btnsettings);

        // Listeners
        android.view.View.OnClickListener addlistener = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO:add create alarm function here
                Log.d("BUTTONS", "User tapped the add button");
                startActivity(intentTask);
                // setContentView(R.layout.activity_createtask);
            }
        };
        btnadd.setOnClickListener(addlistener);
        android.view.View.OnClickListener urgentgolistener = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO:add create go to Urgent screen
                Log.d("BUTTONS", "User tapped the go to Urgent button");
            }
        };
        btnurgent.setOnClickListener(urgentgolistener);
        android.view.View.OnClickListener normalgolistener = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO:add create go to normal screen
                Log.d("BUTTONS", "User tapped the go to normal button");
            }
        };
        btnnormal.setOnClickListener(normalgolistener);
        android.view.View.OnClickListener allgolistener = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO:add create go to normal screen
                Log.d("BUTTONS", "User tapped the go to ALL button");
            }
        };
        btnAll.setOnClickListener(allgolistener);
        android.view.View.OnClickListener settingslistener = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO:add create go to normal screen
                Log.d("BUTTONS", "User tapped the go to Settings button");
                startActivity(intentSettings);
            }
        };
        btngosetting.setOnClickListener(settingslistener);
    }

}
