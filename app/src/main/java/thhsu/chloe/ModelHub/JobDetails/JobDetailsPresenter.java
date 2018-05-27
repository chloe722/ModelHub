package thhsu.chloe.ModelHub.JobDetails;

import android.support.design.widget.BottomNavigationView;
import android.util.Log;

import thhsu.chloe.ModelHub.ModelHub;
import thhsu.chloe.ModelHub.activities.ModelHubActivity;
import thhsu.chloe.ModelHub.api.model.Jobs;

/**
 * Created by Chloe on 5/6/2018.
 */

public class JobDetailsPresenter implements JobDetailsContract.Presenter {

    private final JobDetailsContract.View mCaseDetailsView;
    ModelHubActivity mActivity;
    private Jobs mJob;
    private BottomNavigationView mBottomNavigationView;


    public JobDetailsPresenter(JobDetailsContract.View CaseDetailsView, Jobs job, BottomNavigationView bottomNavigationView){
        mCaseDetailsView = CaseDetailsView;
        if (CaseDetailsView != null){
            mCaseDetailsView.setPresenter(this);
            mJob = job;
            Log.d("Chloe", "Job details mJob: " + mJob);
            mBottomNavigationView = bottomNavigationView;
        }
    }

    @Override
    public void start() {
        loadJob();
    }

    @Override
    public void result(int requestCode, int resultCode) {
    }

    @Override
    public void loadJob() {
        mCaseDetailsView.showJobDetails(mJob);
    }

    @Override
    public void updateInterestJob(Jobs jobs, boolean isSaved) {
        ModelHub.getModelHubSQLHelper().updateJobs(mJob, isSaved);
    }


}
