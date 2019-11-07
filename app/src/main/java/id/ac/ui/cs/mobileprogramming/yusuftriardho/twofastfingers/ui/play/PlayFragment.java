package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.ui.play;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.R;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.viewmodels.WordViewModel;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.databinding.FragmentPlayBinding;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.interfaces.fragments.PlayInterface;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.viewmodels.PlayViewModel;

public class PlayFragment extends Fragment implements PlayInterface {


    public FragmentPlayBinding playBinding;
    private PlayViewModel playViewModel;
    private WordViewModel wordViewModel;

    public static PlayFragment newInstance() {
        return new PlayFragment();
    }


    List<String> ls = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        playBinding = FragmentPlayBinding.inflate(inflater, container, false);
        playViewModel = ViewModelProviders.of(getActivity()).get(PlayViewModel.class);
        wordViewModel = ViewModelProviders.of(getActivity()).get(WordViewModel.class);

//        wordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
//            @Override
//            public void onChanged(@Nullable final List<Word> word) {
//                for(int i = 0; i < word.size(); i++) {
//                    Log.i(">>>>>>", word.get(i).getWord()+ "-" + word.size());
//                }
//                if (word.size() == 1) {
//                    playViewModel.setWords(word);
//                    playViewModel.initPlay(getResources().getBoolean(R.bool.isTablet));
//                }
//            }
//        });

        Log.i(">>>>", Locale.getDefault().getDisplayLanguage());

        playViewModel.setWords(wordViewModel.getAllWords());
        playViewModel.setTimer(this);
        playViewModel.initPlay(getResources().getBoolean(R.bool.isTablet));

        playBinding.setPlayViewModel(playViewModel);
        playBinding.setLifecycleOwner(getActivity());
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
