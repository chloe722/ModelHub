package thhsu.chloe.ModelHub.Interest;

import java.util.ArrayList;

import thhsu.chloe.ModelHub.ModelHub;
import thhsu.chloe.ModelHub.api.GetInterestCallBack;
import thhsu.chloe.ModelHub.api.GetInterestTask;
import thhsu.chloe.ModelHub.api.model.Cases;

/**
 * Created by Chloe on 4/30/2018.
 */

public class InterestPresenter implements InterestContract.Presenter{

    private InterestContract.View mInterestView;
    ArrayList<Cases> mJobs;
    public InterestPresenter(InterestContract.View savedJobView){
        mInterestView = savedJobView;
        if(savedJobView != null){
            mInterestView.setPresenter(this);
        }
    }

    @Override
    public void start() {
        loadCases();
    }

    @Override
    public void result(int requestCode, int resultCode) {
    }

    @Override
    public void showCases(ArrayList<Cases> cases) {
        mInterestView.showCases(cases);

    }

    @Override
    public void loadCases() {
        new GetInterestTask(new GetInterestCallBack() {
            @Override
            public void onCompleted(ArrayList<Cases> cases) {
                showCases(cases);

            }
        }).execute();
    }

    @Override
    public void openCaseDetails(Cases acase) {
        mInterestView.showCasesDetailUi(acase);
    }

    @Override
    public void refreshCases() {
        loadCases();
        ModelHub.getModelHubSQLHelper().setInterestChanged(false);
    }

//    @Override
//    public void updateInterestCase(Cases cases, boolean isSaved) {
//        ModelHub.getModelHubSQLHelper().updateCases(mJobs, isSaved);
//    }
}
