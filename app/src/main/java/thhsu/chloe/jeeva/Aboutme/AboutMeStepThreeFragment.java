package thhsu.chloe.jeeva.Aboutme;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import thhsu.chloe.jeeva.R;

/**
 * Created by Chloe on 5/4/2018.
 */

public class AboutMeStepThreeFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Button mCompleteBtn;
    private Button mBackBtn;

    private OnStepThreeListener mOnStepThreeListener;

    public AboutMeStepThreeFragment() {
    }

    public static AboutMeStepOneFragment newInstance(String param1, String param2){
        AboutMeStepOneFragment fragment = new AboutMeStepOneFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stepperindicater_three, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCompleteBtn = view.findViewById(R.id.stepper_three_complete_btn);
        mBackBtn = view.findViewById(R.id.stepper_three_back_btn);
    }

    @Override
    public void onResume() {
        super.onResume();
        mCompleteBtn.setOnClickListener(this);
        mBackBtn.setOnClickListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mCompleteBtn.setOnClickListener(null);
        mBackBtn.setOnClickListener(null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.stepper_three_complete_btn:
                if(mOnStepThreeListener != null){
                    mOnStepThreeListener.onCompletePressed(this);
                    break;
                }
            case R.id.stepper_three_back_btn:
                if(mOnStepThreeListener != null){
                    mOnStepThreeListener.onBackPressed(this);
                    break;
                }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnStepThreeListener){
            mOnStepThreeListener = (OnStepThreeListener) context;
        }else{
            throw new RuntimeException(context.toString()
                    + "must implement OnFragmentInteractionListner");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnStepThreeListener = null;
        mCompleteBtn = null;
        mBackBtn = null;
    }

    public interface OnStepThreeListener {
        void onCompletePressed(Fragment fragment);
        void onBackPressed(Fragment fragment);
    }
}
