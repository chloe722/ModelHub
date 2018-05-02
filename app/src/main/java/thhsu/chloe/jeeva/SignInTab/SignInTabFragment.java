package thhsu.chloe.jeeva.SignInTab;

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

/**
 * Created by Chloe on 4/30/2018.
 */

public class SignInTabFragment extends Fragment implements SignInTabContract.View {

    SignInTabContract.Presenter mPresenter;

    public static SignInTabFragment newInstance(){return new SignInTabFragment();}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sign_in_tab, container, false);
//        ((Button) root.findViewById(R.id.signin_tab_signup_btn));
//        ((Button) root.findViewById(R.id.signin_tab_signin_btn));
        return root;
    }



    @Override
    public void setPresenter(SignInTabContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }
}
