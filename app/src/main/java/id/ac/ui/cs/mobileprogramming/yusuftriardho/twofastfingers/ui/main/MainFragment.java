package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.R;

public class MainFragment extends Fragment {

    Button sideBarBtn, startBtn, timer, backBtn;
    TextView title, textBox, resultBox;
    EditText input;

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
        getRes();

        sideBarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, SideBarFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, MainFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onStartClick(view);
            }
        });
    }

    public void getRes() {
        sideBarBtn = getView().findViewById(R.id.sidebar_btn);
        startBtn = getView().findViewById(R.id.start_btn);
        title = getView().findViewById(R.id.title);
        textBox = getView().findViewById(R.id.textBox);
        input = getView().findViewById(R.id.inputText);
        timer = getView().findViewById(R.id.timer);
        resultBox = getView().findViewById(R.id.resultBox);
        backBtn = getView().findViewById(R.id.back_btn);
    }

    public void playState() {
        sideBarBtn.setVisibility(View.GONE);
        startBtn.setVisibility(View.GONE);
        title.setVisibility(View.GONE);
        resultBox.setVisibility(View.GONE);

        textBox.setVisibility(View.VISIBLE);
        input.setVisibility(View.VISIBLE);
        timer.setVisibility(View.VISIBLE);
        backBtn.setVisibility(View.VISIBLE);
    }

    public void menuState() {
        textBox.setVisibility(View.GONE);
        input.setVisibility(View.GONE);
        timer.setVisibility(View.GONE);

        sideBarBtn.setVisibility(View.VISIBLE);
        startBtn.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);
        resultBox.setVisibility(View.VISIBLE);
        backBtn.setVisibility(View.VISIBLE);

    }

    public void runTimer() {
        new CountDownTimer(60000, 1000) {

            public void onTick(long ms) {
                timer.setText(""+ms/1000);
            }

            public void onFinish() {
                menuState();
            }
        }.start();
    }

    public void onStartClick(View view) {
        playState();
        runTimer();
    }

}
