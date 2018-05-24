package thhsu.chloe.ModelHub.api;

import thhsu.chloe.ModelHub.api.model.User;

/**
 * Created by Chloe on 5/21/2018.
 */

public interface GetUserInfoCallBack {
    void onCompleted(User user);
    void onError(String errorMessage);
}
