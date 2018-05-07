package thhsu.chloe.jeeva.JobDetails;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import thhsu.chloe.jeeva.Jeeva;
import thhsu.chloe.jeeva.R;
import thhsu.chloe.jeeva.adapters.JobDetailsAdapter;

/**
 * Created by Chloe on 5/6/2018.
 */

public class JobDetailsFragment extends Fragment implements JobDetailsContract.View{

    private JobDetailsContract.Presenter mPresenter;
    private JobDetailsAdapter mJobDetailAdapter;

    public static JobDetailsFragment newInstance(){return new JobDetailsFragment();}

    @Override
    public void setPresenter(JobDetailsContract.Presenter presenter) {
        if(presenter != null){
            mPresenter = presenter;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mJobDetailAdapter = new JobDetailsAdapter(getContext(), mPresenter);
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
//            mPresenter.start();


    }



    @Override
    public void showJobDetails() {

    }
}
