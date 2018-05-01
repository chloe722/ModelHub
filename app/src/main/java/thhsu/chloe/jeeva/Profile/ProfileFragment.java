package thhsu.chloe.jeeva.Profile;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import thhsu.chloe.jeeva.R;

/**
 * Created by Chloe on 4/30/2018.
 */

public class ProfileFragment extends Fragment implements ProfileContract.View {

    ProfileContract.Presenter mPresenter;

    public static ProfileFragment newInstance(){
        return new ProfileFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        return root;
    }

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
