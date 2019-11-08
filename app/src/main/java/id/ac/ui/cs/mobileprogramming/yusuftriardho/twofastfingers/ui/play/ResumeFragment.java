package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.ui.play;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.R;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.databinding.FragmentResumeBinding;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.interfaces.fragments.ResumeInterface;
import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.viewmodels.PlayViewModel;

public class ResumeFragment extends Fragment implements ResumeInterface {

    public FragmentResumeBinding resumeBinding;
    public PlayViewModel playViewModel;

    public static ResumeFragment newInstance() {
        return new ResumeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        resumeBinding = FragmentResumeBinding.inflate(inflater, container, false);

        resumeBinding.setLifecycleOwner(getActivity());
        resumeBinding.setInterfaceResume(this);

        return resumeBinding.getRoot();
    }

    public void onClickResume() {
        getFragmentManager().beginTransaction()
                .replace(R.id.PlayActivity, PlayFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }
}
