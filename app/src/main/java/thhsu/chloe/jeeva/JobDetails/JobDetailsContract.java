package thhsu.chloe.jeeva.JobDetails;

import thhsu.chloe.jeeva.BasePresenter;
import thhsu.chloe.jeeva.BaseView;

/**
 * Created by Chloe on 5/6/2018.
 */

public interface JobDetailsContract {

    interface View extends BaseView<Presenter>{

        void showJobDetails();

    }

    interface Presenter extends BasePresenter{
        void result(int requestCode, int resultCode);

        void loadJob();
    }
}
