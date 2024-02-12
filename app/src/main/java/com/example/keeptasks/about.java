package com.example.keeptasks;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.TextView;

public class about extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Intent intentSettings = new Intent(getApplicationContext(), Settings.class); // go from main to settings
        Button btnExit = (Button) findViewById(R.id.btnexitabout);
        TextView version = (TextView) findViewById(R.id.version_info);
        version.setText(constants.version);
        TextView desc = (TextView) findViewById(R.id.textView2);
        desc.setText(constants.desc);

        android.view.View.OnClickListener exitListener = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO: create alarm and set in storage
                Log.d("BUTTONS", "User tapped the Exit button");
                finish();
                startActivity(intentSettings);
            }
        };
        btnExit.setOnClickListener(exitListener);
    }
}
