package thhsu.chloe.ModelHub.details;

import thhsu.chloe.ModelHub.BasePresenter;
import thhsu.chloe.ModelHub.BaseView;
import thhsu.chloe.ModelHub.api.model.Jobs;

/**
 * Created by Chloe on 5/6/2018.
 */

public interface JobDetailsContract {

    interface View extends BaseView<Presenter>{

        void showJobDetails(Jobs job);
    }

    interface Presenter extends BasePresenter{
        void result(int requestCode, int resultCode);

        void loadJob();

        void updateInterestJob(Jobs jobs, boolean isSaved);

    }
}
