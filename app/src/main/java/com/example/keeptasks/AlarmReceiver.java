package com.example.keeptasks;

import static com.example.keeptasks.Permissions.notficationPermission;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/*
* call make Alarm first to set everything
*  then call the alarm manger and it auto uses onReceive
* */

public class AlarmReceiver extends BroadcastReceiver {
    private Context context;
    private Activity activity;
    private int color;
    private NotificationManager notificationManager;
    private String head, body;

    public void setNotfiDetails(Context context, Activity activity, NotificationManager notificationManager, String head,
            String body, int color) {// if i want to add vibrate change it here
        // AlarmManager alarmManager = (AlarmManager)
        // getSystemService(Context.ALARM_SERVICE); // add when used
        // alarmManager
        this.context = context;
        this.activity = activity;
        this.color = color;
        this.notificationManager = notificationManager;
        this.head = head;
        this.body = body;

    }

    @Override
    public void onReceive(Context context, Intent intent) {
            Notfication.makeNotification(this.context, this.activity, "CHANNEL_ID_NOTIFICATION", this.head,
                    this.body, this.color,
                    true, this.notificationManager, MainActivity.class);


    }
}
