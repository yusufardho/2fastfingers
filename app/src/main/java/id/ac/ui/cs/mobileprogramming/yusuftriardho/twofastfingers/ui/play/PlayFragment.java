package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.ui.play;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.R;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.db.entity.Word;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.databinding.FragmentPlayBinding;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.interfaces.fragments.PlayInterface;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.viewmodels.PlayViewModel;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.viewmodels.WordViewModel;

public class PlayFragment extends Fragment implements PlayInterface {


    private FragmentPlayBinding playBinding;
    public PlayViewModel playViewModel;
    private WordViewModel wordViewModel;

    public static PlayFragment newInstance() {
        return new PlayFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        playBinding = FragmentPlayBinding.inflate(inflater, container, false);
        playViewModel = ViewModelProviders.of(getActivity()).get(PlayViewModel.class);
        wordViewModel = ViewModelProviders.of(getActivity()).get(WordViewModel.class);

        playViewModel.ignoreReceiver = false;
        playViewModel.forceStopTimer();
        playViewModel.setWords(wordViewModel.getAllWords());
        playViewModel.setTimer(this);
        if (playViewModel.getTIME() == 0) playViewModel.isFromPause = false;
        if (!playViewModel.isFromPause) playViewModel.setTIME(59);
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
        input = getView().findViewById(R.id.inputText);
        textBox = getView().findViewById(R.id.textBox);
        onInputChange();
    }

    public void onClickExit() {
        playViewModel.forceStopTimer();
        onFinishState();
    }

    public void onClickRetry() {
        playViewModel.forceStopTimer();
        getFragmentManager().beginTransaction()
                .replace(R.id.PlayActivity, PlayFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }


    public void onFinishState() {
        playViewModel.setCorrectWord(playViewModel.getCorrectWord());
        playViewModel.isFromPause = false;
        getFragmentManager().beginTransaction()
                .replace(R.id.PlayActivity, ResultFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }

    public void setTimer(int currentSec) {
        playViewModel.setTimerBox(String.format(getString(R.string.timer_display), currentSec));
        playBinding.setPlayViewModel(playViewModel);
    }

    public void setTIME(int time) {
        if (time < 0) playViewModel.setTIME(59);
        else playViewModel.setTIME(time);
    }

    private EditText input;
    private TextView textBox;
    private int pointerSelectedWords, charPassed;
    private ArrayList<DataColor> listColor;
    private String displayText;
    private List<Word> words;

    private void onInputChange() {
        displayText = String.valueOf(playViewModel.getDisplayText());
        pointerSelectedWords = 0;
        charPassed = 0;
        words = playViewModel.getWords();
        listColor = new ArrayList<>();

        listColor.add(new DataColor(0,(words.get(0).getWord()+" ").length()-1, Color.BLACK));
        highlightTextPart(displayText, listColor);

        input.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            public void afterTextChanged(Editable s) {
                String lastWord = s.toString();
                if(s.toString().contains(" ")){
                    String now = playViewModel.getWords().get(pointerSelectedWords).getWord() + " ";
                    String next = playViewModel.getWords().get(pointerSelectedWords+1).getWord() + " ";
                    charPassed += now.length();

                    // clear input box
                    input.getText().clear();

                    // set color
                    if (lastWord.equals(now)) {
                        listColor.get(listColor.size()-1).setColor(playViewModel.GREEN);
                        playViewModel.setCorrectWord(playViewModel.getCorrectWord()+1);
                    } else {
                        listColor.get(listColor.size()-1).setColor(playViewModel.RED);
                    }

                    // change line
                    if (playViewModel.getDisplayText().charAt(charPassed) == '\n') {
                        listColor.clear();
                        displayText = displayText.substring(charPassed+1);
                        textBox.setText(displayText);
                        charPassed = 0;
                        listColor.add(new DataColor(0, next.length()-1, Color.BLACK));
                    } else {
                        listColor.add(new DataColor(charPassed, charPassed+next.length()-1, Color.BLACK));
                    }

                    // highlight word
                    highlightTextPart(playViewModel.getDisplayText(), listColor);
                    pointerSelectedWords += 1;
                }
            }
        });
    }

    private void highlightTextPart(CharSequence fullText, ArrayList<DataColor> listColor) {
        Spannable spannable = new SpannableString(fullText);
        for (DataColor dataColor: listColor) {
            spannable.setSpan(new ForegroundColorSpan(dataColor.color), dataColor.left, dataColor.right, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannable.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), dataColor.left, dataColor.right, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        textBox.setText(spannable);
    }

    public class DataColor {
        int left, right, color;

        public DataColor(int left, int right, int color) {
            this.left = left;
            this.right = right;
            this.color = color;
        }

        public void setColor(int color) { this.color = color; }
    }
}
