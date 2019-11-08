package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.VersionHistoryRepository;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.db.entity.VersionHistory;

public class VersionHistoryViewModel extends AndroidViewModel {
    private VersionHistoryRepository mRepository;
    private List<VersionHistory> allVersionHistory;

    public VersionHistoryViewModel (Application application) {
        super(application);
        mRepository = new VersionHistoryRepository(application);
        allVersionHistory = mRepository.getAllVersionHistory();
        allVersionHistory = mRepository.getAllVersionHistory();
    }

    public List<VersionHistory> getAllVersionHistory() { return allVersionHistory; }

    public void insert(VersionHistory versionHistory) { mRepository.insert(versionHistory); }

    public String getStr() {
        String ret = "";
        for (VersionHistory vh: allVersionHistory) {
            ret += vh.getVersion() + " - " + vh.getDate() + "\n";
        }
        return ret;
    }

}
