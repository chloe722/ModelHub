package thhsu.chloe.jeeva.api.model;

/**
 * Created by Chloe on 5/15/2018.
 */

public class UpdataUserRequest {
    String token;
    User user;

    public UpdataUserRequest(String token, User user) {
        this.token = token;
        this.user = user;
    }

}
