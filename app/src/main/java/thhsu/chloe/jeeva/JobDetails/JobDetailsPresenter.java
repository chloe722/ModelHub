package thhsu.chloe.jeeva.JobDetails;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.BottomNavigationView;
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
    private BottomNavigationView mBottomNavigationView;


    public JobDetailsPresenter(JobDetailsContract.View JobDetailsView, Jobs job, BottomNavigationView bottomNavigationView){
        mJobDetailsView = JobDetailsView;
        if (JobDetailsView != null){
            mJobDetailsView.setPresenter(this);
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
        mJobDetailsView.showJobDetails(mJob);
    }


}
