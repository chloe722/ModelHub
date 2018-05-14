package thhsu.chloe.jeeva.User;

import android.content.Context;

import thhsu.chloe.jeeva.Jeeva;
import thhsu.chloe.jeeva.Utils.Constants;
import thhsu.chloe.jeeva.api.PostRegisterLoginCallBack;
import thhsu.chloe.jeeva.api.model.RegisterResult;

/**
 * Created by Chloe on 5/14/2018.
 */

public class UserManager {
    private static final UserManager ourInstance = new UserManager();

    private boolean mLoginStatus;
    private String mUserToken;
    private String mUserEmail;

    private UserManager(){}


    private void loginJeeva(String token, final PostRegisterLoginCallBack postRegisterLoginCallBack){
        new PostRegisterLoginCallBack() {
            @Override
            public void onCompleted(String token) {
                Jeeva.getAppContext().getSharedPreferences(Constants.USER_DATA, Context.MODE_PRIVATE).edit();
            }

            @Override
            public void onError(String errorMessage) {

            }
        };
    }




}
