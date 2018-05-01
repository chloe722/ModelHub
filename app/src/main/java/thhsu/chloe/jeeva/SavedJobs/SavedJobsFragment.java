package thhsu.chloe.jeeva.SavedJobs;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import thhsu.chloe.jeeva.R;
import thhsu.chloe.jeeva.SignInTab.SignInTabFragment;

/**
 * Created by Chloe on 4/30/2018.
 */

public class SavedJobsFragment extends Fragment implements SavedJobContract.View {

   SavedJobContract.Presenter mPresenter;

    public static SavedJobsFragment newInstance(){return new SavedJobsFragment();}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_saved_jobs, container, false);

        return root;
    }

    @Override
    public void setPresenter(SavedJobContract.Presenter presenter) {
        mPresenter = presenter;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }

}
