package thhsu.chloe.jeeva.Filter;

import android.app.Activity;
import android.content.Intent;

import thhsu.chloe.jeeva.JeevaContract;
import thhsu.chloe.jeeva.activities.JeevaActivity;

/**
 * Created by Chloe on 5/2/2018.
 */

public class FilterPresenter implements FilterContract.Presenter {
    FilterContract.View mFilterView;
    public JeevaActivity mJeevaActivity;
    public JeevaContract.Presenter mJeevaPresenter;
    Activity activity;

    public FilterPresenter(FilterContract.View filterView, JeevaActivity jeevaActivity, JeevaContract.Presenter jeevaPresenter){
        this.mJeevaActivity = jeevaActivity;
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
//        Intent intent = new Intent(, JeevaActivity.class);
////        mJeevaPresenter.transToHome();
////        mJeevaActivity.showHomeUi();
//    }
}
