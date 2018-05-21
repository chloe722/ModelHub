package thhsu.chloe.jeeva.SavedJobs;

import android.view.ViewOutlineProvider;

import java.util.ArrayList;

import thhsu.chloe.jeeva.BasePresenter;
import thhsu.chloe.jeeva.BaseView;
import thhsu.chloe.jeeva.JeevaContract;
import thhsu.chloe.jeeva.api.model.Jobs;

/**
 * Created by Chloe on 4/30/2018.
 */

public interface SavedJobContract {

    interface View extends BaseView<Presenter>{

        void showJobs(ArrayList<Jobs> jobs);

        void showJobsDetailUi(Jobs job);
    }

    interface Presenter extends BasePresenter{

        void result(int requestCode, int resultCode);

        void showJobs(ArrayList<Jobs> jobs);

        void loadJobs();

        void openJobDetails(Jobs job);

        void refreshJobs();

//        void updateSavedJob(Jobs jobs, boolean isSaved);
    }

}
