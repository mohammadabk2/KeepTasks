package com.example.keeptasks;

/*To make a Notfication call Permission then call makeNotfication
*             NotificationManager notificationManager = (NotificationManager) getSystemService(
                        Context.NOTIFICATION_SERVICE);
                Notfication.makeNotification(about.this, about.this,"CHANNEL_ID_NOTIFICATION", "head", "body test 123456 hahaha no way this works", Color.GREEN,
                        true,notificationManager,MainActivity.class);
 */
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class Notfication extends AppCompatActivity {

    public static void makeNotification(Context context, Activity activity, String chanel_id, String title, String body,
            int color, boolean vibrate, NotificationManager notificationManager, Class toGO) {

        if (Permissions.notficationPermission(context, activity)) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, chanel_id);
            builder.setSmallIcon(R.drawable.ic_notifications_active_24)
                    .setContentTitle(title)
                    .setContentText(body)
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_HIGH); // add in the argument and a method to choose
            Intent intent = new Intent(context, toGO);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("some data", "some value");

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent,
                    PendingIntent.FLAG_IMMUTABLE);
            builder.setContentIntent(pendingIntent);
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
