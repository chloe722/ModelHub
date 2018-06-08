package thhsu.chloe.ModelHub.signInTab;

import android.content.Intent;

import thhsu.chloe.ModelHub.activities.ModelHubActivity;
import thhsu.chloe.ModelHub.activities.SignInActivity;
import thhsu.chloe.ModelHub.activities.SignUpActivity;

/**
 * Created by Chloe on 4/30/2018.
 */

public class SignInTabPresenter implements SignInTabContract.Presenter {

    private SignInTabContract.View mSignInTabView;
    private ModelHubActivity mModelHubActivity;

    public SignInTabPresenter(SignInTabContract.View signInTabView, ModelHubActivity modelHubActivity){
        mSignInTabView = signInTabView;
        if(signInTabView != null){
            mSignInTabView.setPresenter(this);
        }
        mModelHubActivity = modelHubActivity;
    }

    @Override
    public void start() {
    }

    @Override
    public void result(int requestCode, int resultCode) {}


    @Override
    public void transToSignUp() {
        Intent intentToRegister = new Intent(mModelHubActivity, SignUpActivity.class);
        mModelHubActivity.startActivity(intentToRegister);

    }

    @Override
    public void transToSignIn() {
        Intent intentToSignIn = new Intent(mModelHubActivity, SignInActivity.class);
        mModelHubActivity.startActivity(intentToSignIn);
    }
}
