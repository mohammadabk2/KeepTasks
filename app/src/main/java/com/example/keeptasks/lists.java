package com.example.keeptasks;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class lists extends AppCompatActivity {
    // private ArrayList<String> listOfLists;

    // public lists() {
    //     listOfLists = new ArrayList<String>();
    //     listOfLists.add(constants.listAll);
    //     listOfLists.add(constants.listHistory);
    // }

    // public ArrayList<String> getListOfLists() {
    //     return this.listOfLists;
    // }

    // public void addToList(String listName) {
    //     this.listOfLists.add(listName);
    // }

    // public void removeFromList(String listName) {
    //     this.listOfLists.remove(listName);
    // }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addlist);
        Intent intentSettings = new Intent(getApplicationContext(), Settings.class);
        Button btnExit = (Button) findViewById(R.id.btnexitaddlist);
        Button btnAddList = (Button) findViewById(R.id.btnaddlisttoist);
        Button btnClear = (Button) findViewById(R.id.btnclearlists);
        btnClear.setText(constants.clearMessage);
        Button btnRemoveList = (Button) findViewById(R.id.btnremovelist);
        EditText listName = (EditText) findViewById(R.id.listtoadd);
        listName.setHintTextColor(Color.WHITE);
        listName.setHint("List to add");
        DataBaseHelper dbHelper = new DataBaseHelper(getApplicationContext());

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
                Log.d("BUTTONS", "User tapped the add list button");
                dbHelper.addToList(listName.getText().toString());
                listName.setText("List to add");
            }
        };
        android.view.View.OnClickListener MenuListener = new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the Clear lists button");
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), v);
                popupMenu.getMenuInflater().inflate(R.menu.clearmenue, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.item_all) {
                            Log.d("BUTTONS", "User tapped the Clear All button");
                            dbHelper.clearTable(DataBaseHelper.tableName);
                        } else if (item.getItemId() == R.id.item_history) {
                            Log.d("BUTTONS", "User tapped the Clear History button");
                            dbHelper.clearTable(DataBaseHelper.tableHistoryName);
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        };

        android.view.View.OnClickListener removeListListener = new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the Remove list button");
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), v);
                popupMenu.getMenuInflater().inflate(R.menu.listmenue, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    // create all the menue items
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        // if(item.getItemId() == R.id.)
//                         if (item.getItemId() == R.id.item_all) {
//                             Log.d("BUTTONS", "User tapped the Clear All button");
//                             dbHelper.clearTable(DataBaseHelper.tableName);
//                         } else if (item.getItemId() == R.id.item_history) {
//                             Log.d("BUTTONS", "User tapped the Clear History button");
//                             dbHelper.clearTable(DataBaseHelper.tableHistoryName);
//                         }
                         return true;
                    }
                });
                popupMenu.show();

            }
        };
        btnExit.setOnClickListener(exitListener);
        btnAddList.setOnClickListener(addListListener);
        btnClear.setOnClickListener(MenuListener);
        btnRemoveList.setOnClickListener(removeListListener);
    }
}
