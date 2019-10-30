package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.ui.play;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.util.Random;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.R;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.tools.Timer;

public class PlayFragment extends Fragment {

    private Button backBtn;
    private TextView textBox, timerBox;
    private EditText input;
    private String show;
    private String[] words;
    private PlayViewModel pViewModel;
    private int wordCount, pointerWords;
    public Timer timer;

    private final int TIME = 59;

    public static PlayFragment newInstance() {
        return new PlayFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_play, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getRes();
        setShowText();
        onInputChange();
        timer.execute(TIME);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timer != null) timer.cancel(true);
                pViewModel.setCurrentScore(wordCount);
                getFragmentManager().beginTransaction()
                        .replace(R.id.PlayActivity, ResultFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    public void getRes() {
        pViewModel = ViewModelProviders.of(getActivity()).get(PlayViewModel.class);
        textBox = getView().findViewById(R.id.textBox);
        input = getView().findViewById(R.id.inputText);
        timerBox = getView().findViewById(R.id.timer);
        backBtn = getView().findViewById(R.id.back_btn);

        timer = new Timer(this);
    }

    public void setShowText() {
        String[] word_src = pViewModel.words;
        words = new String[1001];
        show = "";
        int wordIdx = 1;
        int current_length = 0;
        for (int i = 1; i <= 1000; i++) {
            String now = word_src[new Random().nextInt(word_src.length)] + " ";
            if (current_length + now.length() <= 27) {
                words[wordIdx] = now;
                show += now;
                current_length += now.length();
                wordIdx++;
            }
            else {
                show += "\n" + now;
                current_length = now.length();
            }
        }
        textBox.setText(show);
    }

    public void onInputChange() {
        wordCount = 0;
        pointerWords = 1;

        input.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            public void afterTextChanged(Editable s) {
                String lastWord = s.toString();
                if(s.toString().contains(" ")){
                    input.getText().clear();
                    if (lastWord.equals(words[pointerWords])) wordCount++;
                    show = show.substring(words[pointerWords].length());
                    if (show.charAt(0) == '\n') show = show.substring(1);
                    textBox.setText(show);
                    pointerWords++;
                }
            }
        });
    }

    public void onFinishState() {
        Log.d("currentscore via mviewmodel", wordCount+"");
        pViewModel.setCurrentScore(wordCount);
        getFragmentManager().beginTransaction()
                .replace(R.id.PlayActivity, ResultFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }

    public void setTimer(int currentSec) {
        timerBox.setText(String.format("%ss", currentSec));
    }

}
