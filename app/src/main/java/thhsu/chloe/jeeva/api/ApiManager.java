package thhsu.chloe.jeeva.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Chloe on 5/7/2018.
 */

public class ApiManager {
    public static final String BASE_URL = "https://v2.wetogether.co/api/jobs";
    public ApiJobsService apiJobsService;

    public static final ApiManager ourInstance = new ApiManager();
    public static ApiManager getInstance(){return ourInstance;}

    public ApiManager(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor) // To register an application interceptor
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        apiJobsService = retrofit.create(ApiJobsService.class);
    }

}
