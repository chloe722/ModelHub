package thhsu.chloe.ModelHub.aboutme;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import thhsu.chloe.ModelHub.R;

/**
 * Created by Chloe on 5/4/2018.
 */

public class AboutMeStepThreeFragment extends Fragment implements View.OnClickListener, Switch.OnCheckedChangeListener {

    private Button mBackBtn;
    private Button mCompleteBtn;
    private Switch mNotificationSwitchBtn;
    private OnStepThreeListener mOnStepThreeListener;

    public AboutMeStepThreeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stepperindicater_three, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBackBtn = view.findViewById(R.id.btn_stepperthree_back);
        mCompleteBtn = view.findViewById(R.id.btn_stepperthree_complete);
        mNotificationSwitchBtn = view.findViewById(R.id.btn_stepperthree_switch);
        mNotificationSwitchBtn.setChecked(true);
    }

    @Override
    public void onResume() {
        super.onResume();

        mBackBtn.setOnClickListener(this);
        mCompleteBtn.setOnClickListener(this);
        mNotificationSwitchBtn.setOnCheckedChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mBackBtn.setOnClickListener(null);
        mCompleteBtn.setOnClickListener(null);
        mNotificationSwitchBtn.setOnCheckedChangeListener(null);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if(mNotificationSwitchBtn.isChecked()){
            mOnStepThreeListener.onSwitchPressedOn(this);
        }else{
            mOnStepThreeListener.onSwitchPressedOff(this);
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_stepperthree_complete:
                if (mOnStepThreeListener != null) {
                    mOnStepThreeListener.onCompletePressed(this);
                    break;
                }

            case R.id.btn_stepperthree_back:
                if (mOnStepThreeListener != null) {
                    mOnStepThreeListener.onBackPressed(this);
                    break;
                }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnStepThreeListener) {
            mOnStepThreeListener = (OnStepThreeListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mBackBtn = null;
        mCompleteBtn = null;
        mOnStepThreeListener = null;
    }


    public interface OnStepThreeListener {
        void onBackPressed(Fragment fragment);
        void onCompletePressed(Fragment fragment);
        void onSwitchPressedOn(Fragment fragment);
        void onSwitchPressedOff(Fragment fragment);
    }
}
