package thhsu.chloe.jeeva.Home;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

import thhsu.chloe.jeeva.Jeeva;
import thhsu.chloe.jeeva.Utils.Constants;
import thhsu.chloe.jeeva.activities.JeevaActivity;
import thhsu.chloe.jeeva.api.ApiJobManager;
import thhsu.chloe.jeeva.api.GetJobsCallBack;
//import thhsu.chloe.jeeva.api.model.FilterJobs;
import thhsu.chloe.jeeva.api.model.Jobs;

/**
 * Created by Chloe on 5/1/2018.
 */

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View mHomeView;
    ArrayList<Jobs> mJobs;
    private  JeevaActivity mActivtity;
    private Context mContext;
    private boolean mLoading = false;
    private int mLastVisibleItemPosition;
    private int mFirstVisibleItemPosition;
    private int mPaging = Constants.FIRST_PAGING;
    private ProgressBar mProgressBar;

    public HomePresenter(HomeContract.View homeView, ProgressBar progressBar){
        mHomeView = homeView;
        if(homeView != null){
            mHomeView.setPresenter(this);
        }
        mProgressBar = progressBar;
    }

    public void updateJobs(ArrayList<Jobs> jobs){
        this.mJobs = jobs;
//        clearJobs();
        loadFilterResult();
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
        if(!isLoading() && hasNextPaging()){
            setLoading(true);
            ApiJobManager.getInstance().getJobs(new GetJobsCallBack() {
                @Override
                public void onCompleted(ArrayList<Jobs> jobs) {
                    mProgressBar.setVisibility(View.GONE);
                    setLoading(false);
                    showJobs(jobs);
                    setPaging(0);
                    Log.d("Chloe", "jobs" + jobs);
                }

                @Override
                public void onError(String errorMessage) {
                    setLoading(false);
                    Log.d("Chloe", "GetJobsErrorMessage, errorMessage:" + errorMessage);
                }
            });
        }


    }

    @Override
    public void loadFilterResult(){
        ApiJobManager.getInstance().getJobs(new GetJobsCallBack() {
            @Override
            public void onCompleted(ArrayList<Jobs> jobs) {
                showJobs(mJobs);
                Log.d("Chloe", "filter jobs" + mJobs.size());
            }
            @Override
            public void onError(String errorMessage) {
                Log.d("Chloe", "GetJobsErrorMessage, errorMessage:" + errorMessage);
            }
        });
    }

    @Override
    public void onScrollStateChanged(int visibleItemCount, int totalItemCount, int newState) {

        if(newState == RecyclerView.SCROLL_STATE_IDLE && totalItemCount > 0){

            if(mLastVisibleItemPosition == totalItemCount -1){
                Log.d("Chloe", "Scroll to bottom");
                loadJobs();
            } else if (mFirstVisibleItemPosition == 0){}
            }
        }


    @Override
    public void onScrolled(RecyclerView.LayoutManager layoutManager) {
        if(layoutManager instanceof LinearLayoutManager){

            mLastVisibleItemPosition = ((LinearLayoutManager) layoutManager)
                    .findLastVisibleItemPosition();

            mFirstVisibleItemPosition = ((LinearLayoutManager) layoutManager)
                    .findFirstVisibleItemPosition();
        }else if(layoutManager instanceof GridLayoutManager){

            mLastVisibleItemPosition = ((GridLayoutManager) layoutManager)
                    .findLastVisibleItemPosition();

            mFirstVisibleItemPosition = ((GridLayoutManager) layoutManager)
                    .findFirstVisibleItemPosition();
        }
    }

    @Override
    public void openJobDetails(Jobs job) {
        mHomeView.showJobsDetailUi(job);
    }

    @Override
    public void refresh() {
        mHomeView.refreshUi();
    }

    @Override
    public void updateSavedJob(Jobs job, boolean isSaved) {
        Jeeva.getJeevaSQLHelper().updateSavedJobs(job, isSaved);
    }

//    @Override
//    public void clearJobs() {
//        mHomeView.clearJobs();
//    }

    public boolean isLoading(){return mLoading; }

    public void setLoading(boolean loading){mLoading = loading;}

    public void setPaging(int paging){mPaging = paging;}

    private boolean hasNextPaging(){return (mPaging == Constants.NOR_MORE_PAGING)? false: true; }


}
