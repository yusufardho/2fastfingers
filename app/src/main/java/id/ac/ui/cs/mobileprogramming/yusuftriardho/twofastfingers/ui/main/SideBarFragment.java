package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.ui.main;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.R;

public class SideBarFragment extends Fragment {

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
        getView().setBackgroundColor(Color.parseColor("#FFFFFF"));

        Button aboutBtn = getView().findViewById(R.id.about_btn);
        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.bodyFragment, AboutFragment.newInstance())
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
}
