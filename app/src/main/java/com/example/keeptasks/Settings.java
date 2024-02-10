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
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        // move between pages
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        Intent intentabout = new Intent(getApplicationContext(), about.class);
        Intent intentusage = new Intent(getApplicationContext(),Usage.class);
        Intent intentlist = new Intent(getApplicationContext(),List.class);

        // front end
        Button btnexitsetting = (Button) findViewById(R.id.btnexitsettings);
        Button btnclear = (Button) findViewById(R.id.btnclear);
        btnclear.setText(constants.clear_message);
        Button btnimport = (Button) findViewById(R.id.btnimport);
        Button btnexport = (Button) findViewById(R.id.btnexport);
        Button btnabout = (Button) findViewById(R.id.btnaboutapp);
        Button btnUsage = (Button) findViewById(R.id.btnusage);
        Button btnlist = (Button) findViewById(R.id.btnaddlist);
        // Listeners
        android.view.View.OnClickListener exitlistener = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO: create alarm and set in storage
                Log.d("BUTTONS", "User tapped the Exit button");
                finish();
                startActivity(intent);
            }
        };

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
                // importExport.saveDB(getDatabasePath(constants.DB_name).toString());
                // importExport.saveDB(getFilesDir().toString());
//                Toast.makeText(getApplicationContext(),
//                        "" + importExport.saveDB(getDatabasePath(constants.DB_name).toString()), Toast.LENGTH_SHORT)
//                        .show();
                 Toast.makeText(getApplicationContext(),"" + importExport.saveDB("text.txt"),
                 Toast.LENGTH_SHORT).show();
                // importExport.saveDB("text.txt");
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

        android.view.View.OnClickListener usageListener = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO: create alarm and set in storage
                Log.d("BUTTONS", "User tapped the Usage button");
                finish();
                startActivity(intentusage);
            }
        };
        android.view.View.OnClickListener addlistlistener = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO: create alarm and set in storage
                Log.d("BUTTONS", "User tapped the Add list button");
                finish();
                startActivity(intentlist);
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
        btnUsage.setOnClickListener(usageListener);
        btnlist.setOnClickListener(addlistlistener);
    }
}