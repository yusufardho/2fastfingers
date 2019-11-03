package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.R;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.db.entity.PassedScore;

public class PassedScoreListAdapter extends RecyclerView.Adapter<PassedScoreListAdapter.PassedScoreViewHolder> {

    private final LayoutInflater mInflater;
    private List<PassedScore> mPassedScore; // Cached copy of words

    public PassedScoreListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public PassedScoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new PassedScoreViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PassedScoreViewHolder holder, int position) {
        if (mPassedScore != null) {
            PassedScore current = mPassedScore.get(position);
            holder.dateItemView.setText(current.getDate());
            holder.scoreItemView.setText(current.getScore() + " WPM");
        } else {
            holder.dateItemView.setText("not found");
            holder.scoreItemView.setText("not found");
        }
    }

    public void setPassedScores(List<PassedScore> passedScores){
        mPassedScore = passedScores;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mPassedScore != null)
            return mPassedScore.size();
        else return 0;
    }

    class PassedScoreViewHolder extends RecyclerView.ViewHolder {
        private final TextView dateItemView, scoreItemView;

        private PassedScoreViewHolder(View itemView) {
            super(itemView);
            dateItemView = itemView.findViewById(R.id.col1);
            scoreItemView = itemView.findViewById(R.id.col2);
        }
    }
}