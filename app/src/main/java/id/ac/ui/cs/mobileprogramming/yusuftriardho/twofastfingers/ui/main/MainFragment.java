package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.PlayActivity;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.R;

public class MainFragment extends Fragment {

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button sideBarBtn = getView().findViewById(R.id.sidebar_btn);
        sideBarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.MainActivity, SideBarFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });

        Button startBtn = getView().findViewById(R.id.start_btn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainViewModel mViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

                Intent intent = new Intent(getActivity(), PlayActivity.class);
                intent.putExtra("lang", mViewModel.language);
                startActivity(intent);
            }
        });
    }
}
