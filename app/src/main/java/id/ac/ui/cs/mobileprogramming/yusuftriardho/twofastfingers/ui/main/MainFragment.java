package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.ui.main;

import android.os.Bundle;
import android.os.CountDownTimer;
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

public class MainFragment extends Fragment {

    private Button sideBarBtn, startBtn, timer, backBtn;
    private TextView title, textBox, resultBox;
    private EditText input;
    private String show;
    private String[] words;
    private MainViewModel mViewModel;
    private int wordCount, pointerWords;

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
        timer = getView().findViewById(R.id.timer);
        resultBox = getView().findViewById(R.id.resultBox);
        backBtn = getView().findViewById(R.id.back_btn);
    }

    public void isPlayState(Boolean playState) {
        int f1 = View.VISIBLE, f2 = View.GONE;

        if (playState) {
            initText();
            f1 = View.GONE;
            f2 = View.VISIBLE;
        }

        sideBarBtn.setVisibility(f1);
        startBtn.setVisibility(f1);
        title.setVisibility(f1);
        resultBox.setVisibility(f1);

        textBox.setVisibility(f2);
        input.setVisibility(f2);
        timer.setVisibility(f2);
        backBtn.setVisibility(f2);
    }

    void initText() {
        String[] word_src = mViewModel.words;
        words = new String[201];
        show = "";
        int wordIdx = 1;
        int current_length = 0;
        for (int i = 1; i <= 200; i++) {
            String now = word_src[new Random().nextInt(word_src.length)] + " ";
            if (current_length + now.length() <= 27) {
                words[wordIdx] = now;
                show += now;
                current_length += now.length();
                wordIdx++;
            }
            else {
                show += "\n";
                current_length = 0;
            }
        }
        textBox.setText(show);
    }

    void pros() {
        wordCount = 0;
        pointerWords = 1;

        input.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

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

    public void runTimer() {
        new CountDownTimer(60000, 1000) {

            public void onTick(long ms) {
                timer.setText(""+ms/1000);
            }

            public void onFinish() {
                isPlayState(false);
                resultBox.setText("Your score: " + String.valueOf(wordCount) + " word correct!");
            }
        }.start();
    }

    public void onStartClick(View view) {
        isPlayState(true);
        pros();
        runTimer();
    }

}
