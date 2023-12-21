package com.example.keeptasks;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
        Button btnimport = (Button) findViewById(R.id.btnimport);
        Button btnexport = (Button) findViewById(R.id.btnexport);
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

        android.view.View.OnClickListener historylistener = new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the History button");
                try {
                    DataBaseHelper dbHelper = new DataBaseHelper(getApplicationContext());
                    ArrayAdapter taskAdapter = new ArrayAdapter<TaskObj>(getApplicationContext(),
                            R.layout.lv_color_white, dbHelper.getEverything(DataBaseHelper.table_history_name));
                    lv_history.setAdapter(taskAdapter);
                } catch (Exception e) {
                    //
                }

            }
        };

        android.view.View.OnClickListener clearlistener = new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the Clear History button");
                DataBaseHelper dbHelper = new DataBaseHelper(getApplicationContext());
                dbHelper.clear_History();
                lv_history.setAdapter(null);
            }
        };

        android.view.View.OnClickListener importlistener = new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the Import button");
                fileChooser();
            }
        };

        android.view.View.OnClickListener exportlistener = new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the Expotr button");
                fileChooser();
            }
        };
        // set to buttons
        btnexitsetting.setOnClickListener(exitlistener);
        btnhistory.setOnClickListener(historylistener);
        btnclear.setOnClickListener(clearlistener);
        btnimport.setOnClickListener(importlistener);
        btnexport.setOnClickListener(exportlistener);
    }

    // TODO: grab the file after choosing it
    private void fileChooser() {
        Intent intent_file = new Intent(Intent.ACTION_GET_CONTENT);
        intent_file.setType("*/*");
        intent_file.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult(Intent.createChooser(intent_file, "Select a Database file"), 100);
        } catch (Exception e) {
            Log.d("BUTTONS", "No file explorer found");
        }
    }
}

//     @Override
//     protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data){
//         if(requestCode == 100 && resultCode == RESULT_OK && data !=null){
//             URI uri = data.getData();
//             String path = uri.getPath();
//             File file = new File(path);
            
//         }
//     }
