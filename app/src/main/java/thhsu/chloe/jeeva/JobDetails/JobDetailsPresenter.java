package thhsu.chloe.jeeva.JobDetails;

import thhsu.chloe.jeeva.Jeeva;
import thhsu.chloe.jeeva.activities.JeevaActivity;

/**
 * Created by Chloe on 5/6/2018.
 */

public class JobDetailsPresenter implements JobDetailsContract.Presenter {

    private final JobDetailsContract.View mJobDetailsView;
    JeevaActivity mActivity;


    public JobDetailsPresenter(JobDetailsContract.View JobDetailsview){
        mJobDetailsView = JobDetailsview;
        if (JobDetailsview != null){
            mJobDetailsView.setPresenter(this);
        }
//        mJobDetailsView.setPresenter(this);
    }

    public void showJobDetails(){
        mJobDetailsView.showJobDetails();
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
    }
}
