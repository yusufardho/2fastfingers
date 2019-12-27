package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.viewmodels;

import android.graphics.Color;

import androidx.lifecycle.ViewModel;

import java.util.Collections;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.db.entity.Word;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.tools.Timer;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.ui.play.PlayFragment;

public class PlayViewModel extends ViewModel {

    private String timerBox, inputText, resultText;
    private CharSequence displayText;
    private int correctWord;
    private List<Word> words;
    public Timer timer;
    public int TIME;
    public boolean isFromPause, ignoreReceiver;

    public void setCorrectWord(int cnt) { this.correctWord = cnt; }
    public int getCorrectWord() { return this.correctWord; }

    public void setTimerBox(String time) { this.timerBox = time; }
    public String getTimerBox() { return this.timerBox; }

    public void setDisplayText(CharSequence txt) { this.displayText = txt; }
    public CharSequence getDisplayText() { return this.displayText; }

    public void setInputText(String txt) { this.inputText = txt; }
    public String getInputText() { return this.inputText; }

    public void setTimer(PlayFragment playFragment) { this.timer = new Timer(playFragment); }
    public Timer getTimer() { return this.timer; }

    public void setResultText(String str) { this.resultText = str; }
    public String getResultText() { return this.resultText; }

    public void setTIME(int time) { this.TIME = time; }
    public int getTIME() {return this.TIME; }

    public void setWords(List<Word> words) {
        // TO DO

        Collections.shuffle(words);
        this.words = words;
    }
    public List<Word> getWords() { return this.words; }

    public final int GREEN = Color.parseColor("#50C350");
    public final int RED = Color.RED;

    public void forceStopTimer() {
        if (timer != null) {
            timer.cancel(true);
        }
    }

    public void initPlay(boolean isTablet) {
        isFromPause = false;
        inputText = "";
        correctWord = 0;
        initDisplayWords(isTablet);

        timer.execute(TIME);
    }

    public void initDisplayWords(boolean isTablet) {
        int THRESHOLD = (isTablet ? 80 : 35);
        displayText = "";
        int current_length = 0;
        for (int i = 0; i < words.size(); i++) {
            String now = words.get(i).getWord() + " ";
            if (current_length + now.length() > THRESHOLD) {
                displayText = displayText + "\n";
                current_length = now.length();
            }
            displayText = displayText + now;
            current_length += now.length();
        }
    }
}
