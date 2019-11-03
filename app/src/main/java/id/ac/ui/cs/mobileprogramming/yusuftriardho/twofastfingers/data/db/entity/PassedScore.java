package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "passed_score_table")
public class PassedScore {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    @NonNull
    @ColumnInfo(name = "score")
    private int mScore;

    @NonNull
    @ColumnInfo(name = "date")
    private String mDate;


    public PassedScore(@NonNull int score, @NonNull String date) {
        this.mScore = score;
        this.mDate = date;
    }

    public int getId() {
        return this.id;
    }

    public int getScore() {
        return this.mScore;
    }

    public String getDate() {
        return this.mDate;
    }
}
