package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.db.dao.VersionHistoryDao;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.db.database.VersionHistoryRoomDatabase;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.db.entity.VersionHistory;

public class VersionHistoryRepository {

    private VersionHistoryDao versionHistoryDao;
    private List<VersionHistory> allVersionHistory;

    public VersionHistoryRepository(Application application) {
        VersionHistoryRoomDatabase db = VersionHistoryRoomDatabase.getDatabase(application);
        versionHistoryDao = db.versionHistoryDao();
        allVersionHistory = versionHistoryDao.getAllVersionHistory();
    }

    public List<VersionHistory> getAllVersionHistory() {
        return allVersionHistory;
    }

    public void insert (VersionHistory versionHistory) {
        new insertAsyncTask(versionHistoryDao).execute(versionHistory);
    }

    private static class insertAsyncTask extends AsyncTask<VersionHistory, Void, Void> {

        private VersionHistoryDao mAsyncTaskDao;

        insertAsyncTask(VersionHistoryDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final VersionHistory... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
