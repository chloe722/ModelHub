package thhsu.chloe.jeeva.SignInTab;

import thhsu.chloe.jeeva.Home.HomeContract;
import thhsu.chloe.jeeva.SavedJobs.SavedJobsPresenter;

/**
 * Created by Chloe on 4/30/2018.
 */

public class SignInTabPresenter implements SignInTabContract.Presenter {

    private SignInTabContract.View mSignInTabView;

    public SignInTabPresenter(SignInTabContract.View signInTabView){
        mSignInTabView = signInTabView;
        if(signInTabView != null){
            mSignInTabView.setPresenter(this);
        }
    }

    @Override
    public void start() {
    }

    @Override
    public void result(int requestCode, int resultCode) {
    }
}
