package com.example.keeptasks;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.view.View;
import android.util.Log;
import android.content.Intent;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // backend
    private static ArrayList<String> list;
    private String task_complete = "Task Completed";
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
        // on Screen
        Button btnpopup = (Button) findViewById(R.id.btnpopup);
        Button btnadd = (Button) findViewById(R.id.btnadd);
//        Button btnAll = (Button) findViewById(R.id.btnalltask);
        Button btngosetting = (Button) findViewById(R.id.btnsettings);
        Button btnExit = (Button) findViewById(R.id.btnexit);
        // add Task
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
//        android.view.View.OnClickListener allgolistener = new View.OnClickListener() {
//            public void onClick(View v) {
//                // TODO:add create go to All screen
//                Log.d("BUTTONS", "User tapped the go to ALL button");
//                dbHelper = new DataBaseHelper(getApplicationContext());
//                showEverything(dbHelper);
//            }
//        };
        // delete Task
        lv_task.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TaskObj clickedTask = (TaskObj) parent.getItemAtPosition(position);

                dbHelper = new DataBaseHelper(getApplicationContext());
                dbHelper.delete_Task(clickedTask);
                showEverything(dbHelper);

                Toast.makeText(getApplicationContext(), task_complete, Toast.LENGTH_SHORT).show();
            }
        });
        // settings
        android.view.View.OnClickListener settingslistener = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO:add create go to normal screen
                Log.d("BUTTONS", "User tapped the go to Settings button");
                startActivity(intentSettings);
            }
        };
        // Exit app
        android.view.View.OnClickListener exitlistener = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO:add create go to normal screen
                Log.d("BUTTONS", "User tapped the Exit button");
                finish();
                System.exit(0);
            }
        };
        // Menu button
        android.view.View.OnClickListener Menulistener = new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the Menu button");
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), v);
                popupMenu.getMenuInflater().inflate(R.menu.sortmenue, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.item_all) {
                            Log.d("BUTTONS", "User tapped the go to ALL button");
                            dbHelper = new DataBaseHelper(getApplicationContext());
                            showEverything(dbHelper);
                            return true;
                        }
                        // TODO: add urgent and normal
                        return false;
                    }
                });
                popupMenu.show();
            }
        };

        // Set Listener
//        btnAll.setOnClickListener(allgolistener); // remove later
        btnadd.setOnClickListener(addlistener);
        btngosetting.setOnClickListener(settingslistener);
        btnExit.setOnClickListener(exitlistener);
        btnpopup.setOnClickListener(Menulistener);

    }

    // function to update the list view
    private void showEverything(DataBaseHelper dbH) {
        this.taskAdapter = new ArrayAdapter<TaskObj>(getApplicationContext(),
                R.layout.lv_color_white, dbH.getEverything(DataBaseHelper.table_name));
        this.lv_task.setAdapter(taskAdapter);
    }
}
