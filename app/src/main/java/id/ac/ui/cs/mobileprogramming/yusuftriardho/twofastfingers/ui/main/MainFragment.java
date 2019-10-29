package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.ui.main;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.Timer;

public class MainFragment extends Fragment {

    private Button sideBarBtn, startBtn, backBtn;
    private TextView title, textBox, resultBox, timerBox;
    private EditText input;
    private String show;
    private String[] words;
    private MainViewModel mViewModel;
    private int wordCount, pointerWords;
    private Timer timer;

    private final int TIME = 59;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getRes();

        sideBarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, SideBarFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timer != null) timer.cancel(true);
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, MainFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onStartClick(view);
            }
        });
    }

    public void getRes() {
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        sideBarBtn = getView().findViewById(R.id.sidebar_btn);
        startBtn = getView().findViewById(R.id.start_btn);
        title = getView().findViewById(R.id.title);
        textBox = getView().findViewById(R.id.textBox);
        input = getView().findViewById(R.id.inputText);
        timerBox = getView().findViewById(R.id.timer);
        resultBox = getView().findViewById(R.id.resultBox);
        backBtn = getView().findViewById(R.id.back_btn);

        timer = new Timer(this);
    }

    public void isPlayState(Boolean playState) {
        int f1 = View.VISIBLE, f2 = View.GONE;

        if (playState) {
            initText();
            input.getText().clear();
            f1 = View.GONE; // 8
            f2 = View.VISIBLE; // 0
        } else {
            resultBox.setText("Your score: " + String.valueOf(wordCount) + " word correct!");

        }

        sideBarBtn.setVisibility(f1);
        startBtn.setVisibility(f1);
        title.setVisibility(f1);
        resultBox.setVisibility(f1);

        textBox.setVisibility(f2);
        input.setVisibility(f2);
        timerBox.setVisibility(f2);
        backBtn.setVisibility(f2);
    }

    void initText() {
        String[] word_src = mViewModel.words;
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

    public void pros() {
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

    public void setTimer(int currentSec) {
        timerBox.setText(String.format("%ss", currentSec));
    }

    public void onStartClick(View view) {
        isPlayState(true);
        pros();
        timer.execute(TIME);

    }

}
