package thhsu.chloe.jeeva.api;

import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
//import thhsu.chloe.jeeva.api.model.FilterJobs;
import thhsu.chloe.jeeva.api.model.Jobs;
import thhsu.chloe.jeeva.api.model.RegisterResult;
import thhsu.chloe.jeeva.api.model.Result;


import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Chloe on 5/7/2018.
 */

public class ApiJobManager {
    private static final ApiJobManager ourInstance = new ApiJobManager();

    public static ApiJobManager getInstance(){return ourInstance;}

    private ApiJobManager(){}

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
                Log.d("Chloe", "onResponse");
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

    public void getRegister(String email, String password,final PostRegisterLoginCallBack postRegisterLoginCallBack){
        Log.d("Chloe", "register");
        Call<RegisterResult> call = ApiManager.getInstance().apiJobsService.getRegister("", email, password);
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
                if(response.body().getToken() != null){
                    postRegisterLoginCallBack.onCompleted(response.body().getToken());
                    Log.d("Chloe", "LogIn get token: " + (response.body().getToken()));
                }
            }
            @Override
            public void onFailure(Call<RegisterResult> call, Throwable t) {
                postRegisterLoginCallBack.onError(t.getLocalizedMessage());
            }
        });
    }
}

