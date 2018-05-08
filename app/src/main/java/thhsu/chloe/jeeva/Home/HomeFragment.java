package thhsu.chloe.jeeva.Home;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import thhsu.chloe.jeeva.Jeeva;
import thhsu.chloe.jeeva.R;
import thhsu.chloe.jeeva.activities.JeevaActivity;
import thhsu.chloe.jeeva.adapters.HomeAdapter;
import thhsu.chloe.jeeva.api.model.Jobs;

/**
 * Created by Chloe on 5/1/2018.
 */

public class HomeFragment extends Fragment implements HomeContract.View {

    private HomeContract.Presenter mPresenter;
    private HomeAdapter mHomeAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHomeAdapter = new HomeAdapter(mPresenter, new ArrayList<Jobs>());

    }

    public static HomeFragment newInstance(){
        return new HomeFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadJobs();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.home_fragment_recycler_vertical);
        recyclerView.setLayoutManager(new LinearLayoutManager(Jeeva.getAppContext()));
        recyclerView.setAdapter(mHomeAdapter);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.start();
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }

    @Override
    public void showJobs(ArrayList<Jobs> jobs) {
        mHomeAdapter.updateData(jobs);
    }

    @Override
    public void showJobsDetailUi(Jobs job) {
        ((JeevaActivity)getActivity()).transToJobDetails(job);
    }

    @Override
    public void refreshUi() {
        // Save state
        Parcelable recyclerViewState;
        recyclerViewState = ((RecyclerView) getView().findViewById(R.id.home_fragment_recycler_vertical)).getLayoutManager().onSaveInstanceState();
        mHomeAdapter.notifyDataSetChanged();
        // Restore state
        ((RecyclerView) getView().findViewById(R.id.home_fragment_recycler_vertical)).getLayoutManager().onRestoreInstanceState(recyclerViewState);

    }

}
