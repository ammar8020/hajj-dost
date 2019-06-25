package com.pakiboy612gmailtest.hajjdost;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent repeating_intent = new Intent(context, RepeatingActivity.class);
        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

//        Toast.makeText(context, "Receiver", Toast.LENGTH_SHORT).show();

        PendingIntent pendingIntent = PendingIntent.getActivity(context,100,
                repeating_intent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentIntent(pendingIntent)
                .setContentTitle("Today's Plan")
                .setContentText("Click here to see what you should do today")
                .setAutoCancel(true);
        notificationManager.notify(100,builder.build());



    }
}

