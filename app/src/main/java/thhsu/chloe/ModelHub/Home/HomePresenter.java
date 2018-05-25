package thhsu.chloe.ModelHub.Home;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

import thhsu.chloe.ModelHub.ModelHub;
import thhsu.chloe.ModelHub.Utils.Constants;
import thhsu.chloe.ModelHub.activities.ModelHubActivity;
import thhsu.chloe.ModelHub.api.ApiCaseManager;
import thhsu.chloe.ModelHub.api.GetCasesCallBack;
//import thhsu.chloe.jeeva.api.model.FilterJobs;
import thhsu.chloe.ModelHub.api.model.Cases;

/**
 * Created by Chloe on 5/1/2018.
 */

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View mHomeView;
    ArrayList<Cases> mCases;
    private ModelHubActivity mActivtity;
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

    public void updateJobs(ArrayList<Cases> cases){
        this.mCases = cases;
//        clearCases();
        loadFilterResult();
    }


    @Override
    public void start() {
        loadCases();
    }


    @Override
    public void result(int requestCode, int resultCode) {}

    @Override
    public void showCases(ArrayList<Cases> cases) {
        mHomeView.showCases(cases);
    }

    @Override
    public void loadCases() {
        if(!isLoading() && hasNextPaging()){
            setLoading(true);
            ApiCaseManager.getInstance().getCases(new GetCasesCallBack() {
                @Override
                public void onCompleted(ArrayList<Cases> cases) {
                    mProgressBar.setVisibility(View.GONE);
                    setLoading(false);
                    showCases(cases);
                    setPaging(0);
                    Log.d("Chloe", "cases" + cases);
                }

                @Override
                public void onError(String errorMessage) {
                    setLoading(false);
                    Log.d("Chloe", "GetCasesErrorMessage, errorMessage:" + errorMessage);
                }
            });
        }


    }

    @Override
    public void loadFilterResult(){
        ApiCaseManager.getInstance().getCases(new GetCasesCallBack() {
            @Override
            public void onCompleted(ArrayList<Cases> cases) {
                showCases(mCases);
                Log.d("Chloe", "filter cases" + mCases.size());
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
                loadCases();
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
    public void openCaseDetails(Cases acase) {
        mHomeView.showCasesDetailUi(acase);
    }

    @Override
    public void refresh() {
        mHomeView.refreshUi();
    }

    @Override
    public void updateInterest(Cases cases, boolean isSaved) {
        ModelHub.getModelHubSQLHelper().updateCases(cases, isSaved);
    }

//    @Override
//    public void clearCases() {
//        mHomeView.clearCases();
//    }

    public boolean isLoading(){return mLoading; }

    public void setLoading(boolean loading){mLoading = loading;}

    public void setPaging(int paging){mPaging = paging;}

    private boolean hasNextPaging(){return (mPaging == Constants.NOR_MORE_PAGING)? false: true; }


}
