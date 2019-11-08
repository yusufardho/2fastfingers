package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "version_history_table")
public class VersionHistory {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    @NonNull
    @ColumnInfo(name = "version")
    private String version;

    @NonNull
    @ColumnInfo(name = "date")
    private String mDate;


    public VersionHistory(@NonNull String version, @NonNull String date) {
        this.version = version;
        this.mDate = date;
    }

    public int getId() {
        return this.id;
    }

    public String getVersion() { return this.version; }

    public String getDate() {
        return this.mDate;
    }
}
