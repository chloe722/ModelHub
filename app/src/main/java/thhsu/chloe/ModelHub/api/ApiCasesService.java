package thhsu.chloe.ModelHub.api;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
//import thhsu.chloe.jeeva.api.model.FilterJobs;
import thhsu.chloe.ModelHub.api.model.Cases;
import thhsu.chloe.ModelHub.api.model.PostUserInfoResult;
import thhsu.chloe.ModelHub.api.model.RegisterResult;
import thhsu.chloe.ModelHub.api.model.Result;
import thhsu.chloe.ModelHub.api.model.UpdataUserRequest;
import thhsu.chloe.ModelHub.api.model.UserInfo;

/**
 * Created by Chloe on 5/7/2018.
 */

public interface ApiCasesService {
    String  TAGS = "tags";
    String PATH = "cases";
    String REGISTER = "register";
    String TOKEN = "token";
    String USERS = "users";
    String ME = "me";

    String USERS_ME = USERS + "/" + ME;
    String USERS_ME_TOKEN = USERS + "/" + ME + "?" + TOKEN + "=";
    String PATH_JOBS = PATH;
    String PATH_JOBS_TAGS = PATH + "?";

    @GET(PATH_JOBS)
    Call<Result<ArrayList<Cases>>> getCases();

    @GET(PATH_JOBS_TAGS)
    Call<Result<ArrayList<Cases>>> getFilterCases(@Query("tags") String tags);

    @GET(USERS_ME)
    Call<UserInfo> getUserData(@Query("token") String token);

    @POST(REGISTER)
    @FormUrlEncoded
    Call<RegisterResult> getRegister(@Field("name") String name,
                                     @Field("email") String email,
                                     @Field("password") String password);

    // Call<return type>
    @POST(TOKEN)
    @FormUrlEncoded
    Call<RegisterResult> getLogInResult(@Field("email") String email,
                                        @Field("password") String password,
                                        @Field("grant_type") String grant_type);

    @POST(USERS_ME)
//    @Headers({"Content-Type: application/json"})
    Call<PostUserInfoResult> getPostUserInfoResult(@Body UpdataUserRequest updataUserRequest);

}
