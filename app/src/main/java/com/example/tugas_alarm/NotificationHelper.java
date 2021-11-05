package com.example.tugas_alarm;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper {
    public static final String channelID = "channelID";
    public static final String channelName = "Channel Name";
    private NotificationManager mManager;

    public NotificationHelper(Context base) { //membuat dan membangun channel notifikasi
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }

    @TargetApi(Build.VERSION_CODES.O) //mendeskripsikan channel notifikasi yang akan dibangun
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(
                channelID, channelName,
                NotificationManager.IMPORTANCE_HIGH //tingkat importance = high ( penting sekali )
        );
        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager() { //membuka izin pengaturan dari aplikasi untuk memulai service notifikasi
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }

    public NotificationCompat.Builder getChannelNotification() { //builder yang akan membuat notifikasi tampil
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle("REMINDER!")
                .setContentText("Time's Up ")
                .setSmallIcon(R.drawable.home);
    }
}
