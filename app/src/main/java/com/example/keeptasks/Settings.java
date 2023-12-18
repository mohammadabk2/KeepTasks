package com.example.keeptasks;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import android.content.Intent;

public class Settings extends AppCompatActivity {

    // back end
    private static ArrayList<String> list;
    DataBaseHelper dbHelper;
    ArrayAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        // move between pages
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        // front end
        Button btnexit = (Button) findViewById(R.id.btnexitsetting);
        Button btnhistory = (Button) findViewById(R.id.btnhistory);
        ListView lv_history = (ListView) findViewById(R.id.lv2);
        // Listeners
        android.view.View.OnClickListener exitlistener = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO: create alarm and set in storage
                Log.d("BUTTONS", "User tapped the Exit button");
                finish();
                startActivity(intent);
            }
        };
        btnexit.setOnClickListener(exitlistener);

        android.view.View.OnClickListener historylistener = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO: create alarm and set in storage
                Log.d("BUTTONS", "User tapped the History button");
                dbHelper = new DataBaseHelper(getApplicationContext());
                taskAdapter = new ArrayAdapter<TaskObj>(getApplicationContext(),
                        android.R.layout.simple_list_item_1, dbHelper.getEverything());
                lv_history.setAdapter(taskAdapter);

            }
        };
        btnexit.setOnClickListener(historylistener);
    }
}
