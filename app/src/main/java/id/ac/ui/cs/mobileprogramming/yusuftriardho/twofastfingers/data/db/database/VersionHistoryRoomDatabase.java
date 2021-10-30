package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.db.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.db.dao.VersionHistoryDao;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.db.entity.VersionHistory;


@Database(entities = {VersionHistory.class}, version = 1, exportSchema = false)
public abstract class VersionHistoryRoomDatabase extends RoomDatabase {

    public abstract VersionHistoryDao versionHistoryDao();
    private static VersionHistoryRoomDatabase INSTANCE;

    public static VersionHistoryRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (VersionHistoryRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            VersionHistoryRoomDatabase.class, "version_history_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final VersionHistoryDao mDao;
        String[] version_history = {"v1.0 1/11/19", "v1.1 6/11/19", "v1.2 30/10/21"};
        PopulateDbAsync(VersionHistoryRoomDatabase db) {
            mDao = db.versionHistoryDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();

            for(String vh: version_history) {
                String[] tmp = vh.split(" ");
                mDao.insert(new VersionHistory(tmp[0], tmp[1]));
            }

            return null;
        }
    }
}