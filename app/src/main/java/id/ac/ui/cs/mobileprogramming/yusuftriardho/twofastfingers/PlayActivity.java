package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.databinding.ActivityPlayBinding;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.ui.play.PlayFragment;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.ui.play.ResumeFragment;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.viewmodels.PlayViewModel;

public class PlayActivity extends AppCompatActivity {

    public ActivityPlayBinding playBinding;
    public PlayViewModel playViewModel;

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
            playViewModel.forceStopTimer();
            playViewModel.setIsFromPause(true);

            if (!playViewModel.ignoreReceiver) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.PlayActivity, ResumeFragment.newInstance())
                        .commit();
            }
        }
    };

    @Override
    public void onBackPressed() {} // disable back button while playing
}
