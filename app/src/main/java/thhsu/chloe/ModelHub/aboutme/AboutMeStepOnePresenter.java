package thhsu.chloe.ModelHub.aboutme;

import android.content.Context;
import android.content.SharedPreferences;
import thhsu.chloe.ModelHub.ModelHub;
import thhsu.chloe.ModelHub.api.ApiJobManager;
import thhsu.chloe.ModelHub.api.GetUserInfoCallBack;
import thhsu.chloe.ModelHub.api.PostUserInfoCallBack;
import thhsu.chloe.ModelHub.api.model.UpdateUserRequest;
import thhsu.chloe.ModelHub.api.model.User;
import thhsu.chloe.ModelHub.utils.Constants;

/**
 * Created by Chloe on 6/9/2018.
 */

public class AboutMeStepOnePresenter implements AboutMeStepOneContract.Presenter {
    private AboutMeStepOneContract.View mView;

    public AboutMeStepOnePresenter(AboutMeStepOneContract.View view) {
        mView = view;
        if(view != null){
            mView.setPresenter(this);
        }
    }

    @Override
    public void start() {

    }

    @Override
    public void showUserInfoStepOne() {

        SharedPreferences sharedPreferences = ModelHub.getAppContext().getSharedPreferences(Constants.USER_DATA, Context.MODE_PRIVATE);
        String userToken = sharedPreferences.getString(Constants.USER_TOKEN, "");

        ApiJobManager.getInstance().getUserData(userToken, new GetUserInfoCallBack() {
            @Override
            public void onCompleted(User user) {
                mView.showUserInfoStepOneUi(user);
            }

            @Override
            public void onError(String errorMessage) {
            }
        });
    }


    @Override
    public void postUserInfoStepOne(UpdateUserRequest request){
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
