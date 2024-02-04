package com.example.keeptasks;

import static androidx.core.content.ContextCompat.getSystemService;
import static com.example.keeptasks.Permissions.notficationPermission;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.Toast;

/*
* call make Alarm first to set everything
*  then call the alarm manger and it auto uses onReceive
* */

public class AlarmReceiver extends BroadcastReceiver {

    public static NotificationManager notificationManager;
    public static Activity activity;
    private  static  String channel , head,body;

    public static void setDetailsAlarm(String chan,String he,String bod){
        channel = chan;
        head = he;
        body = bod;
    }

    // public void setNotfiDetails(Context context, Activity activity,
    // NotificationManager notificationManager, String head,
    // String body, int color) {// if i want to add vibrate change it here
    // // AlarmManager alarmManager = (AlarmManager)
    // // getSystemService(Context.ALARM_SERVICE); // add when used
    // // alarmManager
    // this.context = context;
    // this.activity = activity;
    // this.color = color;
    // this.notificationManager = notificationManager;
    // this.head = head;
    // this.body = body;

    // }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (!Permissions.notficationPermission(context,activity )) {
            Toast.makeText(context,
                    "Notfications " + constants.permissionDenied + constants.allowPermission,
                    Toast.LENGTH_SHORT).show();
        }
        Notfication.makeNotification(context, activity, channel, head, body,
                Color.BLACK,
                true, notificationManager, MainActivity.class);

    }
}
