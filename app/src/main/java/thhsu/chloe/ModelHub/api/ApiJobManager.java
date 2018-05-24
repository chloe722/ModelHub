package thhsu.chloe.ModelHub.api;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
//import thhsu.chloe.jeeva.api.model.FilterJobs;
import thhsu.chloe.ModelHub.ModelHub;
import thhsu.chloe.ModelHub.activities.ModelHubActivity;
import thhsu.chloe.ModelHub.activities.SignInActivity;
import thhsu.chloe.ModelHub.api.model.Jobs;
import thhsu.chloe.ModelHub.api.model.PostUserInfoResult;
import thhsu.chloe.ModelHub.api.model.RegisterResult;
import thhsu.chloe.ModelHub.api.model.Result;


import retrofit2.Callback;
import retrofit2.Response;
import thhsu.chloe.ModelHub.api.model.UpdataUserRequest;
import thhsu.chloe.ModelHub.api.model.UserInfo;


/**
 * Created by Chloe on 5/7/2018.
 */

public class ApiJobManager {
    SignInActivity mSignActivity;
    ModelHubActivity mModelHubActivity;
    private static final ApiJobManager ourInstance = new ApiJobManager();

    public static ApiJobManager getInstance(){return ourInstance;}

    private ApiJobManager(){

    }

    public void getJobs(final GetJobsCallBack jobsCallBack){

        Call<Result<ArrayList<Jobs>>> call = ApiManager.getInstance().apiJobsService.getJobs();

        call.enqueue(new Callback<Result<ArrayList<Jobs>>>() {
            @Override
            public void onResponse(Call<Result<ArrayList<Jobs>>> call, Response<Result<ArrayList<Jobs>>> response) {
                Log.d("Chloe", "onResponse");
                response.body();
                if(response.body().jobs != null){
                    jobsCallBack.onCompleted(response.body().jobs);
                }else{
                    Log.d("Chloe", "response.body.jobs is empty");
                }
            }

            @Override
            public void onFailure(Call<Result<ArrayList<Jobs>>> call, Throwable t) {
                Log.d("Chloe", "onFailure");
                jobsCallBack.onError(t.getLocalizedMessage());
            }
        });
    }

    public void getFilterJobs(String tags, final GetFilterJobsCallBack filterJobsCallBack){

        Call<Result<ArrayList<Jobs>>> call = ApiManager.getInstance().apiJobsService.getFilterJobs(tags);
        call.enqueue(new Callback<Result<ArrayList<Jobs>>>() {
            @Override
            public void onResponse(Call<Result<ArrayList<Jobs>>> call, Response<Result<ArrayList<Jobs>>> response) {
                Log.d("Chloe", "onResponse" + response.body());
                response.body();
                if(response.body().jobs != null){
                    filterJobsCallBack.onCompleted(response.body().jobs);
                }else{
                    Log.d("Chloe", "response.body.filterjobs is empty");
                }
            }

            @Override
            public void onFailure(Call<Result<ArrayList<Jobs>>> call, Throwable t) {
                Log.d("Chloe", "onFailure");
                filterJobsCallBack.onError(t.getLocalizedMessage());
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


    public void getRegister(String name, String email, String password,final PostRegisterLoginCallBack postRegisterLoginCallBack){
        Log.d("Chloe", "register");
        Call<RegisterResult> call = ApiManager.getInstance().apiJobsService.getRegister(name, email, password);
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
                    Toast.makeText(ModelHub.getAppContext(), "Email doesn't exist", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<RegisterResult> call, Throwable t) {
                postRegisterLoginCallBack.onError(t.getLocalizedMessage());
            }
        });
    }

    public void  getPostUserInfoResult(UpdataUserRequest updataUserRequest, final PostUserInfoCallBack postUserInfoCallBack){
        Call<PostUserInfoResult> call = ApiManager.getInstance().apiJobsService.getPostUserInfoResult(updataUserRequest);
        call.enqueue(new Callback<PostUserInfoResult>() {
            @Override
            public void onResponse(Call<PostUserInfoResult> call, Response<PostUserInfoResult> response) {
                response.body();
                if(response.body().getOk()){
                    postUserInfoCallBack.onComplete();
                    Log.d("Chloe", "Post user info success!: " + (response.body().getOk()));
                }else{
                    Toast.makeText(ModelHub.getAppContext(), "Post userinfo to server faild!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<PostUserInfoResult> call, Throwable t) {
                    postUserInfoCallBack.onError(t.getLocalizedMessage());
            }
        });
    }


}

