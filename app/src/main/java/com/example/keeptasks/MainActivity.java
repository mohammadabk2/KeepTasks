package com.example.keeptasks;

import androidx.appcompat.app.AppCompatActivity;
//import main.java.com.example.keeptasks.TaskObj;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Button;
import android.view.View;
import android.util.Log;
import android.content.Intent;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // backend
    private static ArrayList<String> list;
    ListView lv_task;
    DataBaseHelper dbHelper;
    ArrayAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // intents
        Intent intentTask = new Intent(getApplicationContext(), CreateTask.class); // go from main to form
        Intent intentSettings = new Intent(getApplicationContext(), Settings.class); // go from main to settings
        // backend start
        dbHelper = new DataBaseHelper(getApplicationContext());
        lv_task = (ListView) findViewById(R.id.lv);
        showEverything(dbHelper);
        // frontend
        Button btnadd, btnAll, btngosetting, btnExit;
        // add Task
        btnadd = (Button) findViewById(R.id.btnadd);
        android.view.View.OnClickListener addlistener = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO:add create alarm function here
                Log.d("BUTTONS", "User tapped the add button");
                showEverything(dbHelper);
                finish();
                startActivity(intentTask);
            }
        };
        // All Tasks
        btnAll = (Button) findViewById(R.id.btnalltask);
        android.view.View.OnClickListener allgolistener = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO:add create go to All screen
                Log.d("BUTTONS", "User tapped the go to ALL button");
                dbHelper = new DataBaseHelper(getApplicationContext());
                showEverything(dbHelper);
            }
        };
        // delete Task
        lv_task.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TaskObj clickedTask = (TaskObj) parent.getItemAtPosition(position);

                dbHelper = new DataBaseHelper(getApplicationContext());
                dbHelper.delete_Task(clickedTask);
                showEverything(dbHelper);
                
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
            }
        });
        // settings
        btngosetting = (Button) findViewById(R.id.btnsettings);
        android.view.View.OnClickListener settingslistener = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO:add create go to normal screen
                Log.d("BUTTONS", "User tapped the go to Settings button");
                startActivity(intentSettings);
            }
        };
        // Exit app
        btnExit = (Button) findViewById(R.id.btnexit);
        android.view.View.OnClickListener exitlistener = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO:add create go to normal screen
                Log.d("BUTTONS", "User tapped the Exit button");
                finish();
                System.exit(0);
            }
        };

        // Set Listener
        btnAll.setOnClickListener(allgolistener);
        btnadd.setOnClickListener(addlistener);
        btngosetting.setOnClickListener(settingslistener);
        btnExit.setOnClickListener(exitlistener);
    }

    // function to update the list view
    private void showEverything(DataBaseHelper dbH) {
        this.taskAdapter = new ArrayAdapter<TaskObj>(getApplicationContext(),
                android.R.layout.simple_list_item_1, dbH.getEverything());
        this.lv_task.setAdapter(taskAdapter);
    }

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
}
