package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.db.dao.PassedScoreDao;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.db.database.PassedScoreRoomDatabase;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.db.entity.PassedScore;

public class PassedScoreRepository {

    private PassedScoreDao mPassedScoreDao;
    private LiveData<List<PassedScore>> mAllPassedScores;

    PassedScoreRepository(Application application) {
        PassedScoreRoomDatabase db = PassedScoreRoomDatabase.getDatabase(application);
        mPassedScoreDao = db.passedScoreDao();
        mAllPassedScores = mPassedScoreDao.getAllPassedScores();
    }

    LiveData<List<PassedScore>> getAllPassedScores() {
        return mAllPassedScores;
    }

    public void insert (PassedScore passedScore) {
        new insertAsyncTask(mPassedScoreDao).execute(passedScore);
    }

    private static class insertAsyncTask extends AsyncTask<PassedScore, Void, Void> {

        private PassedScoreDao mAsyncTaskDao;

        insertAsyncTask(PassedScoreDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final PassedScore... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}