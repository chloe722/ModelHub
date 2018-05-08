package thhsu.chloe.jeeva.JobDetails;

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
    private ArrayList<Jobs> jobs;
    private String mJobId;


    public JobDetailsPresenter(JobDetailsContract.View JobDetailsview, String jobId){
        mJobDetailsView = JobDetailsview;
        if (JobDetailsview != null){
            mJobDetailsView.setPresenter(this);
            mJobId = jobId;
        }
//        mJobDetailsView.setPresenter(this);
    }


    @Override
    public void start() {

    }

    @Override
    public void result(int requestCode, int resultCode) {
    }

    @Override
    public void showJobDetails(ArrayList<Jobs> jobs) {
        mJobDetailsView.showJobDetails(jobs);
    }

    @Override
    public void loadJob() {
    }
}
