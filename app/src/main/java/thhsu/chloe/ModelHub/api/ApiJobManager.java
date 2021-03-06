package thhsu.chloe.ModelHub.api;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import thhsu.chloe.ModelHub.ModelHub;
import thhsu.chloe.ModelHub.api.model.Jobs;
import thhsu.chloe.ModelHub.api.model.PostUserInfoResult;
import thhsu.chloe.ModelHub.api.model.RegisterResult;
import thhsu.chloe.ModelHub.api.model.Result;
import thhsu.chloe.ModelHub.api.model.UpdateUserRequest;
import thhsu.chloe.ModelHub.api.model.UserInfo;

//import thhsu.chloe.jeeva.api.model.FilterJobs;


/**
 * Created by Chloe on 5/7/2018.
 */

public class ApiJobManager {
    private static final ApiJobManager ourInstance = new ApiJobManager();

    public static ApiJobManager getInstance(){return ourInstance;}

    private ApiJobManager(){

    }

    public void getJobs(final GetJobsCallBack casesCallBack){

        Call<Result<ArrayList<Jobs>>> call = ApiManager.getInstance().apiJobsService.getJobs();

        call.enqueue(new Callback<Result<ArrayList<Jobs>>>() {
            @Override
            public void onResponse(Call<Result<ArrayList<Jobs>>> call, Response<Result<ArrayList<Jobs>>> response) {
                Log.d("Chloe", "onResponse");
                response.body();
                Log.d("Chloe", "response.body" + response.body());
                if(response.body().jobs != null){
                    casesCallBack.onCompleted(response.body().jobs);
                }else{
                    Log.d("Chloe", "response.body.jobs is empty");
                }
            }

            @Override
            public void onFailure(Call<Result<ArrayList<Jobs>>> call, Throwable t) {
                Log.d("Chloe", "onFailure");
                casesCallBack.onError(t.getLocalizedMessage());
            }
        });
    }

    public void getFilterJobs(String tags, final GetFilterJobsCallBack filterCasesCallBack){

        Call<Result<ArrayList<Jobs>>> call = ApiManager.getInstance().apiJobsService.getFilterJobs(tags);
        call.enqueue(new Callback<Result<ArrayList<Jobs>>>() {
            @Override
            public void onResponse(Call<Result<ArrayList<Jobs>>> call, Response<Result<ArrayList<Jobs>>> response) {
                Log.d("Chloe", "onResponse" + response.body());
                response.body();
                if(response.body().jobs != null){
                    filterCasesCallBack.onCompleted(response.body().jobs);
                }else{
                    Log.d("Chloe", "response.body.filtercases is empty");
                }
            }

            @Override
            public void onFailure(Call<Result<ArrayList<Jobs>>> call, Throwable t) {
                Log.d("Chloe", "onFailure");
                filterCasesCallBack.onError(t.getLocalizedMessage());
            }
        });
    }

    public void getUserData(String token, final GetUserInfoCallBack getUserInfoCallBack){
        Log.d("Chloe", "get user info");
        Call<UserInfo> call = ApiManager.getInstance().apiJobsService.getUserData(token);
        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                response.body().getUser();
                if((response.body().getUser()) != null){
                    getUserInfoCallBack.onCompleted(response.body().getUser());
                }else{
                    Log.d("Chloe", "response body is empty");
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                    getUserInfoCallBack.onError(t.getLocalizedMessage());
            }
        });
    }


    public void getRegister(String name, String age, String gender, String email, String password,final PostRegisterLoginCallBack postRegisterLoginCallBack){
        Log.d("Chloe", "register");
        Call<RegisterResult> call = ApiManager.getInstance().apiJobsService.getRegister(name, age, gender,  email, password);
        call.enqueue(new Callback<RegisterResult>() {
            @Override
            public void onResponse(Call<RegisterResult> call, Response<RegisterResult> response) {
                response.body();
                if(response.body().getToken() != null){
                    postRegisterLoginCallBack.onCompleted(response.body().getToken());
                    Log.d("Chloe", "Register get token: " + (response.body().getToken()));
                }
            }

            @Override
            public void onFailure(Call<RegisterResult> call, Throwable t) {
                postRegisterLoginCallBack.onError(t.getLocalizedMessage());
            }
        });
    }

    public void getLogInResult(String email, String password, final PostRegisterLoginCallBack postRegisterLoginCallBack){
        Call<RegisterResult> call = ApiManager.getInstance().apiJobsService.getLogInResult(email, password, "credentials");
        call.enqueue(new Callback<RegisterResult>() {
            @Override
            public void onResponse(Call<RegisterResult> call, Response<RegisterResult> response) {
                response.body();
                if (response.body() != null){
                    if(response.body().getToken() != null){
                        postRegisterLoginCallBack.onCompleted(response.body().getToken());
                        Log.d("Chloe", "LogIn get token: " + (response.body().getToken()));
                    }
                }else{
                    Gson gson = new Gson();
                    ErrorResponse r = gson.fromJson(response.errorBody().charStream(),ErrorResponse.class);
                    Toast.makeText(ModelHub.getAppContext(), r.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<RegisterResult> call, Throwable t) {
                postRegisterLoginCallBack.onError(t.getLocalizedMessage());
            }
        });
    }

    public void  getPostUserInfoResult(UpdateUserRequest updateUserRequest, final PostUserInfoCallBack postUserInfoCallBack){
        Call<PostUserInfoResult> call = ApiManager.getInstance().apiJobsService.getPostUserInfoResult(updateUserRequest);
        call.enqueue(new Callback<PostUserInfoResult>() {
            @Override
            public void onResponse(@NonNull Call<PostUserInfoResult> call, @NonNull Response<PostUserInfoResult> response) {
                response.body();
                if(response.body().getOk()){
                    postUserInfoCallBack.onComplete();
                    Log.d("Chloe", "Post user info success!: " + (response.body().getOk()));
                }else{
                Toast.makeText(ModelHub.getAppContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
            }
            }
            @Override
            public void onFailure(Call<PostUserInfoResult> call, Throwable t) {
                    postUserInfoCallBack.onError(t.getLocalizedMessage());
            }
        });
    }

    public void upLoadImage(File image, final UploadImageCallBack uploadImageCallBack){
        RequestBody b = RequestBody.create(MediaType.parse("multipart/form-data"), image);
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", image.getName(), b);
        Call<UploadResponse> call = ApiManager.getInstance().apiJobsService.uploadImage(body);
        call.enqueue(new Callback<UploadResponse>() {
            @Override
            public void onResponse(Call<UploadResponse> call, Response<UploadResponse> response) {
                if(response.body().ok) {
                    uploadImageCallBack.onComplete(response.body().url);
                }else{
                    Toast.makeText(ModelHub.getAppContext(), response.body().message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UploadResponse> call, Throwable t) {
                Log.d("Chloe","upload error: " + t);
            }
        });
    }


}

