package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.databinding.ActivityPlayBinding;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.services.PlayingNotificationService;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.ui.play.PlayFragment;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.ui.play.ResumeFragment;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.viewmodels.PlayViewModel;

public class PlayActivity extends AppCompatActivity {

    public ActivityPlayBinding playBinding;
    public PlayViewModel playViewModel;
    Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        playBinding = DataBindingUtil.setContentView(this, R.layout.activity_play);
        playViewModel = ViewModelProviders.of(this).get(PlayViewModel.class);

        registerReceiver(callReceiver, new IntentFilter("INCOMING_CALL_DETECTED"));

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.PlayActivity, PlayFragment.newInstance())
                    .commitNow();
        }
    }

    BroadcastReceiver callReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            clear();
            playViewModel.isFromPause = true;

            if (!playViewModel.ignoreReceiver) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.PlayActivity, ResumeFragment.newInstance())
                        .commit();
            }
        }
    };

    @Override
    public void onBackPressed() {} // disable back button while playing

    public boolean mBounded;
    public PlayingNotificationService mPNS;

    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("service > ", "disconnected");
            mBounded = false;
            mPNS = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("service > ", "connected");
            mBounded = true;
            PlayingNotificationService.LocalBinder mLocalBinder = (PlayingNotificationService.LocalBinder)service;
            mPNS = mLocalBinder.getPNSInstance();
        }
    };


    public void startService(int startTime) {

        serviceIntent = new Intent(this, PlayingNotificationService.class);
        serviceIntent.putExtra("inputExtra", String.valueOf(startTime));

        ContextCompat.startForegroundService(this, serviceIntent);
        bindService(serviceIntent, mConnection, BIND_AUTO_CREATE);
    }

    public void stopService() {
        serviceIntent = new Intent(PlayActivity.this, PlayingNotificationService.class);
        stopService(serviceIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(callReceiver);
        clear();
    }

    public void clear() {
        playViewModel.forceStopTimer();
        try {
            if (mPNS != null) mPNS.clearNotif();
            if (mBounded) {
                unbindService(mConnection);
                stopService();
            }
        } catch (Exception ignored) {}
    }
}
