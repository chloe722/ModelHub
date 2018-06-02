package thhsu.chloe.ModelHub.Home;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import thhsu.chloe.ModelHub.BasePresenter;
import thhsu.chloe.ModelHub.BaseView;
//import thhsu.chloe.jeeva.api.model.FilterJobs;
import thhsu.chloe.ModelHub.api.model.Jobs;

/**
 * Created by Chloe on 5/1/2018.
 */

public interface HomeContract {

    interface View extends BaseView<Presenter>{

        void showJobs(ArrayList<Jobs> jobs);

//        void showFilterJobs(ArrayList<FilterJobs> filterJobs);

        void showJobsDetailUi(Jobs job);

        void refreshUi();

//        void clearJobs();

    }

    interface Presenter extends BasePresenter{

        void result(int requestCode, int resultCode);

        void showJobs(ArrayList<Jobs> jobs);

        void loadJobs();

        void loadFilterResult();

//        void showFilterJobs(ArrayList<FilterJobs> filterJobs);

        void onScrollStateChanged(int visibleItemCount, int totalItemCount, int newState);

        void onScrolled(RecyclerView.LayoutManager layoutManager);

        void openCaseDetails(Jobs job);

        void refresh();

        void updateInterest(Jobs jobs, boolean isSaved);

        void refreshJobs();

//        void clearJobs();


    }
}
