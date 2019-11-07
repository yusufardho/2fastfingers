package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.WordRepository;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.db.entity.Word;

public class WordViewModel extends AndroidViewModel {

    private WordRepository mRepository;
    private List<Word> mAllWords;

    public WordViewModel (Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    public List<Word> getAllWords() { return mAllWords; }

    public void insert(Word word) { mRepository.insert(word); }
}