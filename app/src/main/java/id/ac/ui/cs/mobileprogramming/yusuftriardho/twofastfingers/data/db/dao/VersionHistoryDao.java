package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.db.entity.VersionHistory;

@Dao
public interface VersionHistoryDao {

    @Insert
    void insert(VersionHistory versionHistory);

    @Query("DELETE FROM version_history_table")
    void deleteAll();

    @Query("SELECT * from version_history_table")
    List<VersionHistory> getAllVersionHistory();
}
