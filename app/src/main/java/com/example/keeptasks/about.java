package com.example.keeptasks;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;

public class about extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Intent intentSettings = new Intent(getApplicationContext(), Settings.class); // go from main to settings
        Button btnexit = (Button) findViewById(R.id.btnexitabout);
        android.view.View.OnClickListener exitlistener = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO: create alarm and set in storage
                Log.d("BUTTONS", "User tapped the Exit button");
                finish();
                startActivity(intentSettings);
            }
        };
        btnexit.setOnClickListener(exitlistener);
    }

}
