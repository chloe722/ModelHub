package thhsu.chloe.jeeva.SavedJobs;

import java.util.ArrayList;

import thhsu.chloe.jeeva.api.GetSavedJobsCallBack;
import thhsu.chloe.jeeva.api.GetSavedJobsTask;
import thhsu.chloe.jeeva.api.model.Jobs;

/**
 * Created by Chloe on 4/30/2018.
 */

public class SavedJobsPresenter implements SavedJobContract.Presenter{

    private SavedJobContract.View mSavedJobView;

    public SavedJobsPresenter(SavedJobContract.View savedJobView){
        mSavedJobView = savedJobView;
        if(savedJobView != null){
            mSavedJobView.setPresenter(this);
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
        mSavedJobView.showJobs(jobs);

    }

    @Override
    public void loadJobs() {
        new GetSavedJobsTask(new GetSavedJobsCallBack() {
            @Override
            public void onCompleted(ArrayList<Jobs> jobs) {
                showJobs(jobs);
            }
        }).execute();
    }

    @Override
    public void openJobDetails(Jobs job) {
        mSavedJobView.showJobsDetailUi(job);
    }
}
