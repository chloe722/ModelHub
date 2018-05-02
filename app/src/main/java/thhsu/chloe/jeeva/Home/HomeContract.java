package thhsu.chloe.jeeva.Home;

import android.support.v7.widget.RecyclerView;

import thhsu.chloe.jeeva.BasePresenter;
import thhsu.chloe.jeeva.BaseView;

/**
 * Created by Chloe on 5/1/2018.
 */

public interface HomeContract {

    interface View extends BaseView<Presenter>{

        void showJobsList();

        void showJobsDetailUi();


    }

    interface Presenter extends BasePresenter{

        void result(int requestCode, int resultCode);

        void showJobsList();

        void onScrollStateChanged(int visibleItemCount, int totalItemCount, int newState);

        void onScrolled(RecyclerView.LayoutManager layoutManager);

        void openJobDetails();

    }
}
