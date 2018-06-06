package thhsu.chloe.ModelHub;


//import android.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

import thhsu.chloe.ModelHub.Home.HomeFragment;
import thhsu.chloe.ModelHub.Home.HomePresenter;
import thhsu.chloe.ModelHub.JobDetails.JobDetailsFragment;
import thhsu.chloe.ModelHub.JobDetails.JobDetailsPresenter;
import thhsu.chloe.ModelHub.Profile.ProfileFragment;
import thhsu.chloe.ModelHub.Profile.ProfileInfoFragment;
import thhsu.chloe.ModelHub.Profile.ProfilePresenter;
import thhsu.chloe.ModelHub.Interest.InterestFragment;
import thhsu.chloe.ModelHub.Interest.InterestPresenter;
import thhsu.chloe.ModelHub.SignInTab.SignInTabFragment;
import thhsu.chloe.ModelHub.SignInTab.SignInTabPresenter;
import thhsu.chloe.ModelHub.Utils.Constants;
import thhsu.chloe.ModelHub.activities.ModelHubActivity;
import thhsu.chloe.ModelHub.api.model.Jobs;

/**
 * Created by Chloe on 4/30/2018.
 */

public class ModelHubPresenter implements ModelHubContract.Presenter {
    private final ModelHubContract.View mModelHubContractView;
    private FragmentManager mFragmentManager;
    private ModelHubActivity mActivity;
    private BottomNavigationView mBottomNavigationView;
    private Toolbar mToolbar;
    private ProgressBar mProgressBar;

    private static final String HOME = "HOME";
    private static final String INTEREST = "INTEREST";
    private static final String PROFILE = "PROFILE";
    private static final String SIGNIN = "SIGNIN";
    private static final String JOBDETAILS = "JOBDETAILS";



    private SignInTabFragment mSignInTabFragment;
    private InterestFragment mInterestFragment;
    private HomeFragment mHomeFragment;
    private ProfileFragment mProfileFragment;
    private ProfileInfoFragment mProfileInfoFragment;
    private JobDetailsFragment mCaseDetailsFragment;

    private SignInTabPresenter mSignInTabPresenter;
    private InterestPresenter mInterestPresenter;
    private HomePresenter mHomePresenter;
    private ProfilePresenter mProfilePresenter;
    private JobDetailsPresenter mJobDetailsPresenter;


    public ModelHubPresenter(ModelHubContract.View modelHubView, FragmentManager fragmentManager, ModelHubActivity activity,
                             BottomNavigationView bottomNavigationView, Toolbar toolbar, ProgressBar mProgressBar){
        this.mActivity = activity;
        mModelHubContractView = modelHubView;
        if(modelHubView != null){
            mModelHubContractView.setPresenter(this);
        }else{
            Log.d("Chloe", "modelHubView is empty");
        }
        mFragmentManager = fragmentManager;
        mBottomNavigationView = bottomNavigationView;
        mToolbar = toolbar;
        this.mProgressBar = mProgressBar;

    }

    @Override
    public void result(int requestCode, int resultCode, Intent data) {
        if(requestCode == Constants.FILTER_REQUEST && resultCode == Constants.RESULT_SUCCESS){
            Bundle bundle = data.getExtras();
            ArrayList<Jobs> jobs = (ArrayList<Jobs>)  bundle.getSerializable("filterResult");  //Convert to Arraylist
            Log.d("Chloe", "filter bundle: " + jobs.size());
            mHomePresenter.updateJobs(jobs);
        }
//        else if(requestCode == Constants.CAPTURE_IMAGE_FRAGMENT_REQUEST){
//            mProfileFragment.onActivityResult(Constants.CAPTURE_IMAGE_FRAGMENT_REQUEST, Activity.RESULT_OK, data);
//        }

    }

//    public void updateJobs(ArrayList<Jobs> jobs){
//        mHomePresenter.updateJobs(jobs);
//    }

    @Override
    public void transToHome() {
        FragmentTransaction transaction
                = mFragmentManager.beginTransaction();

        if(mHomeFragment == null) mHomeFragment = HomeFragment.newInstance();
        if(mInterestFragment != null) transaction.hide(mInterestFragment);
        if(mProfileFragment != null) transaction.hide(mProfileFragment);
        if(mSignInTabFragment != null) transaction.hide(mSignInTabFragment);
        if (!mHomeFragment.isAdded()){
            transaction.add(R.id.main_container_for_fragment, mHomeFragment, HOME);
        }else{
            transaction.show(mHomeFragment);
        }
        transaction.commit();
        if(mHomePresenter == null){
            mHomePresenter = new HomePresenter(mHomeFragment, mProgressBar);
        }
        mHomePresenter.refreshJobs();
        mModelHubContractView.showHomeUi();

    }

    @Override
    public void transToInterest() {

        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if(mInterestFragment == null) mInterestFragment = InterestFragment.newInstance();
        if(mHomeFragment != null) transaction.hide(mHomeFragment);
        if(mProfileFragment != null) transaction.hide(mProfileFragment);
        if(mSignInTabFragment != null) transaction.hide(mSignInTabFragment);

        if (!mInterestFragment.isAdded()){
            transaction.add(R.id.main_container_for_fragment, mInterestFragment, INTEREST);
        }else{
            transaction.show(mInterestFragment);
        }
        transaction.commit();

        if(mInterestPresenter == null){
            mInterestPresenter = new InterestPresenter(mInterestFragment);
        }
        mInterestPresenter.refreshJobs();
        mModelHubContractView.showInterestUi();
    }

    @Override
    public void transToProfile() {

        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        if(mProfileFragment == null) mProfileFragment = ProfileFragment.newInstance();
        if(mHomeFragment != null) transaction.hide(mHomeFragment);
        if(mInterestFragment != null) transaction.hide(mInterestFragment);
        if(mSignInTabFragment != null) transaction.hide(mSignInTabFragment);
        if (!mProfileFragment.isAdded()){
            transaction.add(R.id.main_container_for_fragment, mProfileFragment, PROFILE);
        }else{
            transaction.show(mProfileFragment);
        }

        transaction.commit();

        if(mProfilePresenter == null){
            mProfilePresenter = new ProfilePresenter(mProfileFragment, mActivity);
        }
        mModelHubContractView.showProfileUi();
    }

    @Override
    public void transToSignInTabPage() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        if(mSignInTabFragment == null) mSignInTabFragment = SignInTabFragment.newInstance(); //Create only one time
        if(mHomeFragment != null) transaction.hide(mHomeFragment);
        if(mInterestFragment != null) transaction.hide(mInterestFragment);
        if(mProfileFragment != null) transaction.hide(mProfileFragment);

        if (!mSignInTabFragment.isAdded()){
            transaction.add(R.id.main_container_for_fragment, mSignInTabFragment, SIGNIN);
        }else{
            transaction.show(mSignInTabFragment);
        }
        transaction.commit();

        if(mSignInTabPresenter == null){
            mSignInTabPresenter = new SignInTabPresenter(mSignInTabFragment);
        }
        mModelHubContractView.showSignInTabPageUi();
    }

    @Override
    public void transToJobDetails(Jobs jobs) {
        int currentNavItemId = mBottomNavigationView.getSelectedItemId();
        final FragmentTransaction transaction =
                mFragmentManager.beginTransaction()
                        .setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_left, R.animator.slide_in_right, R.animator.slide_out_right); //smooth animator while switching the fragment
        mCaseDetailsFragment = JobDetailsFragment.newInstance();
      if(currentNavItemId == R.id.action_home){
          if(mHomeFragment != null) {
              transaction.hide(mHomeFragment);
              transaction.addToBackStack(HOME);
          }
          mBottomNavigationView.setVisibility(View.GONE);
          mToolbar.findViewById(R.id.home_filter).setVisibility(View.GONE);
          mToolbar.findViewById(R.id.tool_bar_back_btn).setVisibility(View.VISIBLE);
          mToolbar.findViewById(R.id.tool_bar_back_btn).setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  mCaseDetailsFragment.getFragmentManager().popBackStack(); //Back to previous fragment (not new fragment)
              }
          });

      }else if(currentNavItemId == R.id.action_interest){
          if(mInterestFragment != null){
              transaction.hide(mInterestFragment);
              transaction.addToBackStack(INTEREST);
          }
          mBottomNavigationView.setVisibility(View.GONE);
          mToolbar.findViewById(R.id.tool_bar_back_btn).setVisibility(View.VISIBLE);
          mToolbar.findViewById(R.id.tool_bar_back_btn).setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  mCaseDetailsFragment.getFragmentManager().popBackStack(); //Back to previous fragment (not new fragment)
              }
          });
      }

        transaction.add(R.id.main_container_for_fragment, mCaseDetailsFragment, JOBDETAILS);
        transaction.commit();
        mJobDetailsPresenter = new JobDetailsPresenter(mCaseDetailsFragment, jobs, mBottomNavigationView); //Create presenter instrance
        Log.d("Chloe", "ModelHubPresenter job: " + jobs);

        mModelHubContractView.showJobDetailsUi();

    }

    @Override
    public void refreshInterestItem() {
        if(ModelHub.getModelHubSQLHelper().isInterestChanged()){
            if(mInterestPresenter != null){
                mInterestPresenter.refreshJobs();
            }

            if(mHomePresenter != null){
                mHomePresenter.refresh();
            }
        }
    }

    @Override
    public void start() {
        transToHome();
    }

}
