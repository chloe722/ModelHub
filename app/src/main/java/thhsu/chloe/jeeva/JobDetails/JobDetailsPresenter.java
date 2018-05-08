package thhsu.chloe.jeeva.JobDetails;

import android.util.Log;

import java.util.ArrayList;

import thhsu.chloe.jeeva.Jeeva;
import thhsu.chloe.jeeva.activities.JeevaActivity;
import thhsu.chloe.jeeva.api.model.Jobs;

/**
 * Created by Chloe on 5/6/2018.
 */

public class JobDetailsPresenter implements JobDetailsContract.Presenter {

    private final JobDetailsContract.View mJobDetailsView;
    JeevaActivity mActivity;
    private Jobs mJob;


    public JobDetailsPresenter(JobDetailsContract.View JobDetailsView, Jobs job){
        mJobDetailsView = JobDetailsView;
        if (JobDetailsView != null){
            mJobDetailsView.setPresenter(this);
            mJob = job;
            Log.d("Chloe", "Job details mJob: " + mJob);
        }
    }


    @Override
    public void start() {
        loadJob();
    }

    @Override
    public void result(int requestCode, int resultCode) {
    }

//    @Override
//    public void showJobDetails(Jobs job) {
//        mJobDetailsView.showJobDetails(job);
//    }

    @Override
    public void loadJob() {
        mJobDetailsView.showJobDetails(mJob);
    }
}
