package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.viewmodels;

import android.graphics.Color;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.db.entity.Word;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.tools.Timer;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.ui.play.PlayFragment;

public class PlayViewModel extends ViewModel {

    private final int TIME = 59;
    private String timerBox, inputText, resultText;
    private CharSequence displayText;
    private int correctWord;
    private List<Word> words;
    public Timer timer;

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

    public void setWords(List<Word> words) {
        Collections.shuffle(words);
        this.words = words;
    }
    public List<Word> getWords() { return this.words; }

    private final int GREEN = Color.parseColor("#50C350");
    private final int RED = Color.RED;
    private int pointerSelectedWords, charPassed;
    private ArrayList<DataColor> listColor;

    public void initPlay(boolean isTablet) {
        inputText = "";
        correctWord = 0;
        initDisplayWords(isTablet);
        pointerSelectedWords = 0;
        charPassed = 0;
        listColor = new ArrayList<>();

        listColor.add(new DataColor(0,(words.get(0).getWord()+" ").length()-1, Color.BLACK));
        highlightTextPart(displayText, listColor);

        timer.execute(TIME);
    }

    public void initDisplayWords(boolean isTablet) {
        int THRESHOLD = (isTablet ? 50 : 35);
        displayText = "";
        int current_length = 0;
        for (int i = 0; i < words.size(); i++) {
//            words.get(i).setWord(words.get(i).getWord() + " ");
            String now = words.get(i).getWord() + " ";
            if (current_length + now.length() > THRESHOLD) {
                displayText = displayText + "\n";
                current_length = now.length();
            }
            displayText = displayText + now;
            current_length += now.length();
        }
    }

    public void afterInputTextChanged(Editable s) {
        String lastWord = s.toString();
        if(s.toString().contains(" ")){
            String now = words.get(pointerSelectedWords).getWord() + " ";
            String next = words.get(pointerSelectedWords+1).getWord() + " ";
            charPassed += now.length();

            // clear input box
            inputText = "";

            // set color
            if (lastWord.equals(now)) {
                listColor.get(listColor.size()-1).color = GREEN;
                correctWord += 1;
            } else {
                listColor.get(listColor.size()-1).color = RED;
            }

            // change line
            if (displayText.charAt(charPassed) == '\n') {
                listColor.clear();
                displayText = String.valueOf(displayText).substring(charPassed+1);
                charPassed = 0;
                listColor.add(new DataColor(0,next.length()-1, Color.BLACK));
            } else {
                listColor.add(new DataColor(charPassed, charPassed+next.length()-1, Color.BLACK));
            }

            // highlight word
            highlightTextPart(displayText, listColor);
            pointerSelectedWords++;
        }
    }

    public void highlightTextPart(CharSequence fullText, ArrayList<DataColor> listColor) {
        Spannable spannable = new SpannableString(fullText);
        for (DataColor dataColor: listColor) {
            spannable.setSpan(new ForegroundColorSpan(dataColor.color), dataColor.left, dataColor.right, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannable.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), dataColor.left, dataColor.right, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        displayText = spannable;
    }

    public class DataColor {
        int left, right, color;

        public DataColor(int left, int right, int color) {
            this.left = left;
            this.right = right;
            this.color = color;
        }
    }
}
