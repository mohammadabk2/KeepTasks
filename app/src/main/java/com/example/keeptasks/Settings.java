package com.example.keeptasks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;

public class Settings extends AppCompatActivity {

    // back end
    private static final int REQUEST_WRITE_PERMISSION = 786;
    String clear_message = " Clear List ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        // move between pages
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        Intent intentabout = new Intent(getApplicationContext(), about.class);

        // front end
        Button btnexitsetting = (Button) findViewById(R.id.btnexitsettings);
        Button btnclear = (Button) findViewById(R.id.btnclear);
        btnclear.setText(clear_message);
        Button btnimport = (Button) findViewById(R.id.btnimport);
        Button btnexport = (Button) findViewById(R.id.btnexport);
        Button btnabout = (Button) findViewById(R.id.btnaboutapp);
        // Listeners
        android.view.View.OnClickListener exitlistener = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO: create alarm and set in storage
                Log.d("BUTTONS", "User tapped the Exit button");
                finish();
                startActivity(intent);
            }
        };
        // android.view.View.OnClickListener clearlistener = new View.OnClickListener()
        // {
        // public void onClick(View v) {
        // Log.d("BUTTONS", "User tapped the Clear History button");
        // DataBaseHelper dbHelper = new DataBaseHelper(getApplicationContext());
        // dbHelper.clear_DataBase(DataBaseHelper.table_history_name);
        // }
        // };

        android.view.View.OnClickListener importlistener = new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the Import button");
                // requestPermission();
                // TODO:

            }
        };

        android.view.View.OnClickListener exportlistener = new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the Expotr button");
                // TODO:
            }
        };
        android.view.View.OnClickListener aboutlistener = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO: create alarm and set in storage
                Log.d("BUTTONS", "User tapped the about button");
                finish();
                startActivity(intentabout);
            }
        };

        android.view.View.OnClickListener Menulistener = new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the Menu button");
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), v);
                popupMenu.getMenuInflater().inflate(R.menu.clearmenue, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        DataBaseHelper dbHelper = new DataBaseHelper(getApplicationContext());
                        if (item.getItemId() == R.id.item_all) {
                            Log.d("BUTTONS", "User tapped the Clear All button");
                            dbHelper.clear_Table(DataBaseHelper.table_name);
                        } else if (item.getItemId() == R.id.item_history) {
                            Log.d("BUTTONS", "User tapped the Clear History button");
                            dbHelper.clear_Table(DataBaseHelper.table_history_name);
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        };
        // set to buttons
        btnexitsetting.setOnClickListener(exitlistener);
        btnclear.setOnClickListener(Menulistener);
        btnimport.setOnClickListener(importlistener);
        btnexport.setOnClickListener(exportlistener);
        btnabout.setOnClickListener(aboutlistener);
    }
}