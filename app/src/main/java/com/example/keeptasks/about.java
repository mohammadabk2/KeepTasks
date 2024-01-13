package com.example.keeptasks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
        Button btnexit = (Button) findViewById(R.id.btnexitabout);
        TextView version = (TextView) findViewById(R.id.version_info);
        version.setText(constants.version);
        TextView desc = (TextView) findViewById(R.id.textView2);
        desc.setText(constants.desc);

        android.view.View.OnClickListener exitlistener = new View.OnClickListener() {
            public void onClick(View v) {
                // TODO: create alarm and set in storage
                Log.d("BUTTONS", "User tapped the Exit button");
                // testing notfiacations
                NotificationBody noti = new NotificationBody();
                NotificationManager notificationManager = (NotificationManager) getSystemService(
                        Context.NOTIFICATION_SERVICE);
                Notfication.makeNotification(about.this, about.this,"CHANNEL_ID_NOTIFICATION", "head", "body test 123456 hahaha no way this works", Color.GREEN,
                        true,notificationManager);

                // finish();
                // startActivity(intentSettings);
            }
        };
        btnexit.setOnClickListener(exitlistener);
    }


}
