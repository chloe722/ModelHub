package thhsu.chloe.ModelHub.details;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import thhsu.chloe.ModelHub.ModelHub;
import thhsu.chloe.ModelHub.R;
import thhsu.chloe.ModelHub.utils.Constants;
import thhsu.chloe.ModelHub.activities.ModelHubActivity;
import thhsu.chloe.ModelHub.adapters.JobDetailsAdapter;
import thhsu.chloe.ModelHub.api.model.Jobs;

/**
 * Created by Chloe on 5/6/2018.
 */

public class JobDetailsFragment extends Fragment implements JobDetailsContract.View, View.OnClickListener{

    private String mUserToken;
    private JobDetailsContract.Presenter mPresenter;
    private JobDetailsAdapter mJobDetailAdapter;
    private BottomNavigationView mBottomNavigationView;

    public static JobDetailsFragment newInstance(){
        return new JobDetailsFragment();
    }

    @Override
    public void setPresenter(JobDetailsContract.Presenter presenter) {
        if(presenter != null){
            mPresenter = presenter;
        }else{
            Log.d("Chloe", "Presenter is empty.");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mJobDetailAdapter = new JobDetailsAdapter(getContext(), new Jobs(), mPresenter);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.USER_DATA, Context.MODE_PRIVATE);
        mUserToken = sharedPreferences.getString(Constants.USER_TOKEN,"");
        mBottomNavigationView = (BottomNavigationView) getActivity().findViewById(R.id.bottom_navigation);
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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_job_details, container,false);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recyclerview_job_details);
        recyclerView.setLayoutManager(new LinearLayoutManager(ModelHub.getAppContext()));
        recyclerView.setAdapter(mJobDetailAdapter);
        Button applyBtn = (Button) root.findViewById(R.id.btn_job_details_apply);
        applyBtn.setOnClickListener(this);
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
        int currentSelectedItem = mBottomNavigationView.getSelectedItemId();

        if (currentSelectedItem == R.id.action_home){

            if (getActivity() != null) {
                ((ModelHubActivity) getActivity()).showHomeUi();
                ((ModelHubActivity) getActivity()).showFilterIcn();
            }
        }else{
            if (getActivity() != null) {
                ((ModelHubActivity) getActivity()).showInterestUi();
            }
        }

        ((ModelHubActivity) getActivity()).hideToolbarBackBtn();
        ((ModelHubActivity) getActivity()).showBtnNavView();
        ((ModelHubActivity) getActivity()).refreshInterestItemUi();

    }


    @Override
    public void showJobDetails(Jobs job) {
        mJobDetailAdapter.updateJobs(job);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_job_details_apply:
                if(!(mUserToken.equals(""))){
                    composeEmail();
                }else{
                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(getContext(), R.style.PopupDialog);
                    builder.setMessage("Oops! Seems you've not signed in.")
                            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();
                }
        }
    }

    public void composeEmail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, "chloe.thhsu@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Apply for Android developer position");
        startActivity(intent);
    }
}
