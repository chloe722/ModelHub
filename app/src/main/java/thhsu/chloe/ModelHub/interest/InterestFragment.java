package thhsu.chloe.ModelHub.interest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import thhsu.chloe.ModelHub.ModelHub;
import thhsu.chloe.ModelHub.R;
import thhsu.chloe.ModelHub.utils.Constants;
import thhsu.chloe.ModelHub.activities.ModelHubActivity;
import thhsu.chloe.ModelHub.adapters.InterestAdapter;
import thhsu.chloe.ModelHub.api.model.Jobs;

/**
 * Created by Chloe on 4/30/2018.
 */

public class InterestFragment extends Fragment implements InterestContract.View {

   private InterestContract.Presenter mPresenter;
   private InterestAdapter mInterestAdapter;

    public static InterestFragment newInstance(){return new InterestFragment();}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInterestAdapter = new InterestAdapter(new ArrayList<Jobs>(), mPresenter);
    }

    @Override
    public void onResume() {super.onResume();}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.USER_DATA, Context.MODE_PRIVATE);
        String userToken = sharedPreferences.getString(Constants.USER_TOKEN, "");

        if(userToken.equals("")){
            View root = inflater.inflate(R.layout.fragment_interest_visitor, container, false);
            return root;
        }else{
            View root = inflater.inflate(R.layout.fragment_interest, container, false);
            RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recyclerview_interest);
            recyclerView.setLayoutManager(new LinearLayoutManager(ModelHub.getAppContext()));
            recyclerView.setAdapter(mInterestAdapter);

//            if(mInterestAdapter.getItemCount() == 0){
//                interestText.setVisibility(View.VISIBLE);
//            } (mInterestAdapter.getItemCount() > 0){
//                interestText.setVisibility(View.GONE);
//            }

            return root;
        }
    }

    @Override
    public void setPresenter(InterestContract.Presenter presenter) {
        if(presenter != null){mPresenter = presenter;}
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.start();
    }


    @Override
    public void showJobs(ArrayList<Jobs> jobs) {
        mInterestAdapter.updateData(jobs);
    }

    @Override
    public void showJobsDetailUi(Jobs job) {
        if (getActivity() != null) {
            ((ModelHubActivity) getActivity()).transToJobDetails(job);
        }
    }

}
