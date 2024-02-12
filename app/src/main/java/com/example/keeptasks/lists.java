package com.example.keeptasks;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class lists extends AppCompatActivity {
    private ArrayList<String> listOfLists;

    public lists() {
        listOfLists = new ArrayList<String>();
        listOfLists.add(constants.listAll);
        listOfLists.add(constants.listHistory);
    }

    public ArrayList<String> getListOfLists() {
        return this.listOfLists;
    }

    public void addToList(String listName) {
        this.listOfLists.add(listName);
    }

    public void removeFromList(String listName) {
        this.listOfLists.remove(listName);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addlist);
        Intent intentSettings = new Intent(getApplicationContext(), Settings.class);
        Button btnexit = (Button) findViewById(R.id.btnexitaddlist);
        Button btnaddlist = (Button) findViewById(R.id.btnaddlisttoist);

        android.view.View.OnClickListener exitListener = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO: create alarm and set in storage
                Log.d("BUTTONS", "User tapped the Exit button");
                finish();
                startActivity(intentSettings);
            }
        };

        android.view.View.OnClickListener addListListener = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO: create alarm and set in storage
                Log.d("BUTTONS", "User tapped the add list button");
                // TODO: add to list
            }
        };
        btnexit.setOnClickListener(exitListener);
        btnaddlist.setOnClickListener(addListListener);
    }
}
