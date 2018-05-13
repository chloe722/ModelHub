package thhsu.chloe.jeeva.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Chloe on 5/7/2018.
 */

public class ApiManager {
    public static final String BASE_URL = "https://wetogether.skijur.com/api/";
    public ApiJobsService apiJobsService;




    private static final ApiManager ourInstance = new ApiManager();
    public static ApiManager getInstance(){return ourInstance;}

    private ApiManager(){

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
