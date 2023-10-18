package com.example.keeptasks;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;
import android.util.Log;

public class TaskBody extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskshow);

        FileRead fr = new FileRead(null);
        // Intent
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        // Screen Objects
        TextView vTitle = (TextView) findViewById(R.id.viewtitle);
        vTitle.setText(getName());
        
        TextView vDate = (TextView) findViewById(R.id.viewtitle);
        TextView vNote = (TextView) findViewById(R.id.viewnote);
        Button btnEdit = (Button) findViewById(R.id.btedit);
        //
        android.view.View.OnClickListener editlistener = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO:add create go to All screen
                Log.d("BUTTONS", "User tapped the Edit button");
                startActivity(intent);
                finish();

            }
        };
        btnEdit.setOnClickListener(editlistener);
        // vTitle.setText(this.title);
        // vNote.setText(this.Note);
        // add date
    }

}

// path it gets saved in /data/data/com.example.keeptasks/files
