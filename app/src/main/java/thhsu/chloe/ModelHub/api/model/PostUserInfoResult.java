package thhsu.chloe.ModelHub.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Chloe on 5/15/2018.
 */

public class PostUserInfoResult {

    @SerializedName("ok")
    @Expose
    private Boolean ok;

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }
}
