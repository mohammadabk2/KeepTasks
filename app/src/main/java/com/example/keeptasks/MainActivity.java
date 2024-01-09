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
    private boolean on_history = false;

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
        showEverything(dbHelper, DataBaseHelper.table_name);
        // on Screen
        Button btnpopup = (Button) findViewById(R.id.btnpopup);
        Button btnadd = (Button) findViewById(R.id.btnadd);
        // Button btnAll = (Button) findViewById(R.id.btnalltask);
        Button btngosetting = (Button) findViewById(R.id.btnsettings);
        Button btnExit = (Button) findViewById(R.id.btnexit);
        // add Task
        android.view.View.OnClickListener addlistener = new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the add button");
                showEverything(dbHelper, DataBaseHelper.table_name);
                finish();
                startActivity(intentTask);
            }
        };
        // Compelte Task
        lv_task.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TaskObj clickedTask = (TaskObj) parent.getItemAtPosition(position);
                dbHelper = new DataBaseHelper(getApplicationContext());
                String table = "";
                if (!on_history) {
                    dbHelper.delete_Task(clickedTask);
                    table = table = DataBaseHelper.table_name;
                    Toast.makeText(getApplicationContext(), task_complete, Toast.LENGTH_SHORT).show();
                } else {
                    table = table = DataBaseHelper.table_history_name;
                }
                showEverything(dbHelper, table); // change this to the right table being used
            }
        });
        // settings
        android.view.View.OnClickListener settingslistener = new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the go to Settings button");
                startActivity(intentSettings);
            }
        };
        // Exit app
        android.view.View.OnClickListener exitlistener = new View.OnClickListener() {
            public void onClick(View v) {
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
                        dbHelper = new DataBaseHelper(getApplicationContext());
                        String toShow = "";
                        if (item.getItemId() == R.id.item_all) {
                            Log.d("BUTTONS", "User tapped the go to ALL button");
                            toShow = DataBaseHelper.table_name;
                            btnpopup.setText("All");
                            on_history = false;
                        } else if (item.getItemId() == R.id.item_history) {
                            toShow = DataBaseHelper.table_history_name;
                            on_history = true;
                            btnpopup.setText("Completed");
                        }
                        showEverything(dbHelper, toShow);
                        return true;
                    }
                });
                popupMenu.show();
            }
        };

        // Set Listener
        btnadd.setOnClickListener(addlistener);
        btngosetting.setOnClickListener(settingslistener);
        btnExit.setOnClickListener(exitlistener);
        btnpopup.setOnClickListener(Menulistener);
    }

    // function to update the list view
    private void showEverything(DataBaseHelper dbH, String tableName) {
        this.taskAdapter = new ArrayAdapter<TaskObj>(getApplicationContext(),
                R.layout.lv_color_white, dbH.getEverything(tableName));
        this.lv_task.setAdapter(taskAdapter);
    }
}
