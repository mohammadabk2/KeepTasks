package com.example.keeptasks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.view.View;
import android.util.Log;
import android.content.Intent;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static ArrayList<String> list;
    private ListView lv_task;
    private  DataBaseHelper dbHelper;
    private ArrayAdapter taskAdapter;
    private boolean on_history = false;
    public static TaskObj task_held;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Permissions
        Permissions.notficationPermission(getApplicationContext(), MainActivity.this);
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
        Button btngosetting = (Button) findViewById(R.id.btnsettings);
        Button btnExit = (Button) findViewById(R.id.btnexit);
        Button btnsearch = (Button) findViewById(R.id.btnsearch);
        EditText search_box = (EditText) findViewById(R.id.search_filed);
        search_box.setHint(constants.search_hint);
        search_box.setHintTextColor(Color.WHITE);

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
        lv_task.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TaskObj clickedTask = (TaskObj) parent.getItemAtPosition(position);
                dbHelper = new DataBaseHelper(getApplicationContext());
                String table = "";
                if (!on_history) {
                    dbHelper.complete_Task(clickedTask);
                    table = DataBaseHelper.table_name;
                    Toast.makeText(getApplicationContext(), constants.task_complete, Toast.LENGTH_SHORT).show();
                } else {
                    table = DataBaseHelper.table_history_name;
                }
                showEverything(dbHelper, table); // change this to the right table being used
                return true;
            }
        });
        // Edit task
        lv_task.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TaskObj clickedTask = (TaskObj) parent.getItemAtPosition(position);
                dbHelper = new DataBaseHelper(getApplicationContext());
                task_held = clickedTask;
                finish();
                startActivity(intentTask);
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
                        List<com.example.keeptasks.TaskObj> list = new ArrayList<>();
                        if (item.getItemId() == R.id.item_all) {
                            Log.d("BUTTONS", "User tapped the go to ALL item");
                            list = dbHelper.getEverything(DataBaseHelper.table_name);
                            btnpopup.setText("All");
                            on_history = false;
                        } else if (item.getItemId() == R.id.item_history) {
                            Log.d("BUTTONS", "User tapped the go to history item");
                            list = dbHelper.getEverything(DataBaseHelper.table_history_name);
                            on_history = true;
                            btnpopup.setText("Completed");
                        } else if (item.getItemId() == R.id.item_urgent) {
                            Log.d("BUTTONS", "User tapped the go to Urgent item");
                            list = dbHelper.getSorted()[0];
                            btnpopup.setText("Urgent");
                            on_history = false;
                        } else if (item.getItemId() == R.id.item_normal) {
                            Log.d("BUTTONS", "User tapped the go to Normal item");
                            list = dbHelper.getSorted()[1];
                            btnpopup.setText("Normal");
                            on_history = false;
                        }
                        showList(list);
                        return true;
                    }
                });
                popupMenu.show();
            }
        };
        // search L
        android.view.View.OnClickListener searchlistener = new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the Search button");
                dbHelper = new DataBaseHelper(getApplicationContext());
                showList(dbHelper.search(search_box.getText().toString()));
            }
        };

        // Set Listener
        btnadd.setOnClickListener(addlistener);
        btngosetting.setOnClickListener(settingslistener);
        btnExit.setOnClickListener(exitlistener);
        btnpopup.setOnClickListener(Menulistener);
        btnsearch.setOnClickListener(searchlistener);
    }

    // function to update the list view
    private void showEverything(DataBaseHelper dbH, String tableName) {
        this.taskAdapter = new ArrayAdapter<TaskObj>(getApplicationContext(),
                R.layout.lv_color_white, dbH.getEverything(tableName));
        this.lv_task.setAdapter(taskAdapter);
    }

    private void showList(List<TaskObj> list) {
        this.taskAdapter = new ArrayAdapter<TaskObj>(getApplicationContext(),
                R.layout.lv_color_white, list);
        this.lv_task.setAdapter(taskAdapter);
    }
}
