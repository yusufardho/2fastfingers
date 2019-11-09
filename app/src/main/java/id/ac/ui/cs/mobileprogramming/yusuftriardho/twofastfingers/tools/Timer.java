package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.tools;

import android.os.AsyncTask;

import java.util.concurrent.TimeUnit;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.ui.play.PlayFragment;

public class Timer extends AsyncTask<Integer, Integer,  Integer> {
    private PlayFragment playFragment;

    public Timer(PlayFragment playFragment) {
        this.playFragment = playFragment;
    }

    @Override
    protected Integer doInBackground(Integer... param) {
        int time = param[0];
        while (time >= 0) {
            if (isCancelled()) {
                playFragment.setTIME(time);
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
        playFragment.setTimer(val[0]);
    }

    @Override
    protected void onPostExecute(Integer result) {
        playFragment.onFinishState();
    }
}