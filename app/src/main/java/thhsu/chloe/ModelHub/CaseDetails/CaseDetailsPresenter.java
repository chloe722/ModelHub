package thhsu.chloe.ModelHub.CaseDetails;

import android.support.design.widget.BottomNavigationView;
import android.util.Log;

import thhsu.chloe.ModelHub.ModelHub;
import thhsu.chloe.ModelHub.activities.ModelHubActivity;
import thhsu.chloe.ModelHub.api.model.Cases;

/**
 * Created by Chloe on 5/6/2018.
 */

public class CaseDetailsPresenter implements CaseDetailsContract.Presenter {

    private final CaseDetailsContract.View mCaseDetailsView;
    ModelHubActivity mActivity;
    private Cases mCase;
    private BottomNavigationView mBottomNavigationView;


    public CaseDetailsPresenter(CaseDetailsContract.View CaseDetailsView, Cases acase, BottomNavigationView bottomNavigationView){
        mCaseDetailsView = CaseDetailsView;
        if (CaseDetailsView != null){
            mCaseDetailsView.setPresenter(this);
            mCase = acase;
            Log.d("Chloe", "Job details mCase: " + mCase);
            mBottomNavigationView = bottomNavigationView;
        }
    }

    @Override
    public void start() {
        loadCase();
    }

    @Override
    public void result(int requestCode, int resultCode) {
    }

    @Override
    public void loadCase() {
        mCaseDetailsView.showCaseDetails(mCase);
    }

    @Override
    public void updateInterestCase(Cases cases, boolean isSaved) {
        ModelHub.getModelHubSQLHelper().updateCases(mCase, isSaved);
    }


}
