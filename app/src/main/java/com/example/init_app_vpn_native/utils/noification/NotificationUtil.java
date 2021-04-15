package com.example.init_app_vpn_native.utils.noification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.init_app_vpn_native.BuildConfig;
import com.example.init_app_vpn_native.R;

public class NotificationUtil {
    private static final String CHANNEL_ID = "" + BuildConfig.APPLICATION_ID;
    private static final int NOTIFICATION_ID = 12;
    public static int NOTIFICATION_REQUEST_CODE = 1;

    private static NotificationCompat.Builder builder(Context context, String title, String content, int priority) {
        NotificationCompat.Builder build;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            build = new NotificationCompat.Builder(context, CHANNEL_ID);
        else build = new NotificationCompat.Builder(context);
        build.setSmallIcon(R.mipmap.ic_launcher).setContentTitle(title).setContentText(content).setPriority(priority);
        return build;
    }

    private static NotificationCompat.Builder builder(Context context, String title, String content, int priority, Bitmap largeIcon) {
        return builder(context, title, content, priority).setLargeIcon(largeIcon);
    }

    private static NotificationCompat.Builder builder(Context context, String title, String content, int priority, Action... actions) {
        NotificationCompat.Builder build = builder(context, title, content, priority);
        for (Action a : actions) {
            build.addAction(a.icon, a.actionName, a.pendingIntent);
        }
        return build;
    }

    private static NotificationCompat.Builder builder(Context context, String title, String content, int priority, Bitmap largeIcon, Action... actions) {
        NotificationCompat.Builder build = builder(context, title, content, priority).setLargeIcon(largeIcon);
        for (Action a : actions) {
            build.addAction(a.icon, a.actionName, a.pendingIntent);
        }
        return build;
    }

    public static void showNotification(Context context, String title, String content, int priority, Class<?> classPending) {
        NotificationManagerCompat notificationManager;
        notificationManager = NotificationManagerCompat.from(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = CHANNEL_ID;
            String description = CHANNEL_ID;
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            notificationManager.createNotificationChannel(channel);
        }
        Intent intent = new Intent(context, classPending);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, NOTIFICATION_REQUEST_CODE, intent, 0);
        notificationManager.notify(NOTIFICATION_ID, builder(context, title, content, priority).setContentIntent(pendingIntent).build());
    }

    public static void showNotification(Context context, String title, String content, int priority, Bitmap largeIcon, Class<?> classPending) {
        NotificationManagerCompat notificationManager;
        notificationManager = NotificationManagerCompat.from(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = CHANNEL_ID;
            String description = CHANNEL_ID;
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            notificationManager.createNotificationChannel(channel);
        }
        Intent intent = new Intent(context, classPending);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, NOTIFICATION_REQUEST_CODE, intent, 0);
        notificationManager.notify(NOTIFICATION_ID, builder(context, title, content, priority, largeIcon).setContentIntent(pendingIntent).build());
    }
//    public static void showNotification(Context context, String title, String content, int priority, Class<?> classPending, Action... actions) {
//        NotificationManagerCompat notificationManager;
//        notificationManager = NotificationManagerCompat.from(context);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            CharSequence name = CHANNEL_ID;
//            String description = CHANNEL_ID;
//            int importance = NotificationManager.IMPORTANCE_DEFAULT;
//            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
//            channel.setDescription(description);
//            notificationManager.createNotificationChannel(channel);
//        }
//        Intent intent = new Intent(context, classPending);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, NOTIFICATION_REQUEST_CODE, intent, 0);
//        notificationManager.notify(NOTIFICATION_ID, builder(context, title, content, priority, actions).setContentIntent(pendingIntent).build());
//    }


//    public static void showNotification(Context context, String title, String content, int priority, Class<?> classPending, Bitmap largeIcon,String ... action) {
//        NotificationManagerCompat notificationManager;
//        notificationManager = NotificationManagerCompat.from(context);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            CharSequence name = CHANNEL_ID;
//            String description = CHANNEL_ID;
//            int importance = NotificationManager.IMPORTANCE_DEFAULT;
//            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
//            channel.setDescription(description);
//            notificationManager.createNotificationChannel(channel);
//        }
//        Intent intent = new Intent(context, classPending);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, NOTIFICATION_REQUEST_CODE, intent, 0);
//        notificationManager.notify(NOTIFICATION_ID, builder(context, title, content, priority, largeIcon, actions).setContentIntent(pendingIntent).build());
//    }

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
