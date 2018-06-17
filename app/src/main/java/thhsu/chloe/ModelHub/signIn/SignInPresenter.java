package thhsu.chloe.ModelHub.signIn;

import thhsu.chloe.ModelHub.api.ApiJobManager;
import thhsu.chloe.ModelHub.api.PostRegisterLoginCallBack;

/**
 * Created by Chloe on 6/14/2018.
 */

public class SignInPresenter implements SignInContract.Presenter {
    private SignInContract.View mView;

    public SignInPresenter(SignInContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void start() {}

    @Override
    public void onClickSignIn(String email, String password) {
        boolean haveError = false;

        if (email.equals("") || !(email.contains("@"))) {
            haveError = true;
            mView.showEmailError(true);
        } else {
            mView.showEmailError(false);
        }

        if (password.length() < 6) {
            haveError = true;
            mView.showPasswordError(true);
        } else {
            mView.showPasswordError(false);
        }

        if(!haveError) {
            ApiJobManager.getInstance().getLogInResult(email, password, new PostRegisterLoginCallBack() {
                @Override
                public void onCompleted(String token) {
                    mView.onClickSignInUi(token);
                }

                @Override
                public void onError(String errorMessage) {
                }
            });
        }
    }
}
