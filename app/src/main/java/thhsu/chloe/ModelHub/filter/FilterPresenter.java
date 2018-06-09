package thhsu.chloe.ModelHub.filter;

import android.app.Activity;
import android.content.Intent;

import java.util.ArrayList;

import thhsu.chloe.ModelHub.ModelHubContract;
import thhsu.chloe.ModelHub.activities.ModelHubActivity;
import thhsu.chloe.ModelHub.api.ApiJobManager;
import thhsu.chloe.ModelHub.api.GetFilterJobsCallBack;
import thhsu.chloe.ModelHub.api.model.Jobs;
import thhsu.chloe.ModelHub.utils.Constants;

/**
 * Created by Chloe on 5/2/2018.
 */

public class FilterPresenter implements FilterContract.Presenter {
    private FilterContract.View mFilterView;
    private ModelHubActivity mModelHubActivity;
    private ModelHubContract.Presenter mPresenter;
    Activity activity;

    public FilterPresenter(FilterContract.View filterView){
//        this.mModelHubActivity = modelHubActivity;
//        this.mPresenter = modelHubPresenter;
        this.mFilterView = filterView;
        if(filterView != null){
            mFilterView.setPresenter(this);
        }
    }

//    ModelHubActivity modelHubActivity, ModelHubContract.Presenter modelHubPresenter


    @Override
    public void start() {
    }

    public void setFilterResult(String tags){
        ApiJobManager.getInstance().getFilterJobs(tags, new GetFilterJobsCallBack() {
            @Override
            public void onCompleted(ArrayList<Jobs> jobs) {

                mFilterView.setResult(jobs);
            }

            @Override
            public void onError(String errorMessage) {
            }
        });
    }





}

//    @Override
//    public void transToHome() {
//        Intent intent = new Intent(, ModelHubActivity.class);
////        mPresenter.transToHome();
////        mModelHubActivity.showHomeUi();
//    }
//}
