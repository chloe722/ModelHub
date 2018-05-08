package thhsu.chloe.jeeva.JobDetails;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import thhsu.chloe.jeeva.Jeeva;
import thhsu.chloe.jeeva.R;
import thhsu.chloe.jeeva.activities.JeevaActivity;
import thhsu.chloe.jeeva.adapters.JobDetailsAdapter;
import thhsu.chloe.jeeva.api.model.Jobs;

/**
 * Created by Chloe on 5/6/2018.
 */

public class JobDetailsFragment extends Fragment implements JobDetailsContract.View{

    private JobDetailsContract.Presenter mPresenter;
    private JobDetailsAdapter mJobDetailAdapter;
    JeevaActivity mJeevaActivity;

    public static JobDetailsFragment newInstance(){
        return new JobDetailsFragment();
    }

    @Override
    public void setPresenter(JobDetailsContract.Presenter presenter) {
        if(presenter != null){
            mPresenter = presenter;
        }else{
            Log.d("Chloe", "Presenter is empty!!!!!!!!!");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mJobDetailAdapter = new JobDetailsAdapter(getContext(), new Jobs(), mPresenter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mPresenter != null) mPresenter.start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode,resultCode);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_job_details, container,false);

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.job_details_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(Jeeva.getAppContext()));
        recyclerView.setAdapter(mJobDetailAdapter);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(mPresenter != null) mPresenter.start();
    }

    @Override
    public void onDestroy() {  //When the fragment finish, run the code below.  onDestroy --> Fragment has finished or got destroyed
        super.onDestroy();
        ((JeevaActivity) getActivity()).showBtnNavView();
        ((JeevaActivity) getActivity()).showFilterIcn();
        ((JeevaActivity) getActivity()).hideToolbarBackBtn();

    }



    @Override
    public void showJobDetails(Jobs job) {
        mJobDetailAdapter.updateJobs(job);
    }
}
