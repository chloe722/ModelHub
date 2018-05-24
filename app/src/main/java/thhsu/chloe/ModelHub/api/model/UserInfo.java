package thhsu.chloe.ModelHub.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Chloe on 5/21/2018.
 */

public class UserInfo {
    @SerializedName("ok")
    @Expose
    private Boolean ok;

    @SerializedName("user")
    @Expose
    private User user;


    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
