package com.example.init_app_vpn_native.utils.noification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.init_app_vpn_native.BuildConfig;
import com.example.init_app_vpn_native.R;

public class NotificationUtil {
    private static final String CHANNEL_ID = "" + BuildConfig.APPLICATION_ID;
    public static int NOTIFICATION_REQUEST_CODE = 1;

    NotificationCompat.Builder builder(Context context, String title, String content, int priority) {
        NotificationCompat.Builder build;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            build = new NotificationCompat.Builder(context, CHANNEL_ID);
        else build = new NotificationCompat.Builder(context);
        build.setSmallIcon(R.mipmap.ic_launcher).setContentTitle(content).setContentText(content).setPriority(priority);
        return build;
    }

    NotificationCompat.Builder builder(Context context, String title, String content, int priority, Bitmap largeIcon) {
        return builder(context, title, content, priority).setLargeIcon(largeIcon);
    }

    NotificationCompat.Builder builder(Context context, String title, String content, int priority, Action... actions) {
        NotificationCompat.Builder build = builder(context, title, content, priority);
        for (Action a : actions) {
            build.addAction(a.icon, a.actionName, a.pendingIntent);
        }
        return build;
    }

    NotificationCompat.Builder builder(Context context, String title, String content, int priority, Bitmap largeIcon, Action... actions) {
        NotificationCompat.Builder build = builder(context, title, content, priority).setLargeIcon(largeIcon);
        for (Action a : actions) {
            build.addAction(a.icon, a.actionName, a.pendingIntent);
        }
        return build;
    }

    public static void showNotification(Context context,) {
        NotificationManager notificationManager;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = CHANNEL_ID;
            String description = CHANNEL_ID;
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

    }

    public class Action {
        int icon;
        String actionName;
        PendingIntent pendingIntent;

        public Action(int icon, String actionName, PendingIntent pendingIntent) {
            this.icon = icon;
            this.actionName = actionName;
            this.pendingIntent = pendingIntent;
        }
    }
}
