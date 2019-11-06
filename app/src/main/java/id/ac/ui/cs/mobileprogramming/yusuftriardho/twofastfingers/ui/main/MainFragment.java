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

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.PlayActivity;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.databinding.FragmentMainBinding;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.interfaces.fragments.MainInterface;

public class MainFragment extends Fragment implements MainInterface {

    public FragmentMainBinding mainBinding;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainBinding = FragmentMainBinding.inflate(inflater, container, false);
        mainBinding.setLifecycleOwner(getActivity());
        mainBinding.setMainInterface(this);

        return mainBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().setBackgroundColor(Color.parseColor("#03A9F4"));
    }

    public void onClickStart() {
        Intent intent = new Intent(getActivity(), PlayActivity.class);
        startActivity(intent);
    }
}
