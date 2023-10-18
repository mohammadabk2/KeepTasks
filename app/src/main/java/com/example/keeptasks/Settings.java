package com.example.keeptasks;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.util.Log;
import android.content.Intent;

public class Settings extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        //move between pages
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        // buttons
        Button btnexit = (Button) findViewById(R.id.btedit);
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
    }
}
