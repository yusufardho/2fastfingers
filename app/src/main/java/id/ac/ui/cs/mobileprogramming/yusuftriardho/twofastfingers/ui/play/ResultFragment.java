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
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.MainActivity;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.R;

public class ResultFragment extends Fragment {

    private PlayViewModel pViewModel;
    private String dispTxt;

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

        pViewModel = ViewModelProviders.of(getActivity()).get(PlayViewModel.class);
        TextView scoreView = getView().findViewById(R.id.score);
        dispTxt = String.format("%d " + getString(R.string.score_result), pViewModel.getCurrentScore());
        scoreView.setText(dispTxt);

        Button mainMenuBtn = getView().findViewById(R.id.main_menu_btn);
        mainMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        Button shareBtn = getView().findViewById(R.id.share_btn);
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickShare(getActivity());
            }
        });

        Button tryAgainBtn = getView().findViewById(R.id.try_again_btn);
        tryAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.PlayActivity, PlayFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void onClickShare(Context context) {
        Bitmap bitmap = generateImg(context, R.drawable.result);

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

    private Bitmap generateImg(Context mContext,  int resourceId) {
        try {
            Resources resources = mContext.getResources();
            Bitmap bitmap = BitmapFactory.decodeResource(resources, resourceId);
            bitmap = bitmap.copy(android.graphics.Bitmap.Config.ARGB_8888, true);

            float scale = resources.getDisplayMetrics().density;
            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            Typeface plain = getResources().getFont(R.font.zcool);

            paint.setTypeface(plain);
            paint.setColor(Color.WHITE);
            paint.setTextSize((int) (220 * scale));

            Rect bounds = new Rect();
            paint.getTextBounds(dispTxt, 0, dispTxt.length(), bounds);
            int x = (bitmap.getWidth() - bounds.width())/7;
            int y = (bitmap.getHeight() + bounds.height())/6;

            // xx WPM
            canvas.drawText(dispTxt, x * scale, y * scale, paint);

            paint.setTextSize((int) (50 * scale));
            x += 75;
            if (pViewModel.getCurrentScore() >= 10) {
                x += 75;
            }
            y = (bitmap.getHeight() + bounds.height())/5 - 150;

            // (W.. P.. M..)
            canvas.drawText(getString(R.string.WPMstand), x * scale, y * scale, paint);

            return bitmap;
        } catch (Exception ignored) {}
        return null;
    }



}
