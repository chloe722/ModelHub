package thhsu.chloe.jeeva.adapters;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import com.rbrooks.indefinitepagerindicator.IndefinitePagerIndicator;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import thhsu.chloe.jeeva.Home.HomeContract;
import thhsu.chloe.jeeva.Jeeva;
import thhsu.chloe.jeeva.R;
import thhsu.chloe.jeeva.Utils.CircleTransform;
import thhsu.chloe.jeeva.Utils.Constants;
import thhsu.chloe.jeeva.Utils.LinePagerIndicatorDecoration;
import thhsu.chloe.jeeva.activities.JeevaActivity;
import thhsu.chloe.jeeva.api.model.Jobs;

/**
 * Created by Chloe on 5/1/2018.
 */

public class HomeAdapter extends RecyclerView.Adapter {

    private HomeContract.Presenter mPresenter;
    private ArrayList<Jobs> mJobs;
    SharedPreferences sharedPreferences;
    String token;
    private int mNextPaging;
    public IndefinitePagerIndicator indefinitePagerIndicator;

    public HomeAdapter(HomeContract.Presenter presenter, ArrayList<Jobs> jobs){
        mPresenter = presenter;
        this.mJobs = jobs;
        this.mNextPaging = Constants.FIRST_PAGING;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        sharedPreferences = Jeeva.getAppContext().getSharedPreferences(Constants.USER_DATA, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(Constants.USER_TOKEN, "");

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
        private RecyclerView mRecyclerRecommend;
        public TextView mRecommendedTitle, mJobTitle;


        public HomeMainItemViewHolder(View itemView) {
            super(itemView);

            mRecyclerRecommend = (RecyclerView) itemView.findViewById(R.id.home_horizontal_recyclerview);
            indefinitePagerIndicator = (IndefinitePagerIndicator) itemView.findViewById(R.id.recyclerview_pager_indicator);
            mRecommendedTitle = (TextView) itemView.findViewById(R.id.horizontal_recommend_title);
//            mJobTitle = (TextView) itemView.findViewById(R.id.vertical_job_title);
//            itemView.setOnClickListener(this);

        }

        public RecyclerView getRecyclerRecommend(){return mRecyclerRecommend;}
        public TextView getRecommendedTitle(){return mRecommendedTitle;}
        private TextView getJobTitle(){return mJobTitle;}

    }

    private void bindMainItem(HomeMainItemViewHolder holder){
        holder.getRecyclerRecommend().setLayoutManager(new LinearLayoutManager(Jeeva.getAppContext(),
                LinearLayoutManager.HORIZONTAL, false));
        holder.getRecyclerRecommend().setOnFlingListener(null);
        new LinearSnapHelper().attachToRecyclerView(holder.getRecyclerRecommend());
        holder.getRecyclerRecommend().setAdapter(new HomeJobRecommendAdapter(mPresenter, mJobs));
//        holder.getRecyclerRecommend().addItemDecoration(new LinePagerIndicatorDecoration());
        indefinitePagerIndicator.attachToRecyclerView(holder.getRecyclerRecommend());
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
            mHomeJobTitle = (TextView) itemView.findViewById(R.id.saved_job_title);
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
            ((ConstraintLayout) itemView.findViewById(R.id.constraintlayout_saved_job_item)).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Jobs job = mJobs.get(getAdapterPosition()-1);
            Log.d("Chloe", "adapterPosition: " + (getAdapterPosition()-1));

            switch (v.getId()){
                case R.id.home_job_savedJob_icn_btn:
                    if(!token.equals("")){
                        if(Jeeva.getJeevaSQLHelper().getSavedJob(job.getId())){
                            mPresenter.updateSavedJob(job, false);
                            mSavedJobIcnBtn.setImageResource(R.drawable.ic_bookmark_border_red_24dp);
                            break;
                        }else{
                            mPresenter.updateSavedJob(job, true);
                            Log.d("Chloe", "is saved: " +Jeeva.getJeevaSQLHelper().getSavedJob(job.getId()) );
                            mSavedJobIcnBtn.setImageResource(R.drawable.ic_bookmark_red_24dp);
                            break;
                        }
                    }else{
                        Toast.makeText(Jeeva.getAppContext(), "You are not logged in yet", Toast.LENGTH_SHORT).show();
                    }
                break;

                case R.id.constraintlayout_saved_job_item:
                    Log.d("Chloe", "getTitle in home adapter: " + mJobs.get(getAdapterPosition()).getTitle());
                    mPresenter.openJobDetails(mJobs.get(getAdapterPosition()-1)); // setOpenJob here  getAdapterPosition()
                    break;
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
//            (holder.getHomeJobTypeTag()).setBackgroundResource(R.drawable.yellow_rounded_shape);
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
        if(holder.getHomeJobCompanyLogo() != null && mJobs.get(position).getLogo() != null) {
            Picasso.get().load(mJobs.get(position).getLogo()).transform(new CircleTransform()).into(holder.getHomeJobCompanyLogo());
        }

        Log.d("Chloe", "postiton: " + position );
        Log.d("Chloe", "postiton ID: " + mJobs.get(position).getId());
        Log.d("Chloe", "postiton title: " + mJobs.get(position).getTitle());
        if( !token.equals("")){
            if(Jeeva.getJeevaSQLHelper().getSavedJob(mJobs.get(position).getId())){
                Log.d("Chloe", "true");
                holder.getSavedJobIcnBtn().setImageResource(R.drawable.ic_bookmark_red_24dp);
            }else{
                Log.d("Chloe", "false");
                holder.getSavedJobIcnBtn().setImageResource(R.drawable.ic_bookmark_border_red_24dp);
            }
        }else{
            holder.getSavedJobIcnBtn().setImageResource(R.drawable.ic_bookmark_border_red_24dp);
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

    public boolean isNextPagingExist(){
        return (mNextPaging == -1)? false:true;
    }

    private void setNextPaging(int nextPaging){mNextPaging = nextPaging;}


}
