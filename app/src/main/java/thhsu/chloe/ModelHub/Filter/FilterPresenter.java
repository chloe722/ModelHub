package thhsu.chloe.ModelHub.Filter;

import android.app.Activity;

import thhsu.chloe.ModelHub.ModelHubContract;
import thhsu.chloe.ModelHub.activities.ModelHubActivity;

/**
 * Created by Chloe on 5/2/2018.
 */

public class FilterPresenter implements FilterContract.Presenter {
    FilterContract.View mFilterView;
    public ModelHubActivity mModelHubActivity;
    public ModelHubContract.Presenter mJeevaPresenter;
    Activity activity;

    public FilterPresenter(FilterContract.View filterView, ModelHubActivity modelHubActivity, ModelHubContract.Presenter jeevaPresenter){
        this.mModelHubActivity = modelHubActivity;
        this.mJeevaPresenter = jeevaPresenter;
        this.mFilterView = filterView;
        if(filterView != null){
            mFilterView.setPresenter(this);
        }
    }


    @Override
    public void start() {

    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void loadData() {

    }

//    @Override
//    public void transToHome() {
//        Intent intent = new Intent(, ModelHubActivity.class);
////        mJeevaPresenter.transToHome();
////        mModelHubActivity.showHomeUi();
//    }
}
