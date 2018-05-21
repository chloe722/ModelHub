package thhsu.chloe.jeeva.api;

import thhsu.chloe.jeeva.api.model.User;
import thhsu.chloe.jeeva.api.model.UserInfo;

/**
 * Created by Chloe on 5/21/2018.
 */

public interface GetUserInfoCallBack {
    void onCompleted(User user);
    void onError(String errorMessage);
}
