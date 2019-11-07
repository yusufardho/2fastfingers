package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.db.entity.PassedScore;

@Dao
public interface PassedScoreDao {

    @Insert
    void insert(PassedScore passedScore);

    @Query("SELECT * from passed_score_table ORDER BY score DESC LIMIT 10")
    LiveData<List<PassedScore>> getAllPassedScores();
}