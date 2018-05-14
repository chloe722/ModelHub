package thhsu.chloe.jeeva.JobDetails;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import thhsu.chloe.jeeva.Jeeva;
import thhsu.chloe.jeeva.R;
import thhsu.chloe.jeeva.Utils.Constants;
import thhsu.chloe.jeeva.activities.JeevaActivity;
import thhsu.chloe.jeeva.adapters.JobDetailsAdapter;
import thhsu.chloe.jeeva.api.model.Jobs;

/**
 * Created by Chloe on 5/6/2018.
 */

public class JobDetailsFragment extends Fragment implements JobDetailsContract.View, View.OnClickListener{

    private JobDetailsContract.Presenter mPresenter;
    private JobDetailsAdapter mJobDetailAdapter;
    JeevaActivity mJeevaActivity;
    private Button mApplyBtn;
    private SharedPreferences sharedPreferences;
    String token;

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
        sharedPreferences = getActivity().getSharedPreferences(Constants.USER_DATA, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(Constants.USER_TOKEN,"");
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
        mApplyBtn = (Button) root.findViewById(R.id.job_details_apply_btn);
        mApplyBtn.setOnClickListener(this);
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
        ((JeevaActivity) getActivity()).showHomeUi();

    }



    @Override
    public void showJobDetails(Jobs job) {
        mJobDetailAdapter.updateJobs(job);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.job_details_apply_btn:
                if(!(token.equals(""))){
                    composeEmail();
                }else{
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(getContext(), R.style.PopupDialog);
                    } else {
                        builder = new AlertDialog.Builder(getContext());
                    }
                    builder.setMessage("Oops! Seems you've not logged in. ")
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
