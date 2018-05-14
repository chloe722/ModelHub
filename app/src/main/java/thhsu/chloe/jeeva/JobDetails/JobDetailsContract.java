package thhsu.chloe.jeeva.JobDetails;

import java.util.ArrayList;

import thhsu.chloe.jeeva.BasePresenter;
import thhsu.chloe.jeeva.BaseView;
import thhsu.chloe.jeeva.api.model.Jobs;

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

    }
}
