package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.db.dao.WordDao;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.db.database.WordRoomDatabase;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.db.entity.Word;

public class WordRepository {

    private WordDao mWordDao;
    private List<Word> mAllWords;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    public List<Word> getAllWords() {
        return mAllWords;
    }

    public void insert (Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}