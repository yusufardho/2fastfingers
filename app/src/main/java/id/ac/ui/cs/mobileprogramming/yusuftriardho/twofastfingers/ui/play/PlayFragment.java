package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.ui.play;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
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

import java.util.ArrayList;
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
    private int wordCount, pointerWords, charPassed, THRESHOLD;
    private ArrayList<DataColor> listColor;
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
        setRetainInstance(true);
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
        Intent intent = getActivity().getIntent();
        pViewModel.setWordsLang(intent.getStringExtra("lang"));
        String[] word_src = pViewModel.words;
        if (textBox.getText().charAt(0) == 'p') {
            THRESHOLD = 50;
        } else {
            THRESHOLD = 27;
        }
        words = new String[1001];
        show = "";
        int current_length = 0;
        for (int i = 1; i <= 1000; i++) {
            String now = word_src[new Random().nextInt(word_src.length)] + " ";
            words[i] = now;
            if (current_length + now.length() <= THRESHOLD) {
                show += now;
                current_length += now.length();
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
        charPassed = 0;
        listColor = new ArrayList<>();
        listColor.add(new DataColor(0,words[pointerWords].length()-1, Color.YELLOW));
        highlightTextPart(show, listColor);

        input.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            public void afterTextChanged(Editable s) {
                String lastWord = s.toString();
                if(s.toString().contains(" ")){
                    input.getText().clear();
                    charPassed += words[pointerWords].length();
                    if (lastWord.equals(words[pointerWords])) {
                        listColor.get(listColor.size()-1).color = Color.parseColor("#45B8AC");
                        wordCount++;
                    } else {
                        listColor.get(listColor.size()-1).color = Color.parseColor("#F7CAC9");
                    }
                    if (show.charAt(charPassed) == '\n') {
                        listColor.clear();
                        show = show.substring(charPassed+1);
                        textBox.setText(show);
                        charPassed = 0;
                        listColor.add(new DataColor(0,words[pointerWords+1].length()-1, Color.YELLOW));
                    } else {
                        listColor.add(new DataColor(charPassed, charPassed+words[pointerWords+1].length()-1, Color.YELLOW));
                    }

                    highlightTextPart(show, listColor);
                    pointerWords++;

                }
            }
        });
    }

    public void onFinishState() {
        pViewModel.setCurrentScore(wordCount);
        getFragmentManager().beginTransaction()
                .replace(R.id.PlayActivity, ResultFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }

    public void setTimer(int currentSec) {
        timerBox.setText(String.format(getString(R.string.timer_display), currentSec));
    }

    public void highlightTextPart(String fullText, ArrayList<DataColor> arrList) {
        Spannable spannable = new SpannableString(fullText);

        for (int i = 0; i < arrList.size(); i++) {
            DataColor current = arrList.get(i);
            spannable.setSpan(new BackgroundColorSpan(current.color), current.first, current.second, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        textBox.setText(spannable);
    }

    class DataColor {
        int first, second, color;

        public DataColor(int f, int s, int c) {
            this.first = f;
            this.second = s;
            this.color = c;
        }
    }

}
