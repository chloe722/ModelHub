package thhsu.chloe.ModelHub.profileInfo;

import android.content.Context;
import android.content.SharedPreferences;

import thhsu.chloe.ModelHub.ModelHub;
import thhsu.chloe.ModelHub.api.ApiJobManager;
import thhsu.chloe.ModelHub.api.GetUserInfoCallBack;
import thhsu.chloe.ModelHub.api.model.User;
import thhsu.chloe.ModelHub.utils.Constants;

/**
 * Created by Chloe on 6/8/2018.
 */

public class ProfileInfoPresenter implements ProfileInfoContract.Presenter {

    private ProfileInfoContract.View mProfileInfoView;

    public ProfileInfoPresenter(ProfileInfoContract.View profileInfoFragment) {
        mProfileInfoView = profileInfoFragment;
        if(profileInfoFragment != null){
            mProfileInfoView.setPresenter(this);
        }
    }

    @Override
    public void start() {}


    @Override
    public void showUserMoreInfo() {
        SharedPreferences sharedPreferences = ModelHub.getAppContext().getSharedPreferences(Constants.USER_DATA, Context.MODE_PRIVATE);
        String userToken = sharedPreferences.getString(Constants.USER_TOKEN, "");

        if(!userToken.equals("")){
            ApiJobManager.getInstance().getUserData(userToken, new GetUserInfoCallBack() {
                @Override
                public void onCompleted(User user) {
                    mProfileInfoView.showUserMoreInfoUi(user);
                }

                @Override
                public void onError(String errorMessage) {

                }
            });
        }
    }
}
