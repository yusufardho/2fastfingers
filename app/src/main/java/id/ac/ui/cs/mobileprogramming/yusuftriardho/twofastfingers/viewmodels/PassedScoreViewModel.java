package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.PassedScoreRepository;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.db.entity.PassedScore;

public class PassedScoreViewModel extends AndroidViewModel {

    private PassedScoreRepository mRepository;

    private LiveData<List<PassedScore>> mAllPassedScores;

    public PassedScoreViewModel (Application application) {
        super(application);
        mRepository = new PassedScoreRepository(application);
        mAllPassedScores = mRepository.getAllPassedScores();
    }

    public LiveData<List<PassedScore>> getmAllPassedScores() { return mAllPassedScores; }

    public void insert(PassedScore passedScore) { mRepository.insert(passedScore); }
}