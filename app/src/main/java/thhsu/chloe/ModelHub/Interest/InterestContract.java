package thhsu.chloe.ModelHub.Interest;

import java.util.ArrayList;

import thhsu.chloe.ModelHub.BasePresenter;
import thhsu.chloe.ModelHub.BaseView;
import thhsu.chloe.ModelHub.api.model.Cases;

/**
 * Created by Chloe on 4/30/2018.
 */

public interface InterestContract {

    interface View extends BaseView<Presenter>{

        void showCases(ArrayList<Cases> cases);

        void showCasesDetailUi(Cases acase);
    }

    interface Presenter extends BasePresenter{

        void result(int requestCode, int resultCode);

        void showCases(ArrayList<Cases> cases);

        void loadCases();

        void openCaseDetails(Cases acase);

        void refreshCases();

//        void updateInterestCase(Cases cases, boolean isSaved);
    }

}
