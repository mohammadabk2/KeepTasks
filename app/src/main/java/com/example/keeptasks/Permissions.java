package com.example.keeptasks;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Permissions {
    public static boolean notficationPermission(Context context, Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(context,
                    android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity,
                        new String[] { Manifest.permission.POST_NOTIFICATIONS },
                        101);
                return false;
            }
        }
        return true;
    }
}
