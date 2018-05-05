package thhsu.chloe.jeeva.Aboutme;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import thhsu.chloe.jeeva.R;

/**
 * Created by Chloe on 5/4/2018.
 */

public class AboutMeStepOneFragment extends Fragment implements View.OnClickListener {
    private Button mNextBtn;
    private OnStepOneListener mOnStepOneListener;
    EditText mFullName;
    EditText mEmail;
    EditText mPhone;
    TextInputLayout mFullNameLayout;
    TextInputLayout mEmailLayout;
    TextInputLayout mPhoneLayout;

    public AboutMeStepOneFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stepperindicater_one, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNextBtn = view.findViewById(R.id.stepper_one_next_btn);
        mFullName = view.findViewById(R.id.stepper_one_textinput_fullname);
        mEmail = view.findViewById(R.id.stepper_one_textinput_email);
        mPhone = view.findViewById(R.id.stepper_one_textinput_phone);
        mFullNameLayout = view.findViewById(R.id.stepper_one_textinputlayout_fullname);
        mEmailLayout = view.findViewById(R.id.stepper_one_textinputlayout_email);
        mPhoneLayout = view.findViewById(R.id.stepper_one_textinputlayout_phone);
    }

    private boolean validateData(){
        boolean result = true;

        String name = mFullName.getText().toString();
        if(name == null || name.length() < 3) {
            //set the error message
            mFullNameLayout.setError(getString(R.string.invalidName));
            result = false;
        }else{
            // remove error message
            mFullNameLayout.setErrorEnabled(false);
        }

        String email = mEmail.getText().toString();
        if(email == null || !(email.contains("@"))){
            mEmailLayout.setError(getString(R.string.invalidEmail));
            result = false;
        }else{
            mEmailLayout.setErrorEnabled(false);
        }

        String phone = mPhone.getText().toString();
        if(phone == null || phone.equals("")){
            mPhoneLayout.setError(getString(R.string.invalidPhoneNumber));
            result = false;
        }else if(phone.equals("0") || phone.length() < 8){
            mPhoneLayout.setError(getString(R.string.invalidPhoneNumber));
            result = false;
        }else{
            mPhoneLayout.setErrorEnabled(false);
        }
        return result;
    }

    @Override
    public void onResume() {
        super.onResume();
        mNextBtn.setOnClickListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mNextBtn.setOnClickListener(null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.stepper_one_next_btn:
                if(mOnStepOneListener != null && validateData()){
                    mOnStepOneListener.onNextPressed(this);
                    break;
                }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnStepOneListener){
            mOnStepOneListener = (OnStepOneListener) context;
        }else{
            throw new RuntimeException(context.toString()
                        + "must implement OnFragmentInteractionListner");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnStepOneListener = null;
        mNextBtn = null;
    }

    public interface OnStepOneListener {
        void onNextPressed(Fragment fragment);
    }
}
