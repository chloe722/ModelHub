package thhsu.chloe.ModelHub.SavedJobs;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import thhsu.chloe.ModelHub.ModelHub;
import thhsu.chloe.ModelHub.R;
import thhsu.chloe.ModelHub.Utils.Constants;
import thhsu.chloe.ModelHub.activities.ModelHubActivity;
import thhsu.chloe.ModelHub.adapters.SavedJobsAdapter;
import thhsu.chloe.ModelHub.api.model.Jobs;

/**
 * Created by Chloe on 4/30/2018.
 */

public class SavedJobsFragment extends Fragment implements SavedJobContract.View {


   private SavedJobContract.Presenter mPresenter;
   private SavedJobsAdapter mSavedJobsAdapter;
   SharedPreferences sharedPreferences;
   String token;

    public static SavedJobsFragment newInstance(){return new SavedJobsFragment();}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSavedJobsAdapter = new SavedJobsAdapter(new ArrayList<Jobs>(), mPresenter);
    }

    @Override
    public void onResume() {super.onResume();}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sharedPreferences = getActivity().getSharedPreferences(Constants.USER_DATA, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(Constants.USER_TOKEN,"");

        if(token.equals("")){
            View root = inflater.inflate(R.layout.fragment_saved_jobs_visitor, container, false);
            return root;
        }else{
            View root = inflater.inflate(R.layout.fragment_saved_jobs, container, false);
            RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.saved_job_recyclerview);
            recyclerView.setLayoutManager(new LinearLayoutManager(ModelHub.getAppContext()));
            recyclerView.setAdapter(mSavedJobsAdapter);
            TextView saveJobsText = root.findViewById(R.id.saved_jobs_text);

//            if(mSavedJobsAdapter.getItemCount() == 0){
//                saveJobsText.setVisibility(View.VISIBLE);
//            } (mSavedJobsAdapter.getItemCount() > 0){
//                saveJobsText.setVisibility(View.GONE);
//            }

            return root;
        }
    }

    @Override
    public void setPresenter(SavedJobContract.Presenter presenter) {
        if(presenter != null){mPresenter = presenter;}
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.start();
    }


    @Override
    public void showJobs(ArrayList<Jobs> jobs) {
        mSavedJobsAdapter.updateData(jobs);
    }

    @Override
    public void showJobsDetailUi(Jobs job) {
        ((ModelHubActivity) getActivity()).transToJobDetails(job);
    }
}
