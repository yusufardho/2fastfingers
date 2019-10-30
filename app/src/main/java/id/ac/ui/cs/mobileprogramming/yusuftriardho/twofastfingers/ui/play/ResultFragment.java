package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.ui.play;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.MainActivity;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.R;

public class ResultFragment extends Fragment {

    private PlayViewModel pViewModel;

    public static ResultFragment newInstance() {
        return new ResultFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setResult();
        Button mainMenu = getView().findViewById(R.id.main_menu_btn);
        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void setResult() {
        pViewModel = ViewModelProviders.of(getActivity()).get(PlayViewModel.class);
        TextView scoreView = getView().findViewById(R.id.score);
        String txt = String.valueOf(pViewModel.getCurrentScore()) + " WPM";
        scoreView.setText(txt);
    }
}
