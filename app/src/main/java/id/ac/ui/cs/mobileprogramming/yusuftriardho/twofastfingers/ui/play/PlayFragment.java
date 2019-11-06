package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.ui.play;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.R;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.databinding.FragmentPlayBinding;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.interfaces.fragments.PlayInterface;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.viewmodels.PlayViewModel;

public class PlayFragment extends Fragment implements PlayInterface {

    public FragmentPlayBinding playBinding;
    private PlayViewModel playViewModel;

    public static PlayFragment newInstance() {
        return new PlayFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        playBinding = FragmentPlayBinding.inflate(inflater, container, false);
        playViewModel = ViewModelProviders.of(getActivity()).get(PlayViewModel.class);

        playViewModel.setTimer(this);
        playViewModel.initPlay(getResources().getBoolean(R.bool.isTablet));

        playBinding.setPlayViewModel(playViewModel);
//        playBinding.setLifecycleOwner(getActivity());
        playBinding.setPlayInterface(this);

        return playBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRetainInstance(true);
    }

    public void onClickExit() {
        if (playViewModel.getTimer() != null) {
            playViewModel.getTimer().cancel(true);
        }
        onFinishState();
    }


    public void onFinishState() {
        playViewModel.setCorrectWord(playViewModel.getCorrectWord());
        getFragmentManager().beginTransaction()
                .replace(R.id.PlayActivity, ResultFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }

    public void setTimer(int currentSec) {
        playViewModel.setTimerBox(String.format(getString(R.string.timer_display), currentSec));
        playBinding.setPlayViewModel(playViewModel);
    }
}
