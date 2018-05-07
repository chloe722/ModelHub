package thhsu.chloe.jeeva.Home;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import thhsu.chloe.jeeva.BasePresenter;
import thhsu.chloe.jeeva.BaseView;
import thhsu.chloe.jeeva.api.model.Jobs;

/**
 * Created by Chloe on 5/1/2018.
 */

public interface HomeContract {

    interface View extends BaseView<Presenter>{

        void showJobs(ArrayList<Jobs> jobs);

        void showJobsDetailUi(String jobId);


    }

    interface Presenter extends BasePresenter{

        void result(int requestCode, int resultCode);

        void showJobs(ArrayList<Jobs> jobs);

        void loadJobs();

        void onScrollStateChanged(int visibleItemCount, int totalItemCount, int newState);

        void onScrolled(RecyclerView.LayoutManager layoutManager);

        void openJobDetails(String jobId);

    }
}
