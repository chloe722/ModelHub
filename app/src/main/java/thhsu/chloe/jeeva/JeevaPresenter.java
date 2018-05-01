package thhsu.chloe.jeeva;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Log;

import thhsu.chloe.jeeva.Home.HomeFragment;
import thhsu.chloe.jeeva.Home.HomePresenter;
import thhsu.chloe.jeeva.Profile.ProfileFragment;
import thhsu.chloe.jeeva.Profile.ProfilePresenter;
import thhsu.chloe.jeeva.SavedJobs.SavedJobsFragment;
import thhsu.chloe.jeeva.SavedJobs.SavedJobsPresenter;
import thhsu.chloe.jeeva.SignInTab.SignInTabFragment;
import thhsu.chloe.jeeva.SignInTab.SignInTabPresenter;

/**
 * Created by Chloe on 4/30/2018.
 */

public class JeevaPresenter implements JeevaContract.Presenter {
    private final JeevaContract.View mJeevaContractView;
    private FragmentManager mFragmentManager;

    public static final String HOME = "HOME";
    public static final String SAVEDJOBS = "SAVEDJOBS";
    public static final String PROFILE = "PROFILE";
    public static final String SIGNIN = "SIGNIN";


    private SignInTabFragment mSignInTabFragment;
    private SavedJobsFragment mSavedJobsFragment;
    private HomeFragment mHomeFragment;
    private ProfileFragment mProfileFragment;

    private SignInTabPresenter mSignInTabPresenter;
    private SavedJobsPresenter mSavedJobsPresenter;
    private HomePresenter mHomePresenter;
    private ProfilePresenter mProfilePresenter;

    public JeevaPresenter(JeevaContract.View jeevaView, FragmentManager fragmentManager){
        mJeevaContractView = jeevaView;
        if(jeevaView != null){
            mJeevaContractView.setPresenter(this);
        }else{
            Log.d("Chloe", "jeevaView is empty");
        }
        mFragmentManager = fragmentManager;

    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void transToHome() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        if(mFragmentManager.findFragmentByTag(HOME) != null)
            mFragmentManager.popBackStack();
        if(mHomeFragment == null) mHomeFragment = HomeFragment.newInstance();
        if(mSavedJobsFragment != null) transaction.hide(mSavedJobsFragment);
        if(mSignInTabFragment != null) transaction.hide(mSignInTabFragment);
        if (!mHomeFragment.isAdded()){
            transaction.add(R.id.main_container_for_fragment, mHomeFragment, HOME);
        }else{
            transaction.show(mHomeFragment);
        }
        transaction.commit();

        if(mHomePresenter == null){
            mHomePresenter = new HomePresenter(mHomeFragment);
        }
        mJeevaContractView.showHomeUi();


    }



    @Override
    public void transToSavedJob() {

        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        if(mFragmentManager.findFragmentByTag(SAVEDJOBS) != null)
            mFragmentManager.popBackStack();
        if(mSavedJobsFragment == null) mSavedJobsFragment = SavedJobsFragment.newInstance();
        if(mHomeFragment != null) transaction.hide(mHomeFragment);
        if(mSignInTabFragment != null) transaction.hide(mSignInTabFragment);
        if (!mSavedJobsFragment.isAdded()){
            transaction.add(R.id.main_container_for_fragment, mSavedJobsFragment, SAVEDJOBS);
        }else{
            transaction.show(mSavedJobsFragment);
        }
        transaction.commit();

        if(mSavedJobsPresenter == null){
            mSavedJobsPresenter = new SavedJobsPresenter(mSavedJobsFragment);
        }
        mJeevaContractView.showSavedJobUi();

    }

    @Override
    public void transToProfile() {

        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        if(mFragmentManager.findFragmentByTag(PROFILE) != null)
            mFragmentManager.popBackStack();
        if(mProfileFragment == null) mProfileFragment = ProfileFragment.newInstance();
        if(mHomeFragment != null) transaction.hide(mHomeFragment);
        if(mSavedJobsFragment != null) transaction.hide(mSavedJobsFragment);
        if (!mProfileFragment.isAdded()){
            transaction.add(R.id.main_container_for_fragment, mProfileFragment, PROFILE);
        }else{
            transaction.show(mProfileFragment);
        }
        transaction.commit();

        if(mProfilePresenter == null){
            mProfilePresenter = new ProfilePresenter(mProfileFragment);
        }
        mJeevaContractView.showProfileUi();

    }

    @Override
    public void transToSignInTabPage() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        if(mFragmentManager.findFragmentByTag(SIGNIN) != null)
            mFragmentManager.popBackStack();
        if(mSignInTabFragment == null) mSignInTabFragment = SignInTabFragment.newInstance();
        if(mSavedJobsFragment != null) transaction.hide(mSavedJobsFragment);
        if(mHomeFragment != null) transaction.hide(mHomeFragment);
        if (!mSignInTabFragment.isAdded()){
            transaction.add(R.id.main_container_for_fragment, mSignInTabFragment, SIGNIN);
        }else{
            transaction.show(mSignInTabFragment);
        }
        transaction.commit();

        if(mSignInTabPresenter == null){
            mSignInTabPresenter = new SignInTabPresenter(mSignInTabFragment);
        }
        mJeevaContractView.showSignInTabPageUi();

    }

    @Override
    public void start() {
        transToHome();
    }

}
