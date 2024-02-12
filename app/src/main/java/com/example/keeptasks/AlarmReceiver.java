package com.example.keeptasks;

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
    public static Activity activity; // add to setDetailsAlarm
    private static String channel, head, body;

    public static void setDetailsAlarm(String chan, String he, String bod) {
        channel = chan;
        head = he;
        body = bod;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (!Permissions.notficationPermission(context, activity)) {
            Toast.makeText(context,
                    "Notfications " + constants.permissionDenied + constants.allowPermission,
                    Toast.LENGTH_SHORT).show();
        }
        Notfication.makeNotification(context, activity, channel, head, body,
                Color.BLACK,
                true, notificationManager, MainActivity.class);
    }
}
