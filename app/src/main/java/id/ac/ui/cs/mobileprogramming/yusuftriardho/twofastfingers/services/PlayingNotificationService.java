package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.R;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.tools.Timer;

import static id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.App.CHANNEL_ID;

public class PlayingNotificationService extends Service {

    private final int NOTIF_ID = 1;
    NotificationManager mNotificationManager;
    Timer timer;
    IBinder mBinder = new LocalBinder();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public class LocalBinder extends Binder {
        public PlayingNotificationService getPNSInstance() {
            return PlayingNotificationService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int timeStart = Integer.parseInt(intent.getStringExtra("inputExtra"));

        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        startForeground(NOTIF_ID, getPlayingNotification(""));

        timer = new Timer(this);
        timer.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, timeStart);

        return START_NOT_STICKY;
    }

    private Notification getPlayingNotification(String text) {
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(getString(R.string.is_running))
                .setContentText(text + " " + getString(R.string.second_left))
                .setSmallIcon(R.drawable.logo_round)
                .build();

        return notification;
    }

    public void updateNotification(String text) {
        Notification notification = getPlayingNotification(text);
        mNotificationManager.notify(NOTIF_ID, notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public void clearNotif() {
        mNotificationManager.cancel(NOTIF_ID);
        if (timer != null) {
            timer.cancel(true);
        }
    }

}
