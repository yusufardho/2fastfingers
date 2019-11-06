package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.viewmodels;

import android.graphics.Color;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Random;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.tools.Timer;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.ui.play.PlayFragment;

public class PlayViewModel extends ViewModel {

    private final String SRC = "a ability able about above accept according account across act action activity actually add address administration admit adult affect after again against age agency agent ago agree agreement ahead air all allow almost alone along already also although always American among amount analysis and animal another answer any anyone anything appear apply approach area argue arm around arrive art article artist as ask assume at attack attention attorney audience author authority available avoid away baby back bad bag ball bank bar base be beat beautiful because become bed before begin behavior behind believe benefit best better between beyond big bill billion bit black blood blue board body book born both box boy break bring brother budget build building business but buy by call camera campaign can cancer candidate capital car card care career carry case catch cause cell center central century certain certainly chair challenge chance change character charge check child choice choose church citizen city civil claim class clear clearly close coach cold collection college color come commercial common community company compare computer concern condition conference Congress consider consumer contain continue control cost could country couple course court cover create crime cultural culture cup current customer cut dark data daughter day dead deal death debate decade decide decision deep defense degree Democrat democratic describe design despite detail determine develop development die difference different difficult dinner direction director discover discuss discussion disease do doctor dog door down draw dream drive drop drug during each early east easy eat economic economy edge education effect effort eight either election else employee end energy enjoy enough enter entire environment environmental especially establish even evening event ever every everybody everyone everything evidence exactly example executive exist expect experience expert explain eye face fact factor fail fall family far fast father fear federal feel feeling few field fight figure fill film final finally financial find fine finger finish fire firm first fish five floor fly focus follow food foot for force foreign forget form former forward four free friend from front full fund future game garden gas general generation get girl give glass go goal good government great green ground group grow growth guess gun guy hair half hand hang happen happy hard have he head health hear heart heat heavy help her here herself high him himself his history hit hold home hope hospital hot hotel hour house how however huge human hundred husband I idea identify if image imagine impact important improve in include including increase indeed indicate individual industry information inside instead institution interest interesting international interview into investment involve issue it item its itself job join just keep key kid kill kind kitchen know knowledge land language large last late later laugh law lawyer lay lead leader learn least leave left leg legal less let letter level lie life light like likely line list listen little live local long look lose loss lot love low machine magazine main maintain major majority make man manage management manager many market marriage material matter may maybe me mean measure media medical meet meeting member memory mention message method middle might military million mind minute miss mission model modern moment money month more morning most mother mouth move movement movie Mr Mrs much music must my myself name nation national natural nature near nearly necessary need network never new news newspaper next nice night no none nor north not note nothing notice now n\'t number occur of off offer office officer official often oh oil ok old on once one only onto open operation opportunity option or order organization other others our out outside over own owner page pain painting paper parent part participant particular particularly partner party pass past patient pattern pay peace people per perform performance perhaps period person personal phone physical pick picture piece place plan plant play player PM point police policy political politics poor popular population position positive possible power practice prepare present president pressure pretty prevent price private probably problem process produce product production professional professor program project property protect prove provide public pull purpose push put quality question quickly quite race radio raise range rate rather reach read ready real reality realize really reason receive recent recently recognize record red reduce reflect region relate relationship religious remain remember remove report represent Republican require research resource respond response responsibility rest result return reveal rich right rise risk road rock role room rule run safe same save say scene school science scientist score sea season seat second section security see seek seem sell send senior sense series serious serve service set seven several sex sexual shake share she shoot short shot should shoulder show side sign significant similar simple simply since sing single sister sit site situation six size skill skin small smile so social society soldier some somebody someone something sometimes son song soon sort sound source south southern space speak special specific speech spend sport spring staff stage stand standard star start state statement station stay step still stock stop store story strategy street strong structure student study stuff style subject success successful such suddenly suffer suggest summer support sure surface system table take talk task tax teach teacher team technology television tell ten tend term test than thank that the their them themselves then theory there these they thing think third this those though thought thousand threat three through throughout throw thus time to today together tonight too top total tough toward town trade traditional training travel treat treatment tree trial trip trouble true truth try turn TV two type under understand unit until up upon us use usually value various very victim view violence visit voice vote wait walk wall want war watch water way we weapon wear week weight well west western what whatever when where whether which while white who whole whom whose why wide wife will win wind window wish with within without woman wonder word work worker world worry would write writer wrong yard yeah year yes yet you young your yourself";
    private final int TIME = 59;
    public String[] words, selectedWords;
    private String timerBox, inputText, resultText;
    private CharSequence displayText;
    private int correctWord;
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

        listColor.add(new DataColor(0,selectedWords[0].length()-1, Color.BLACK));
        highlightTextPart(displayText, listColor);

        timer.execute(TIME);
    }

    public void initDisplayWords(boolean isTablet) {
        int THRESHOLD = (isTablet ? 50 : 35);
        selectedWords = new String[1000];
        words = SRC.split(" ");

        displayText = "";
        int current_length = 0;
        for (int i = 0; i < 1000; i++) {
            String now = words[new Random().nextInt(words.length)] + " ";
            selectedWords[i] = now;
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
            // clear input box
            inputText = "";

            charPassed += selectedWords[pointerSelectedWords].length();

            // set color
            if (lastWord.equals(selectedWords[pointerSelectedWords])) {
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
                listColor.add(new DataColor(0,selectedWords[pointerSelectedWords+1].length()-1, Color.BLACK));
            } else {
                listColor.add(new DataColor(charPassed, charPassed+selectedWords[pointerSelectedWords+1].length()-1, Color.BLACK));
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
