package com.example.keeptasks;

/*
 * To make a Notfication call the check checkPermission then makeNotfication
 */
import android.Manifest;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

public class Notfication extends AppCompatActivity {

    private Context context;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

//    public void setNotfication(Context context, Activity activity) {
//        this.context = context;
//        this.activity = activity;
//    }


    public static void makeNotification(Context context, Activity activity,String chanel_id, String title, String body, int color, boolean vibrate , NotificationManager notificationManager) {
        Log.d("Notification", "!!!!!!!"+Permissions.notficationPermission(context,activity));
        if (Permissions.notficationPermission(context,activity)) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, chanel_id);
            builder.setSmallIcon(R.drawable.ic_notifications_active_24)
                    .setContentTitle(title)
                    .setContentText(body)
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_HIGH); // add in the argument and a method to choose
            Log.d("Notification", "Before creating intent");
            Intent intent = new Intent(context, NotificationBody.class);// add arguemnt to builder
            Log.d("Notification", "after creating intent");
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("some data", "some value");

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent,
                    PendingIntent.FLAG_IMMUTABLE);
            builder.setContentIntent(pendingIntent);
//            NotificationManager notificationManager = (NotificationManager) getSystemService(
//                    Context.NOTIFICATION_SERVICE);

            NotificationChannel notificationChannel = notificationManager.getNotificationChannel(chanel_id);
            if (notificationChannel == null) {
                int importance = NotificationManager.IMPORTANCE_HIGH;// make in arguemnt
                notificationChannel = new NotificationChannel(chanel_id, "some desc", importance);
                notificationChannel.setLightColor(color);
                notificationChannel.enableVibration(vibrate);
                notificationManager.createNotificationChannel(notificationChannel);
            }

            notificationManager.notify(0, builder.build());
        }

    }
}
