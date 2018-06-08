package thhsu.chloe.ModelHub.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;

import thhsu.chloe.ModelHub.ModelHub;
import thhsu.chloe.ModelHub.api.ApiJobManager;
import thhsu.chloe.ModelHub.api.GetUserInfoCallBack;
import thhsu.chloe.ModelHub.api.model.User;
import thhsu.chloe.ModelHub.utils.Constants;

/**
 * Created by Chloe on 6/8/2018.
 */

public class ProfileInfoPresenter implements ProfileInfoContract.Presenter {

    private String mUserToken;
    private ProfileInfoContract.View mProfileInfoView;

    public ProfileInfoPresenter(ProfileInfoContract.View profileInfoFragment) {
        mProfileInfoView = profileInfoFragment;
        if(profileInfoFragment != null){
            mProfileInfoView.setPresenter(this);
        }
//        this.mUserToken = mUserToken;
    }

    @Override
    public void start() {}


    @Override
    public void showUserMoreInfo() {
        SharedPreferences sharedPreferences = ModelHub.getAppContext().getSharedPreferences(Constants.USER_DATA, Context.MODE_PRIVATE);
        mUserToken = sharedPreferences.getString(Constants.USER_TOKEN, "");

        if(!mUserToken.equals("")){
            ApiJobManager.getInstance().getUserData(mUserToken, new GetUserInfoCallBack() {
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
