package thhsu.chloe.ModelHub.signInTab;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import thhsu.chloe.ModelHub.R;


/**
 * Created by Chloe on 4/30/2018.
 */

public class SignInTabFragment extends Fragment implements SignInTabContract.View, View.OnClickListener {
    private SignInTabContract.Presenter mPresenter;

    public static SignInTabFragment newInstance(){return new SignInTabFragment();}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sign_in_tab, container, false);
        Button signInTabSignUpBtn = (Button) root.findViewById(R.id.btn_signin_tab_signup);
        Button signInTabSignInBtn = ((Button) root.findViewById(R.id.btn_signin_tab_signin));

        signInTabSignUpBtn.setOnClickListener(this);
        signInTabSignInBtn.setOnClickListener(this);
        return root;
    }

    @Override
    public void setPresenter(SignInTabContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_signin_tab_signup:

                mPresenter.transToSignUp();
                break;

            case R.id.btn_signin_tab_signin:

                mPresenter.transToSignIn();
                break;

        }
    }
}
