package thhsu.chloe.ModelHub.Interest;

import java.util.ArrayList;

import thhsu.chloe.ModelHub.BasePresenter;
import thhsu.chloe.ModelHub.BaseView;
import thhsu.chloe.ModelHub.api.model.Jobs;

/**
 * Created by Chloe on 4/30/2018.
 */

public interface InterestContract {

    interface View extends BaseView<Presenter>{

        void showJobs(ArrayList<Jobs> jobs);

        void showJobsDetailUi(Jobs job);
    }

    interface Presenter extends BasePresenter{

        void result(int requestCode, int resultCode);

        void showJobs(ArrayList<Jobs> jobs);

        void loadJobs();

        void openCaseDetails(Jobs job);

        void refreshJobs();

//        void updateInterestJob(Jobs jobs, boolean isSaved);
    }

}
