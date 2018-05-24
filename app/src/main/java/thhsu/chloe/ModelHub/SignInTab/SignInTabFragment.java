package thhsu.chloe.ModelHub.SignInTab;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import thhsu.chloe.ModelHub.R;
import thhsu.chloe.ModelHub.Utils.Constants;
import thhsu.chloe.ModelHub.activities.RegisterActivity;
import thhsu.chloe.ModelHub.activities.SignInActivity;

/**
 * Created by Chloe on 4/30/2018.
 */

public class SignInTabFragment extends Fragment implements SignInTabContract.View, View.OnClickListener {

    SignInTabContract.Presenter mPresenter;
    private Button mSignInTabSignUpBtn, mSignInTabSignInBtn;

    public static SignInTabFragment newInstance(){return new SignInTabFragment();}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sign_in_tab, container, false);
        mSignInTabSignUpBtn = (Button) root.findViewById(R.id.signin_tab_signup_btn);
        mSignInTabSignInBtn = ((Button) root.findViewById(R.id.signin_tab_signin_btn));

        mSignInTabSignUpBtn.setOnClickListener(this);
        mSignInTabSignInBtn.setOnClickListener(this);
        return root;
    }

    @Override
    public void setPresenter(SignInTabContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Constants.REGISTER_REQUEST && resultCode == Constants.RESULT_SUCCESS){

        }else if (requestCode == Constants.ABOUT_ME_REQUEST && resultCode == Constants.RESULT_SUCCESS){

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signin_tab_signup_btn:
                Intent intentToRegister = new Intent(getActivity(), RegisterActivity.class);
//                startActivityForResult(intentToRegister, Constants.REGISTER_REQUEST);
                startActivity(intentToRegister);
                break;

            case R.id.signin_tab_signin_btn:
                Intent intentToSignIn = new Intent(getActivity(), SignInActivity.class);
                startActivity(intentToSignIn);
                break;

        }
    }
}
