package thhsu.chloe.jeeva.Home;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import thhsu.chloe.jeeva.api.ApiJobManager;
import thhsu.chloe.jeeva.api.GetJobsCallBack;
import thhsu.chloe.jeeva.api.model.Jobs;

/**
 * Created by Chloe on 5/1/2018.
 */

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View mHomeView;

    public HomePresenter(HomeContract.View homeView){
        mHomeView = homeView;
        if(homeView != null){
            mHomeView.setPresenter(this);
        }
        mHomeView.setPresenter(this);
    }


    @Override
    public void start() {
        loadJobs();
    }

    @Override
    public void result(int requestCode, int resultCode) {}

    @Override
    public void showJobs(ArrayList<Jobs> jobs) {
        mHomeView.showJobs(jobs);
    }

    @Override
    public void loadJobs() {
        ApiJobManager.getInstance().getJobs(new GetJobsCallBack() {
            @Override
            public void onCompleted(ArrayList<Jobs> jobs) {
                showJobs(jobs);
                Log.d("Chloe", "jobs" + jobs);
            }

            @Override
            public void onError(String errorMessage) {
                Log.d("Chloe", "GetJobsErrorMessage, errorMessage:" + errorMessage);
            }
        });
    }


    @Override
    public void onScrollStateChanged(int visibleItemCount, int totalItemCount, int newState) {

    }

    @Override
    public void onScrolled(RecyclerView.LayoutManager layoutManager) {

    }

    @Override
    public void openJobDetails(String jobId) {
        mHomeView.showJobsDetailUi(jobId);
    }

}
