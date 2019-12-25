package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.databinding.ActivityMainBinding;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.interfaces.MainInterface;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.ui.main.MainFragment;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.ui.main.SideBarFragment;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.viewmodels.MainViewModel;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.viewmodels.PlayViewModel;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.viewmodels.WordViewModel;

public class MainActivity extends AppCompatActivity implements MainInterface {

    private static boolean flag_sideBar = false;
    private MainViewModel mainViewModel;
    private WordViewModel wordViewModel;
    private PlayViewModel playViewModel;
    public ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        wordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        playViewModel = ViewModelProviders.of(this).get(PlayViewModel.class);

        mainViewModel.setSideBarText(getString(R.string.sidebar_btn));
        mainBinding.setMainViewModel(mainViewModel);
        mainBinding.setMainInterface(this);

        playViewModel.forceStopTimer();

        boolean isTablet = getResources().getBoolean(R.bool.isTablet);
        if (!isTablet) {
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

    public void onClickSideBar() {
        if (!flag_sideBar) {
            flag_sideBar = true;
            mainViewModel.setSideBarText(getString(R.string.back_btn));
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.bodyFragment, SideBarFragment.newInstance(), null)
                    .commit();
        } else {
            flag_sideBar = false;
            mainViewModel.setSideBarText(getString(R.string.sidebar_btn));
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.bodyFragment, MainFragment.newInstance(), null)
                    .commit();
        }
        mainBinding.setMainViewModel(mainViewModel);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        startPlay();
    }

    public void startPlay() {
        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
    }
}