package com.example.unseenmessagesfha.Services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

public class BroadcastRestartService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Broadcaster", "Service created");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(new Intent(context,NotificationService.class));
        }
        else
            context.startService(new Intent(context,NotificationService.class));
    }
}
