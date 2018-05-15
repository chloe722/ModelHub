package thhsu.chloe.jeeva.api;

import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
//import thhsu.chloe.jeeva.api.model.FilterJobs;
import thhsu.chloe.jeeva.api.model.Jobs;
import thhsu.chloe.jeeva.api.model.PostUserInfoResult;
import thhsu.chloe.jeeva.api.model.RegisterResult;
import thhsu.chloe.jeeva.api.model.Result;
import thhsu.chloe.jeeva.api.model.UpdataUserRequest;
import thhsu.chloe.jeeva.api.model.User;

/**
 * Created by Chloe on 5/7/2018.
 */

public interface ApiJobsService {
    String  TAGS = "tags";
    String PATH = "jobs";
    String REGISTER = "register";
    String TOKEN = "token";
    String USERS = "users";
    String ME = "me";

    String USERS_ME = USERS + "/" + ME;
    String USERS_ME_TOKEN = USERS + "/" + ME + "?" + TOKEN + "=";
    String PATH_JOBS = PATH;
    String PATH_JOBS_TAGS = PATH + "?";

    @GET(PATH_JOBS)
    Call<Result<ArrayList<Jobs>>> getJobs();

    @GET(PATH_JOBS_TAGS)
    Call<Result<ArrayList<Jobs>>> getFilterJobs(@Query("tags") String tags);

    @GET(USERS_ME_TOKEN)
    Call<User> getUserData(@Query("token") String token);

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
    @Headers({"Content-Type: application/json"})
    Call<PostUserInfoResult> getPostUserInfoResult(@Body UpdataUserRequest updataUserRequest);






}
