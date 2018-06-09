package thhsu.chloe.ModelHub.aboutme;

import android.content.Context;
import thhsu.chloe.ModelHub.ModelHub;
import android.content.SharedPreferences;
import thhsu.chloe.ModelHub.api.model.User;
import thhsu.chloe.ModelHub.utils.Constants;
import thhsu.chloe.ModelHub.api.ApiJobManager;
import thhsu.chloe.ModelHub.api.GetUserInfoCallBack;
import thhsu.chloe.ModelHub.api.PostUserInfoCallBack;
import thhsu.chloe.ModelHub.api.model.UpdateUserRequest;


/**
 * Created by Chloe on 6/9/2018.
 */

public class AboutMeStepTwoPresenter implements AboutMeStepTwoContract.Presenter {

    private AboutMeStepTwoContract.View mView;

    public AboutMeStepTwoPresenter(AboutMeStepTwoContract.View view) {
        mView = view;
        if(view != null){
            mView.setPresenter(this);
        }
    }

    @Override
    public void start() {}

    @Override
    public void getUserDataStepTwo() {
        SharedPreferences sharedPreferences = ModelHub.getAppContext().getSharedPreferences(Constants.USER_DATA, Context.MODE_PRIVATE);
        String userToken = sharedPreferences.getString(Constants.USER_TOKEN, "");

        ApiJobManager.getInstance().getUserData(userToken, new GetUserInfoCallBack() {
            @Override
            public void onCompleted(User user) {
                mView.showUserDataStepTwo(user);

            }

            @Override
            public void onError(String errorMessage) {

            }
        });
    }

    @Override
    public void postUserInfoStepTwo(UpdateUserRequest request) {

        ApiJobManager.getInstance().getPostUserInfoResult(request, new PostUserInfoCallBack() {
            @Override
            public void onComplete() {
            }
            @Override
            public void onError(String errorMessage) {
            }
        });
    }


}
