package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers;

import android.os.AsyncTask;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.ui.main.MainFragment;

public class Timer extends AsyncTask<Integer, Integer, String> {
    private MainFragment mainFragment;

    public Timer(MainFragment mainFragment) {
        this.mainFragment = mainFragment;
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
        mainFragment.setTimer(val[0]);
        Log.d("onProgressUpdate", "running");
    }

    @Override
    protected void onPostExecute(String result) {
        mainFragment.isPlayState(false);
        Log.d("onPostExecute", "running");
    }
}