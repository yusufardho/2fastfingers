package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.tools;

import android.os.AsyncTask;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.services.PlayingNotificationService;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.ui.play.PlayFragment;

public class Timer extends AsyncTask<Integer, Integer,  Integer> {
    private PlayFragment playFragment;
    private PlayingNotificationService playingNotification;

    public Timer(PlayingNotificationService pns) {
        this.playingNotification = pns;
    }

    public Timer(PlayFragment playFragment) {
        this.playFragment = playFragment;
    }

    @Override
    protected Integer doInBackground(Integer... param) {
        int time = param[0];
        while (time >= 0) {
            if (isCancelled()) {
                if (playFragment != null) playFragment.setTIME(time);
                if (playingNotification != null) playingNotification.stopSelf();
                break;
            }
            try {
                TimeUnit.SECONDS.sleep(1);
                publishProgress(time);
            } catch (InterruptedException ignored) {}
            time--;
        }
        return time;
    }

    @Override
    protected void onProgressUpdate(Integer... val) {
        if (playFragment != null) {
            playFragment.setTimer(val[0]);
        }
        if (playingNotification != null) {
            playingNotification.updateNotification(String.valueOf(val[0]));
        }
    }

    @Override
    protected void onPostExecute(Integer result) {
        if (playFragment != null) playFragment.onFinishState();
        if (playingNotification != null) playingNotification.stopSelf();
    }
}