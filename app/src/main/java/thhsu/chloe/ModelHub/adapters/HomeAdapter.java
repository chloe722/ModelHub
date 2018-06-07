package thhsu.chloe.ModelHub.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rbrooks.indefinitepagerindicator.IndefinitePagerIndicator;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import thhsu.chloe.ModelHub.home.HomeContract;
import thhsu.chloe.ModelHub.ModelHub;
import thhsu.chloe.ModelHub.R;
import thhsu.chloe.ModelHub.utils.CircleTransform;
import thhsu.chloe.ModelHub.utils.Constants;
import thhsu.chloe.ModelHub.api.model.Jobs;

/**
 * Created by Chloe on 5/1/2018.
 */

public class HomeAdapter extends RecyclerView.Adapter {
    private int mNextPaging;
    private String mUserToken;
    private ArrayList<Jobs> mJobs;
    private HomeContract.Presenter mPresenter;
    private IndefinitePagerIndicator mIndefinitePagerIndicator;

    public HomeAdapter(HomeContract.Presenter presenter, ArrayList<Jobs> jobs){
        mJobs = jobs;
        mPresenter = presenter;
        mNextPaging = Constants.FIRST_PAGING;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SharedPreferences sharedPreferences = ModelHub.getAppContext().getSharedPreferences(Constants.USER_DATA, Context.MODE_PRIVATE);
        mUserToken = sharedPreferences.getString(Constants.USER_TOKEN, "");

        if(viewType == Constants.VIEWTYPE_HOME_MAIN){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_home, parent, false);
            return new HomeMainItemViewHolder(view);
        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vertical_home, parent, false);
            return new HomeJobsItemViewHolder(view);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof HomeMainItemViewHolder){
            bindMainItem((HomeMainItemViewHolder) holder);
        }else if (holder instanceof HomeJobsItemViewHolder){
            bindHomeJobsItem((HomeJobsItemViewHolder) holder, position-1);
        }
    }

    @Override
    public int getItemCount() {
        return (isNextPagingExist())? mJobs.size() +1 : mJobs.size();}

    @Override
    public int getItemViewType(int position) {
        return (position == 0)? Constants.VIEWTYPE_HOME_MAIN : Constants.VIEWTYPE_HOME_JOB_LIST;
    }

    private class HomeMainItemViewHolder extends RecyclerView.ViewHolder{
        private RecyclerView mRecyclerUrgent;

        private HomeMainItemViewHolder(View itemView) {
            super(itemView);
            mRecyclerUrgent = (RecyclerView) itemView.findViewById(R.id.recyclerview_home_horizontal);
            mIndefinitePagerIndicator = (IndefinitePagerIndicator) itemView.findViewById(R.id.indicator_home_recyclerview_horizontal);

        }
        private RecyclerView getRecyclerRecommend(){return mRecyclerUrgent;}
    }

    private void bindMainItem(HomeMainItemViewHolder holder){
        holder.getRecyclerRecommend().setLayoutManager(new LinearLayoutManager(ModelHub.getAppContext(),
                LinearLayoutManager.HORIZONTAL, false));
        holder.getRecyclerRecommend().setOnFlingListener(null);
        new LinearSnapHelper().attachToRecyclerView(holder.getRecyclerRecommend());
        holder.getRecyclerRecommend().setAdapter(new HomeJobUrgentAdapter(mPresenter, mJobs));
        mIndefinitePagerIndicator.attachToRecyclerView(holder.getRecyclerRecommend());
    }

    private class HomeJobsItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTextViewHomeJobTitle, mTextViewHomeJobLocation, mTextViewHomeJobWhom, mTextViewHomeJobPay;
        private ImageView mImageViewHomeJobCompanyLogo;
        private ImageButton mImageBtnInterestIcn;

        private HomeJobsItemViewHolder(View itemView) {
            super(itemView);

            mTextViewHomeJobPay = (TextView) itemView.findViewById(R.id.textview_home_job_pay);
            mTextViewHomeJobTitle = (TextView) itemView.findViewById(R.id.textview_home_job_title);
            mTextViewHomeJobLocation = (TextView) itemView.findViewById(R.id.textview_home_job_location);
            mTextViewHomeJobWhom = (TextView) itemView.findViewById(R.id.textview_home_job_whom);
            mImageViewHomeJobCompanyLogo = (ImageView) itemView.findViewById(R.id.imageview_home_job_company_logo);
            mImageBtnInterestIcn = (ImageButton) itemView.findViewById(R.id.imagebtn_home_job_interest);

            mImageBtnInterestIcn.setOnClickListener(this);
            ((CardView) itemView.findViewById(R.id.cardView_main_case_item)).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Jobs jobs = mJobs.get(getAdapterPosition() - 1);

            switch (v.getId()) {
                case R.id.imagebtn_home_job_interest:
                    if (!mUserToken.equals("")) {
                        if (ModelHub.getModelHubSQLHelper().getInterest(jobs.getId())) {
                            mPresenter.updateInterest(jobs, false);
                            mImageBtnInterestIcn.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                            break;
                        } else {
                            mPresenter.updateInterest(jobs, true);
                            Log.d("Chloe", "is saved: " + ModelHub.getModelHubSQLHelper().getInterest(jobs.getId()));
                            mImageBtnInterestIcn.setImageResource(R.drawable.ic_favorite_black_24dp);
                            break;
                        }
                    } else {
                        Toast.makeText(ModelHub.getAppContext(), "You are not logged in yet", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.cardView_main_case_item:
                    mPresenter.openJobDetails(mJobs.get(getAdapterPosition() - 1)); // setOpenJob here  getAdapterPosition()
                    break;
            }

        }

        private TextView getHomeJobPay(){return mTextViewHomeJobPay;}
        private TextView getHomeJobWhom(){return mTextViewHomeJobWhom;}
        private TextView getHomeJobTitle(){return mTextViewHomeJobTitle;}
        private TextView getHomeJobLocation(){return mTextViewHomeJobLocation;}
        private ImageView getHomeJobCompanyLogo(){return mImageViewHomeJobCompanyLogo;}
        private ImageButton getInterestIcnBtn(){return mImageBtnInterestIcn;}
    }

    private void bindHomeJobsItem(HomeJobsItemViewHolder holder, int position) {
        holder.getHomeJobWhom().setText(mJobs.get(position).getWhom());
        holder.getHomeJobTitle().setText(mJobs.get(position).getTitle());
        holder.getHomeJobPay().setText(mJobs.get(position).getIsPaid());
        holder.getHomeJobLocation().setText(mJobs.get(position).getLocation());


        if (holder.getHomeJobCompanyLogo() != null && mJobs.get(position).getLogo() != null) {
            Picasso.get().load(mJobs.get(position).getLogo()).transform(new CircleTransform()).into(holder.getHomeJobCompanyLogo());
        }

        if (!mUserToken.equals("")) {
            if (ModelHub.getModelHubSQLHelper().getInterest(mJobs.get(position).getId())) {
                holder.getInterestIcnBtn().setImageResource(R.drawable.ic_favorite_black_24dp);
            } else {
                holder.getInterestIcnBtn().setImageResource(R.drawable.ic_favorite_border_black_24dp);
            }
        } else {
            holder.getInterestIcnBtn().setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }
    }

    public void updateData(ArrayList<Jobs> jobs){
        Log.d("Chloe", "HomeAdapter update data");
//        for (Jobs job : jobs){
//            mJobs.add(job);
//        }
        mJobs = jobs;
        setNextPaging(Constants.FIRST_PAGING);
        notifyDataSetChanged();

    }

    public void clearJobs(){
        mJobs.clear();
        notifyDataSetChanged();
    }

    private boolean isNextPagingExist(){
        return mNextPaging != -1;
    }
    private void setNextPaging(int nextPaging){mNextPaging = nextPaging;}
}
