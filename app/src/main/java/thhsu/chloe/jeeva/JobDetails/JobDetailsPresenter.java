package thhsu.chloe.jeeva.JobDetails;

/**
 * Created by Chloe on 5/6/2018.
 */

public class JobDetailsPresenter implements JobDetailsContract.Presenter {

    private final JobDetailsContract.View mJobDetailsView;

    public JobDetailsPresenter(JobDetailsContract.View view){
        this.mJobDetailsView = view;
        mJobDetailsView.setPresenter(this);
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
