package com.commonlibrary.util;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.commonlibrary.R;


/**
 * Created by    on 8/8/2016.
 */

public class NotificationsUtils
{
    private static NotificationsUtils notificationsUtils;

    public static NotificationsUtils getInstance()
    {
        if (notificationsUtils == null)
            return new NotificationsUtils();
        return notificationsUtils;
    }


    /***
     * @param message Notification message to display.
     * @param context Context of the current activity.
     * @param screen Activity class to show the screen by clicking on notification.
     * @param id Notification ID.
     * @return
     */
    public NotificationManager showNotification(String message, Context context, Activity screen, int id) {
        try
        {
            StringBuilder sbNotification = new StringBuilder();
            sbNotification.append(context.getString(R.string.app_name));
            sbNotification.append(" Notification");

            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context)
                            //.setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle(sbNotification.toString())
                            .setAutoCancel(true)
                            .setVibrate(new long[] { 100, 250, 100, 500})
                            .setContentText(message);

// Creates an explicit intent for an Activity in your app
            Intent resultIntent = new Intent(context, screen.getClass());
            PendingIntent contentIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(contentIntent);
            NotificationManager mNotificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
            mNotificationManager.notify(id, mBuilder.build());
            return mNotificationManager;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
