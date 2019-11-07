package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.db.database;

import android.content.Context;

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

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
        }
    };
}