package thhsu.chloe.ModelHub.signUp;

import android.content.Context;
import android.widget.Toast;

import thhsu.chloe.ModelHub.api.ApiJobManager;
import thhsu.chloe.ModelHub.api.PostRegisterLoginCallBack;

/**
 * Created by Chloe on 6/14/2018.
 */

public class SignUpPresenter implements SignUpContract.Presenter {
    private SignUpContract.View mView;
    private Context mContext;


    public SignUpPresenter(SignUpContract.View view, Context context) {
        mView = view;
        if(view != null){
            mView.setPresenter(this);
        }
        mContext = context;
    }

    @Override
    public void start() {

    }

    @Override
    public void onClickSignUp(String name, String age, String gender, String email, String password, String confirmPassword) {
        boolean haveError = false;

        if (name.equals("") || name.length() < 3) {
            haveError = true;
            mView.showNameError(true);
        } else {
            mView.showNameError(false);
        }

        if (age.equals("") || age.length() > 2) {
            haveError = true;
            mView.showAgeError(true);
        } else {
            mView.showAgeError(false);
        }

        if (email.equals("") || !(email.contains("@"))) {
            haveError = true;
            mView.showEmailError(true);
        }else{
            mView.showEmailError(false);
        }

        if (password.length() < 6) {
            haveError = true;
            mView.showPasswordError(true);
        }else{
            mView.showPasswordError(false);
        }

        if (!(confirmPassword.equals(password))) {
            haveError = true;
            mView.showConfirmPasswordError(true);
        }else{
            mView.showConfirmPasswordError(false);
        }

        if(!haveError) {
            ApiJobManager.getInstance().getRegister(name, age, gender, email, password, new PostRegisterLoginCallBack() {
                @Override
                public void onCompleted(String token) {
                    mView.onClickSignUpUi(token);
                }

                @Override
                public void onError(String errorMessage) {
                    Toast.makeText(mContext, errorMessage, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
