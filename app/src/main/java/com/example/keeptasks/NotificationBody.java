package com.example.keeptasks;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class NotificationBody extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        TextView view = findViewById(R.id.notification_view);
        view.setText("testing in java body");
    }

//    public void makeNotification(Context context, Activity activity, String chanel_id, String title, String body, int color, boolean vibrate) {
//        Log.d("Notification", "!!!!!!!"+Permissions.notficationPermission(context,activity));
//        if (Permissions.notficationPermission(context,activity)) {
//            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, chanel_id);
//            builder.setSmallIcon(R.drawable.ic_notifications_active_24)
//                    .setContentTitle(title)
//                    .setContentText(body)
//                    .setAutoCancel(true)
//                    .setPriority(NotificationCompat.PRIORITY_HIGH); // add in the argument and a method to choose
//            Log.d("Notification", "Before creating intent");
//            Intent intent = new Intent(context, NotificationBody.class);// add arguemnt to builder
//            Log.d("Notification", "after creating intent");
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            intent.putExtra("some data", "some value");
//
//            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent,
//                    PendingIntent.FLAG_IMMUTABLE);
//            builder.setContentIntent(pendingIntent);
//            NotificationManager notificationManager = (NotificationManager) getSystemService(
//                    Context.NOTIFICATION_SERVICE);
//
//            NotificationChannel notificationChannel = notificationManager.getNotificationChannel(chanel_id);
//            if (notificationChannel == null) {
//                int importance = NotificationManager.IMPORTANCE_HIGH;// make in arguemnt
//                notificationChannel = new NotificationChannel(chanel_id, "some desc", importance);
//                notificationChannel.setLightColor(color);
//                notificationChannel.enableVibration(vibrate);
//                notificationManager.createNotificationChannel(notificationChannel);
//            }
//
//            notificationManager.notify(0, builder.build());
//        }
//
//    }
}
