package thhsu.chloe.jeeva;

import android.support.v4.app.FragmentManager;

import thhsu.chloe.jeeva.SavedJobs.SavedJobsFragment;
import thhsu.chloe.jeeva.SavedJobs.SavedJobsPresenter;
import thhsu.chloe.jeeva.SignInTab.SignInTabFragment;
import thhsu.chloe.jeeva.SignInTab.SignInTabPresenter;

/**
 * Created by Chloe on 4/30/2018.
 */

public class JeevaPresenter implements JeevaContract.Presenter {
    private JeevaContract.View mJeevaContractView;
    private FragmentManager mFragmentManager;

    public static final String HOME = "HOME";
    public static final String SAVEDJOBS = "SAVEDJOBS";
    public static final String PROFILE = "PROFILE";
    public static final String SIGNIN = "SIGNIN";

    private SignInTabFragment mSignInTabFragment;
    private SavedJobsFragment mSavedJobsFragment;

    private SignInTabPresenter mSignInTabPresenter;
    private SavedJobsPresenter mSavedJobsPresenter;

    @Override
    public void start() {

    }

    @Override
    public void result(int requestCode, int resultCode) {
    }

    @Override
    public void transToHome() {

    }

    @Override
    public void transToSavedJob() {

    }

    @Override
    public void transToProfile() {

    }

    @Override
    public void transToDetails() {

    }
}
