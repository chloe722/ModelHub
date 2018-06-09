package thhsu.chloe.ModelHub.filter;

import java.util.ArrayList;
import thhsu.chloe.ModelHub.api.ApiJobManager;
import thhsu.chloe.ModelHub.api.GetFilterJobsCallBack;
import thhsu.chloe.ModelHub.api.model.Jobs;

/**
 * Created by Chloe on 5/2/2018.
 */

public class FilterPresenter implements FilterContract.Presenter {
    private FilterContract.View mFilterView;

    public FilterPresenter(FilterContract.View filterView){
        mFilterView = filterView;
        if(filterView != null){
            mFilterView.setPresenter(this);
        }
    }

    @Override
    public void start() {}

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
