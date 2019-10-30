package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.tools;

import android.os.AsyncTask;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.ui.play.PlayFragment;

public class Timer extends AsyncTask<Integer, Integer, String> {
    private PlayFragment playFragment;

    public Timer(PlayFragment playFragment) {
        this.playFragment = playFragment;
    }

    @Override
    protected String doInBackground(Integer... param) {
        int time = param[0];
        while (time >= 0) {
            if (isCancelled()) break;
            try {
                TimeUnit.SECONDS.sleep(1);
                publishProgress(time);
            } catch (InterruptedException ignored) {}
            time--;
        }
        return "thread done";
    }

    @Override
    protected void onProgressUpdate(Integer... val) {
        playFragment.setTimer(val[0]);
        Log.d("onProgressUpdate", "running");
    }

    @Override
    protected void onPostExecute(String result) {
        playFragment.onFinishState();
        Log.d("onPostExecute", "running");
    }
}