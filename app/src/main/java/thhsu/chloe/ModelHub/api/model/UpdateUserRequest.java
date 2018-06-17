package thhsu.chloe.ModelHub.api.model;

/**
 * Created by Chloe on 5/15/2018.
 */

public class UpdateUserRequest {
    public String token;
    public User user = new User();

    public UpdateUserRequest(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public UpdateUserRequest() {
    }
}

