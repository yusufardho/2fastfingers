package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.databinding.ActivityPlayBinding;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.ui.play.PlayFragment;

public class PlayActivity extends AppCompatActivity {

    public ActivityPlayBinding playBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        playBinding = DataBindingUtil.setContentView(this, R.layout.activity_play);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.PlayActivity, PlayFragment.newInstance())
                    .commitNow();
        }
    }

    @Override
    public void onBackPressed() {} // disable back button while playing
}
