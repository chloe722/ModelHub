package thhsu.chloe.ModelHub.Interest;

import java.util.ArrayList;

import thhsu.chloe.ModelHub.ModelHub;
import thhsu.chloe.ModelHub.api.GetInterestCallBack;
import thhsu.chloe.ModelHub.api.GetInterestTask;
import thhsu.chloe.ModelHub.api.model.Jobs;

/**
 * Created by Chloe on 4/30/2018.
 */

public class InterestPresenter implements InterestContract.Presenter{

    private InterestContract.View mInterestView;
    ArrayList<Jobs> mJobs;
    public InterestPresenter(InterestContract.View savedJobView){
        mInterestView = savedJobView;
        if(savedJobView != null){
            mInterestView.setPresenter(this);
        }
    }

    @Override
    public void start() {
        loadJobs();
    }

    @Override
    public void result(int requestCode, int resultCode) {
    }

    @Override
    public void showJobs(ArrayList<Jobs> jobs) {
        mInterestView.showJobs(jobs);

    }

    @Override
    public void loadJobs() {
        new GetInterestTask(new GetInterestCallBack() {
            @Override
            public void onCompleted(ArrayList<Jobs> cases) {
                showJobs(cases);

            }
        }).execute();
    }

    @Override
    public void openCaseDetails(Jobs job) {
        mInterestView.showJobsDetailUi(job);
    }

    @Override
    public void refreshJobs() {
        loadJobs();
        ModelHub.getModelHubSQLHelper().setInterestChanged(false);
    }

    @Override
    public void updateInterest(Jobs jobs, boolean isSaved) {
        ModelHub.getModelHubSQLHelper().updateJobs(jobs, isSaved);
    }

//    @Override
//    public void updateInterestJob(Jobs jobs, boolean isSaved) {
//        ModelHub.getModelHubSQLHelper().updateJobs(mJobs, isSaved);
//    }
}
