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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        // move between pages
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        // front end
        Button btnexitsetting = (Button) findViewById(R.id.btnexitsetting);
        Button btnhistory = (Button) findViewById(R.id.btnhistory);
        Button btnclear = (Button) findViewById(R.id.btnclear);
        ListView lv_history = (ListView) findViewById(R.id.lv_history);
        // Listeners
        android.view.View.OnClickListener exitlistener = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO: create alarm and set in storage
                Log.d("BUTTONS", "User tapped the Exit button");
                finish();
                startActivity(intent);
            }
        };
        btnexitsetting.setOnClickListener(exitlistener);

        android.view.View.OnClickListener historylistener = new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the History button");
                try{
                    DataBaseHelper dbHelper = new DataBaseHelper(getApplicationContext());
                    ArrayAdapter taskAdapter = new ArrayAdapter<TaskObj>(getApplicationContext(),
                            R.layout.lv_color_white, dbHelper.getEverything(DataBaseHelper.table_history_name));
                    lv_history.setAdapter(taskAdapter);
                }
                catch (Exception e){
                    //
                }

            }
        };
        btnhistory.setOnClickListener(historylistener);

        android.view.View.OnClickListener clearlistener = new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the Clear History button");
                DataBaseHelper dbHelper = new DataBaseHelper(getApplicationContext());
                dbHelper.clear_History();
                lv_history.setAdapter(null);
            }
        };
        btnclear.setOnClickListener(clearlistener);
    }
}
