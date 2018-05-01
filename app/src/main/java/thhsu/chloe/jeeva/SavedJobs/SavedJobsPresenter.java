package thhsu.chloe.jeeva.SavedJobs;

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
    }

    @Override
    public void result(int requestCode, int resultCode) {
    }
}
