package com.example.keeptasks;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Usage extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usage);
        Intent intentSettings = new Intent(getApplicationContext(), Settings.class); // go from main to settings
        TextView textV1 = (TextView) findViewById(R.id.tv1);
        textV1.setText(constants.usage1);
        textV1.setTextColor(Color.WHITE);
        TextView textV2 = (TextView) findViewById(R.id.tv2);
        textV2.setText(constants.usage2);
        textV2.setTextColor(Color.WHITE);
        Button btnExit = (Button) findViewById(R.id.btnexitusage);

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
