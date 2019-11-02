package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.ui.play.PlayFragment;

public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.play_activity);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.PlayActivity, PlayFragment.newInstance())
                    .commitNow();
        }
    }

    @Override
    public void onBackPressed() {
    }
}
