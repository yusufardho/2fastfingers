package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.ui.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.LeaderboardActivity;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.R;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.databinding.FragmentSidebarBinding;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.interfaces.fragments.SideBarInterface;

public class SideBarFragment extends Fragment implements SideBarInterface {

    public FragmentSidebarBinding sidebarBinding;

    public static SideBarFragment newInstance() {
        return new SideBarFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        sidebarBinding = FragmentSidebarBinding.inflate(inflater, container, false);
        sidebarBinding.setLifecycleOwner(getActivity());
        sidebarBinding.setSideBarInterface(this);

        return sidebarBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().setBackgroundColor(Color.parseColor("#FFFFFF"));
    }

    public void onClickAbout() {
        getFragmentManager().beginTransaction()
                .replace(R.id.bodyFragment, AboutFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }

    public void onClickLeaderboard() {
        Intent intent = new Intent(getActivity(), LeaderboardActivity.class);
        startActivity(intent);
    }
}
