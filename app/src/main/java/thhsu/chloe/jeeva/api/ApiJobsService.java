package thhsu.chloe.jeeva.api;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import thhsu.chloe.jeeva.api.model.FilterJobs;
import thhsu.chloe.jeeva.api.model.Jobs;
import thhsu.chloe.jeeva.api.model.Result;

/**
 * Created by Chloe on 5/7/2018.
 */

public interface ApiJobsService {
    String  TAGS = "tags";
    String PATH = "jobs";

    String PATH_JOBS = PATH;
    String PATH_JOBS_TAGS = PATH + "?" + TAGS + "=";

    @GET(PATH_JOBS)
    Call<Result<ArrayList<Jobs>>> getJobs();

    @GET(PATH_JOBS_TAGS)
    Call<Result<ArrayList<FilterJobs>>> getFilterJobs();

}
