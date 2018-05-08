package thhsu.chloe.jeeva.Profile;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import thhsu.chloe.jeeva.R;
import thhsu.chloe.jeeva.activities.AboutMeActivity;
import thhsu.chloe.jeeva.activities.JeevaActivity;

/**
 * Created by Chloe on 4/30/2018.
 */

public class ProfileFragment extends Fragment implements ProfileContract.View, View.OnClickListener {

    ProfileContract.Presenter mPresenter;
    Button mEditInfoBtn;

    public static ProfileFragment newInstance(){
        return new ProfileFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        mEditInfoBtn = (Button) root.findViewById(R.id.profile_edited_btn);

        mEditInfoBtn.setOnClickListener(this);

        return root;
    }

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.profile_edited_btn:
                Intent intent = new Intent(getActivity(), AboutMeActivity.class);
                startActivity(intent);
        }
    }
}
