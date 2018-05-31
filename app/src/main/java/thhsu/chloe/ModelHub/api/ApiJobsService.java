package thhsu.chloe.ModelHub.api;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
//import thhsu.chloe.jeeva.api.model.FilterJobs;
import thhsu.chloe.ModelHub.api.model.Jobs;
import thhsu.chloe.ModelHub.api.model.PostUserInfoResult;
import thhsu.chloe.ModelHub.api.model.RegisterResult;
import thhsu.chloe.ModelHub.api.model.Result;
import thhsu.chloe.ModelHub.api.model.UpdateUserRequest;
import thhsu.chloe.ModelHub.api.model.UserInfo;

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

    @GET(USERS_ME)
    Call<UserInfo> getUserData(@Query("token") String token);

    @POST(REGISTER)
    @FormUrlEncoded
    Call<RegisterResult> getRegister(@Field("name") String name,
                                     @Field("age") String age,
                                     @Field("gender") String gender,
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
    Call<PostUserInfoResult> getPostUserInfoResult(@Body UpdateUserRequest updateUserRequest);

    @Multipart
    @POST("image-upload")
    Call<UploadResponse> uploadImage(@Part("image") RequestBody image);

}
