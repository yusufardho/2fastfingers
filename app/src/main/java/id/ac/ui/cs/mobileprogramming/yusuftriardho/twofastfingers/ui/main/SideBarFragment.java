package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.ui.main;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.util.Locale;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.R;

public class SideBarFragment extends Fragment {

    private MainViewModel mViewModel;

    public static SideBarFragment newInstance() {
        return new SideBarFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sidebar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button backBtn = getView().findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        Button aboutBtn = getView().findViewById(R.id.about_btn);
        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.MainActivity, AboutFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });

        Button langBtn = getView().findViewById(R.id.language_toggle);
        langBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
                if (mViewModel.language.equals("en")) {
                    setLocale("in");
                    mViewModel.language = "in";
                } else {
                    setLocale("");
                    mViewModel.language = "en";
                }
                getFragmentManager().beginTransaction()
                        .replace(R.id.MainActivity, MainFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });

//        Button leaderboardBtn = getView().findViewById(R.id.leaderboard_btn);
//        leaderboardBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getFragmentManager().beginTransaction()
//                        .replace(R.id.container, LeaderBoard.newInstance())
//                        .addToBackStack(null)
//                        .commit();
//            }
//        });
    }

    public void setLocale(String localCode) {
        Resources activityRes = getResources();
        Configuration activityConf = activityRes.getConfiguration();
        Locale newLocale = new Locale(localCode);
        activityConf.setLocale(newLocale);
        activityRes.updateConfiguration(activityConf, activityRes.getDisplayMetrics());

        Resources applicationRes = getActivity().getApplicationContext().getResources();
        Configuration applicationConf = applicationRes.getConfiguration();
        applicationConf.setLocale(newLocale);
        applicationRes.updateConfiguration(applicationConf,
                applicationRes.getDisplayMetrics());
    }
}
