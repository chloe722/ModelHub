package thhsu.chloe.ModelHub.CaseDetails;

import thhsu.chloe.ModelHub.BasePresenter;
import thhsu.chloe.ModelHub.BaseView;
import thhsu.chloe.ModelHub.api.model.Cases;

/**
 * Created by Chloe on 5/6/2018.
 */

public interface CaseDetailsContract {

    interface View extends BaseView<Presenter>{

        void showCaseDetails(Cases acase);
    }

    interface Presenter extends BasePresenter{
        void result(int requestCode, int resultCode);

        void loadCase();

        void updateInterestCase(Cases cases, boolean isSaved);

    }
}
