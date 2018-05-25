package thhsu.chloe.ModelHub.Home;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import thhsu.chloe.ModelHub.BasePresenter;
import thhsu.chloe.ModelHub.BaseView;
//import thhsu.chloe.jeeva.api.model.FilterJobs;
import thhsu.chloe.ModelHub.api.model.Cases;

/**
 * Created by Chloe on 5/1/2018.
 */

public interface HomeContract {

    interface View extends BaseView<Presenter>{

        void showCases(ArrayList<Cases> cases);

//        void showFilterJobs(ArrayList<FilterJobs> filterJobs);

        void showCasesDetailUi(Cases acase);

        void refreshUi();

//        void clearCases();

    }

    interface Presenter extends BasePresenter{

        void result(int requestCode, int resultCode);

        void showCases(ArrayList<Cases> cases);

        void loadCases();

        void loadFilterResult();

//        void showFilterJobs(ArrayList<FilterJobs> filterJobs);

        void onScrollStateChanged(int visibleItemCount, int totalItemCount, int newState);

        void onScrolled(RecyclerView.LayoutManager layoutManager);

        void openCaseDetails(Cases acase);

        void refresh();

        void updateInterest(Cases cases, boolean isSaved);

//        void clearCases();


    }
}
