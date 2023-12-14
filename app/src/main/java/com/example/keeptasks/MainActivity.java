package com.example.keeptasks;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.util.Log;
import android.content.Intent;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static ArrayList<String> list = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intentTask = new Intent(getApplicationContext(), CreateTask.class);
        //add task
        Button btnadd = (Button) findViewById(R.id.btnadd);
        android.view.View.OnClickListener addlistener = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO:add create alarm function here
                Log.d("BUTTONS", "User tapped the add button");
                finish();
                startActivity(intentTask);
                // finish();
            }
        };
        btnadd.setOnClickListener(addlistener);
        //
        Button btnAll = (Button) findViewById(R.id.btnalltask);
        Intent intentTasksAll = new Intent(getApplicationContext(), TaskBody.class);
        android.view.View.OnClickListener allgolistener = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO:add create go to All screen
                Log.d("BUTTONS", "User tapped the go to ALL button");
                finish();
                startActivity(intentTasksAll);
                // finish();

            }
        };
        btnAll.setOnClickListener(allgolistener);

        // Button btnurgent = (Button) findViewById(R.id.btngourgent);
        // android.view.View.OnClickListener urgentgolistener = new
        // View.OnClickListener() {
        // public void onClick(View v) {
        // // TODO:add create go to Urgent screen
        // Log.d("BUTTONS", "User tapped the go to Urgent button");
        // }
        // };
        // btnurgent.setOnClickListener(urgentgolistener);

        // Button btnnormal = (Button) findViewById(R.id.btngonormal);
        // android.view.View.OnClickListener normalgolistener = new
        // View.OnClickListener() {
        // public void onClick(View v) {
        // // TODO:add create go to normal screen
        // Log.d("BUTTONS", "User tapped the go to normal button");
        // }
        // };
        // btnnormal.setOnClickListener(normalgolistener);

        Button btngosetting = (Button) findViewById(R.id.btnsettings);
        Intent intentSettings = new Intent(getApplicationContext(), Settings.class);
        android.view.View.OnClickListener settingslistener = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO:add create go to normal screen
                Log.d("BUTTONS", "User tapped the go to Settings button");
                startActivity(intentSettings);
            }
        };
        btngosetting.setOnClickListener(settingslistener);

        Button btnExit = (Button) findViewById(R.id.btnexit);
        android.view.View.OnClickListener exitlistener = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO:add create go to normal screen
                finish();
                Log.d("BUTTONS", "User tapped the Exit button");
            }
        };
        btnExit.setOnClickListener(exitlistener);
    }
}
