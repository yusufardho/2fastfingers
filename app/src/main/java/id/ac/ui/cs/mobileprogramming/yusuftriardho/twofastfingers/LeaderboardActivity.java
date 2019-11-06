package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.PassedScoreListAdapter;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.PassedScoreViewModel;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.db.entity.PassedScore;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.databinding.ActivityLeaderboardBinding;

public class LeaderboardActivity extends AppCompatActivity {

    // private WordViewModel mWordViewModel;
    private PassedScoreViewModel passedScoreViewModel;
    public ActivityLeaderboardBinding activityLeaderboardBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        activityLeaderboardBinding = DataBindingUtil.setContentView(this, R.layout.activity_leaderboard);
        passedScoreViewModel = ViewModelProviders.of(this).get(PassedScoreViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final PassedScoreListAdapter adapter = new PassedScoreListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        RecyclerView recyclerView = findViewById(R.id.recyclerview);
//        final WordListAdapter adapter = new WordListAdapter(this);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
//        mWordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
//            @Override
//            public void onChanged(@Nullable final List<Word> words) {
//                // Update the cached copy of the words in the adapter.
//                adapter.setWords(words);
//            }
//        });
        passedScoreViewModel.getmAllPassedScores().observe(this, new Observer<List<PassedScore>>() {
            @Override
            public void onChanged(@Nullable final List<PassedScore> passedScores) {
                // Update the cached copy of the words in the adapter.
                adapter.setPassedScores(passedScores);
            }
        });
    }

}
