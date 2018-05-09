package thhsu.chloe.jeeva.adapters;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import thhsu.chloe.jeeva.Home.HomeContract;
import thhsu.chloe.jeeva.Jeeva;
import thhsu.chloe.jeeva.R;
import thhsu.chloe.jeeva.Utils.Constants;
import thhsu.chloe.jeeva.api.model.Jobs;

/**
 * Created by Chloe on 5/1/2018.
 */

public class HomeAdapter extends RecyclerView.Adapter {

    private HomeContract.Presenter mPresenter;
    private ArrayList<Jobs> mJobs;


    public HomeAdapter(HomeContract.Presenter presenter, ArrayList<Jobs> jobs){
        mPresenter = presenter;
        this.mJobs = jobs;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

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
    public int getItemCount() {return mJobs.size()-1;}

    @Override
    public int getItemViewType(int position) {
        return (position == 0)? Constants.VIEWTYPE_HOME_MAIN : Constants.VIEWTYPE_HOME_JOB_LIST;
    }

    private class HomeMainItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private RecyclerView mRecyclerRecommend;
        public TextView mRecommendedTitle, mJobTitle;;


        public HomeMainItemViewHolder(View itemView) {
            super(itemView);

            mRecyclerRecommend = (RecyclerView) itemView.findViewById(R.id.home_horizontal_recyclerview);
            mRecommendedTitle = (TextView) itemView.findViewById(R.id.horizontal_recommend_title);
            mJobTitle = (TextView) itemView.findViewById(R.id.vertical_job_title);
            itemView.setOnClickListener(this);

        }

        public RecyclerView getRecyclerRecommend(){return mRecyclerRecommend;}
        public TextView getRecommendedTitle(){return mRecommendedTitle;}
        private TextView getJobTitle(){return mJobTitle;}

        @Override
        public void onClick(View v) {
//           mPresenter.openJobDetails(mJobs.get(getAdapterPosition()).getId()); // Pass getAdapterPosition here
        }
    }

    private void bindMainItem(HomeMainItemViewHolder holder){
        holder.getRecyclerRecommend().setLayoutManager(new LinearLayoutManager(Jeeva.getAppContext(),
                LinearLayoutManager.HORIZONTAL, false));
        holder.getRecyclerRecommend().setOnFlingListener(null);
        new LinearSnapHelper().attachToRecyclerView(holder.getRecyclerRecommend());
        holder.getRecyclerRecommend().setAdapter(new HomeJobRecommedAdapter());
    }


    private class HomeJobsItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mHomeJobTypeTag, mHomeJobTitle, mHomeJobPostedOnText,
                mHomeJobPostedDate, mHomeJobCompanyTitle, mHomeJobLocationTitle,
                mHomeJobCompanyName, mHomeJobLocationName, mHomeJobUrgentOrNotText ;
        public ImageView mHomeJobCompanyLogo, mHomeJobClockIcn;
        public ImageButton mSavedJobIcnBtn;

        public HomeJobsItemViewHolder(View itemView) {
            super(itemView);

            mHomeJobTypeTag = (TextView) itemView.findViewById(R.id.home_job_type_tag);
            mHomeJobTitle = (TextView) itemView.findViewById(R.id.home_job_title);
            mHomeJobPostedOnText = (TextView) itemView.findViewById(R.id.home_job_posted_text);
            mHomeJobPostedDate = (TextView) itemView.findViewById(R.id.home_job_posted_date);
            mHomeJobCompanyTitle = (TextView) itemView.findViewById(R.id.home_job_company_title);
            mHomeJobLocationTitle = (TextView) itemView.findViewById(R.id.home_job_location_title);
            mHomeJobCompanyName = (TextView) itemView.findViewById(R.id.home_job_company_name);
            mHomeJobLocationName = (TextView) itemView.findViewById(R.id.home_job_location_name);
            mHomeJobUrgentOrNotText = (TextView) itemView.findViewById(R.id.home_job_urgentornot_text);
            mHomeJobCompanyLogo = (ImageView) itemView.findViewById(R.id.home_job_company_logo);
            mSavedJobIcnBtn = (ImageButton) itemView.findViewById(R.id.home_job_savedJob_icn_btn);
            mHomeJobClockIcn = (ImageView) itemView.findViewById(R.id.home_clock_icn);

            mSavedJobIcnBtn.setOnClickListener(this);
            ((ConstraintLayout) itemView.findViewById(R.id.constraintlayout_home_job_item)).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d("Chloe", "adapterPosition: " + getAdapterPosition());

            if(v.getId() == R.id.home_job_savedJob_icn_btn){
                //setSOLite data here
                mSavedJobIcnBtn.setImageResource(R.drawable.ic_bookmark_red_24dp);
            }else{
                Log.d("Chloe", "getTitle in home adapter: " + mJobs.get(getAdapterPosition()).getTitle());
                mPresenter.openJobDetails(mJobs.get(getAdapterPosition()-1)); // setOpenJob here  getAdapterPosition()
            }
        }

        public TextView getHomeJobTypeTag(){return mHomeJobTypeTag;}
        public TextView getHomeJobTitle(){return mHomeJobTitle;}
        public TextView getHomeJobPostedOnText(){return mHomeJobPostedOnText;}
        public TextView getHomeJobPostedDate(){return mHomeJobPostedDate;}
        public TextView getHomeJobCompanyTitle(){return mHomeJobCompanyTitle;}
        public TextView getHomeJobLocationTitle(){return mHomeJobLocationTitle;}
        public TextView getHomeJobCompanyName(){return mHomeJobCompanyName;}
        public TextView getHomeJobLocationName(){return mHomeJobLocationName;}
        public TextView getHomeJobUrgentOrNotText(){return mHomeJobUrgentOrNotText;}
        public ImageView getHomeJobCompanyLogo(){return mHomeJobCompanyLogo;}
        public ImageButton getSavedJobIcnBtn(){return mSavedJobIcnBtn;}
    }

    private void bindHomeJobsItem(HomeJobsItemViewHolder holder, int position){
        holder.getHomeJobTypeTag().setText(mJobs.get(position).getType());
        holder.getHomeJobCompanyName().setText(mJobs.get(position).getCompany());
        holder.getHomeJobLocationName().setText(mJobs.get(position).getLocation());
        holder.getHomeJobPostedDate().setText(mJobs.get(position).getDatePosted());
        holder.getHomeJobTitle().setText(mJobs.get(position).getTitle());
        if (mJobs.get(position).getType().equals("fulltime")){
            (holder.getHomeJobTypeTag()).setText("Full-time");
            (holder.getHomeJobTypeTag()).setBackgroundResource(R.drawable.yellow_rounded_shape);
        }else if(mJobs.get(position).getType().equals("parttime")){
            (holder.getHomeJobTypeTag()).setText("Part-time");
        }else if(mJobs.get(position).getType().equals("intern")){
            (holder.getHomeJobTypeTag()).setText("Intern");
        }
        if(mJobs.get(position).getUrgent()){
            (holder.mHomeJobClockIcn).setImageResource(R.drawable.ic_access_alarms_black_24dp);
            (holder.getHomeJobUrgentOrNotText()).setText("Urgent");
            (holder.getHomeJobUrgentOrNotText()).setTextColor(Color.rgb(247,59,59));
            }

//        if(mJobs.get(position).getLogo() == null){
//            (holder.mHomeJobCompanyLogo).setVisibility(View.GONE);
//
//        }else{
//
//        }
        if(holder.getHomeJobCompanyLogo() != null && mJobs.get(position).getLogo() != null) {
            Picasso.get().load(mJobs.get(position).getLogo()).into(holder.getHomeJobCompanyLogo());
        }


    }


    public void updateData(ArrayList<Jobs> jobs){
        Log.d("Chloe", "HomeAdapter update data");
        for (Jobs job : jobs){
            mJobs.add(job);
        }
        notifyDataSetChanged();
    }

    public void clearJobs(){
        mJobs.clear();
        notifyDataSetChanged();
    }


}
