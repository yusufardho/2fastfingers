package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.databinding.FragmentAboutBinding;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.viewmodels.MainViewModel;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.viewmodels.VersionHistoryViewModel;

public class AboutFragment extends Fragment {

    public FragmentAboutBinding aboutBinding;
    private MainViewModel mainViewModel;
    private VersionHistoryViewModel versionHistoryViewModel;

    public static AboutFragment newInstance() {
        return new AboutFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        aboutBinding = FragmentAboutBinding.inflate(inflater, container, false);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        versionHistoryViewModel = ViewModelProviders.of(this).get(VersionHistoryViewModel.class);

        aboutBinding.setMainViewModel(mainViewModel);

        return aboutBinding.getRoot();
    }
}
