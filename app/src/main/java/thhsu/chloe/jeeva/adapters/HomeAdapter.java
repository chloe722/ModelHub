package thhsu.chloe.jeeva.adapters;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
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

import org.w3c.dom.Text;

import java.util.ArrayList;

import thhsu.chloe.jeeva.Home.HomeContract;
import thhsu.chloe.jeeva.Jeeva;
import thhsu.chloe.jeeva.R;
import thhsu.chloe.jeeva.Utils.Constants;

/**
 * Created by Chloe on 5/1/2018.
 */

public class HomeAdapter extends RecyclerView.Adapter {

    public ArrayList<String> recommendedJobTitleList;
    public ArrayList<String> recommendedJobCompanyNameList;
    private ArrayList<String> jobCompanyNameList;
    private ArrayList<String> jobTitleList;
    private ArrayList<String> jobTitle;
    private HomeContract.Presenter mPresenter;

//    public HomeAdapter(ArrayList<String> recommendedJobTitleList, ArrayList<String> recommendedJobCompanyNameList,){
//        this.recommendedJobTitleList = recommendedJobTitleList;
//        this.recommendedJobCompanyNameList = recommendedJobCompanyNameList;
//    }

    public HomeAdapter(HomeContract.Presenter presenter){
        mPresenter = presenter;

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
    public int getItemCount() {
        return 10;
    }

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
           mPresenter.openJobDetails(); // Pass getAdapterPosition here
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
        public ImageView mHomeJobCompanyLogo;
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
                Log.d("Chloe", "v.getId(): " + v.getId());
                mPresenter.openJobDetails(); // setOpenJob here  getAdapterPosition()
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



    private void bindHomeJobsItem(HomeJobsItemViewHolder holder, int positionInJobList){
//        holder.getHomeJobTypeTag().setText();
//        holder.getHomeJobCompanyName().setText();
//        holder.getHomeJobLocationName().setText();
//        holder.getHomeJobPostedDate().setText();
//        holder.getHomeJobUrgentOrNotText();
    }

}
