package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.db.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.db.dao.PassedScoreDao;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.db.entity.PassedScore;

@Database(entities = {PassedScore.class}, version = 1, exportSchema = false)
public abstract class PassedScoreRoomDatabase extends RoomDatabase {

    public abstract PassedScoreDao passedScoreDao();
    private static PassedScoreRoomDatabase INSTANCE;

    public static PassedScoreRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PassedScoreRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PassedScoreRoomDatabase.class, "passed_score_table")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Override the onOpen method to populate the database.
     * For this sample, we clear the database every time it is created or opened.
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            // new PopulateDbAsync(INSTANCE).execute();
        }
    };

    /**
     * Populate the database in the background.
     * If you want to start with more words, just add them.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final PassedScoreDao mDao;
        PopulateDbAsync(PassedScoreRoomDatabase db) {
            mDao = db.passedScoreDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            return null;
        }
    }
}