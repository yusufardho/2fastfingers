package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.ui.main.MainFragment;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.ui.main.SideBarFragment;

public class MainActivity extends AppCompatActivity {

    private static boolean flag_sideBar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main_activity);

        if (findViewById(R.id.sideBarFragment).getVisibility() == View.GONE) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.bodyFragment, MainFragment.newInstance(), null)
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.bodyFragment, MainFragment.newInstance(), null)
                    .add(R.id.sideBarFragment, SideBarFragment.newInstance(), null)
                    .commit();
        }
    }

    public void onClickSideBar(View view) {
        Button sidebarBtn = findViewById(R.id.sidebar_btn);

        if (!flag_sideBar) {
            flag_sideBar = true;
            sidebarBtn.setText("<");
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.bodyFragment, SideBarFragment.newInstance(), null)
                    .commit();
        } else {
            flag_sideBar = false;
            sidebarBtn.setText(getString(R.string.sidebar_btn));
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.bodyFragment, MainFragment.newInstance(), null)
                    .commit();
        }
    }
}