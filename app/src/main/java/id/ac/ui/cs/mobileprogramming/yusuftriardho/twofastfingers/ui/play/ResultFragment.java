package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.ui.play;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.MainActivity;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.R;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.viewmodels.PassedScoreViewModel;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.data.db.entity.PassedScore;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.databinding.FragmentResultBinding;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.interfaces.fragments.ResultInterface;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.viewmodels.PlayViewModel;

public class ResultFragment extends Fragment implements ResultInterface {

    private FragmentResultBinding resultBinding;
    private PassedScoreViewModel passedScoreViewModel;
    private PlayViewModel playViewModel;
    private String resultText;

    public static ResultFragment newInstance() {
        return new ResultFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        resultBinding = FragmentResultBinding.inflate(inflater, container, false);
        playViewModel = ViewModelProviders.of(getActivity()).get(PlayViewModel.class);
        passedScoreViewModel = ViewModelProviders.of(this).get(PassedScoreViewModel.class);

        resultText = String.format("%d " + getString(R.string.score_result), playViewModel.getCorrectWord());
        playViewModel.setResultText(resultText);
        passedScoreViewModel.insert(new PassedScore(playViewModel.getCorrectWord(), getCurrentDate()));

        resultBinding.setPlayViewModel(playViewModel);
        resultBinding.setLifecycleOwner(getActivity());
        resultBinding.setResultInterface(this);

        return resultBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setBackgroundColor(getView());
    }

    public void onClickMenu() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }

    public void onClickRetry() {
        getFragmentManager().beginTransaction()
                .replace(R.id.PlayActivity, PlayFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }

    public void onClickShare() {
        onClickShare(getActivity());
    }

    private void onClickShare(Context context) {
        Bitmap bitmap = generateImg(context);

        try {
            File cachePath = new File(context.getCacheDir(), "tmp");
            cachePath.mkdirs();
            FileOutputStream stream = new FileOutputStream(cachePath + "/tmp.png");
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            stream.close();
        } catch (IOException ignored) {}

        File imagePath = new File(context.getCacheDir(), "tmp");
        File newFile = new File(imagePath, "tmp.png");
        Uri contentUri = FileProvider.getUriForFile(context, "com.id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.fileprovider", newFile);

        if (contentUri != null) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND)
                    .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    .setDataAndType(contentUri, context.getContentResolver().getType(contentUri))
                    .putExtra(Intent.EXTRA_STREAM, contentUri);
            startActivity(Intent.createChooser(shareIntent, getString(R.string.title_share)));
        }
    }

    private Bitmap generateImg(Context context) {
        try {
            Resources resources = context.getResources();
            Bitmap bitmap = BitmapFactory.decodeResource(resources, R.drawable.result);
            bitmap = bitmap.copy(android.graphics.Bitmap.Config.ARGB_8888, true);

            float scale = resources.getDisplayMetrics().density;
            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            Typeface plain = getResources().getFont(R.font.zcool);

            paint.setTypeface(plain);
            paint.setColor(Color.WHITE);
            paint.setTextSize((int) (220 * scale));

            Rect bounds = new Rect();
            paint.getTextBounds(resultText, 0, resultText.length(), bounds);
            int x = (bitmap.getWidth() - bounds.width())/7;
            int y = (bitmap.getHeight() + bounds.height())/6;

            // xx WPM
            canvas.drawText(resultText, x * scale, y * scale, paint);

            paint.setTextSize((int) (50 * scale));
            x += 75;
            if (playViewModel.getCorrectWord() >= 10) {
                x += 75;
            }
            y = (bitmap.getHeight() + bounds.height())/5 - 150;

            // (W.. P.. M..)
            canvas.drawText(getString(R.string.WPMstand), x * scale, y * scale, paint);

            return bitmap;
        } catch (Exception ignored) {}
        return null;
    }

    public String getCurrentDate() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy\nhh:mm:ss");
        return df.format(c);
    }

    public void setBackgroundColor(View view) {
        final String TIER_1 = "#955251";
        final String TIER_2 = "#F1B3A7";
        final String TIER_3 = "#EFC050";
        final String TIER_4 = "#009B77";
        final String TIER_5 = "#92A8D1";

        String color;
        int score = playViewModel.getCorrectWord();
        if (score < 10) color = TIER_1;
        else if (score < 30) color = TIER_2;
        else if (score < 60) color = TIER_3;
        else if (score < 100) color = TIER_4;
        else color = TIER_5;
        view.setBackgroundColor(Color.parseColor(color));
    }
}
