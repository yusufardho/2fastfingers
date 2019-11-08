package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Locale;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word_english")
    private String word_english;

    @NonNull
    @ColumnInfo(name = "word_indonesia")
    private String word_indonesia;

    public Word(@NonNull String word_english, @NonNull String word_indonesia) {
        this.word_english = word_english;
        this.word_indonesia = word_indonesia;
    }

    public String getWord() {
        if (Locale.getDefault().getDisplayLanguage().equals("Indonesia")) {
            return this.word_indonesia;
        } else {
            return this.word_english;
        }
    }

    public String getWord_english() {
        return this.word_english;
    }

    public String getWord_indonesia() {
        return this.word_indonesia;
    }
}
