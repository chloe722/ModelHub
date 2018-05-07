package thhsu.chloe.jeeva.JobDetails;

/**
 * Created by Chloe on 5/6/2018.
 */

public class JobDetailsPresenter implements JobDetailsContract.Presenter {

    private final JobDetailsContract.View mJobDetailsView;

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
