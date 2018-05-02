package thhsu.chloe.jeeva.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
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
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    private class HomeMainItemViewHolder extends RecyclerView.ViewHolder{
        private RecyclerView mRecyclerRecommend;
        public TextView mRecommendedTitle, mJobTitle;;


        public HomeMainItemViewHolder(View itemView) {
            super(itemView);

            mRecyclerRecommend = (RecyclerView) itemView.findViewById(R.id.home_horizontal_recyclerview);
            mRecommendedTitle = (TextView) itemView.findViewById(R.id.horizontal_recommend_title);
            mJobTitle = (TextView) itemView.findViewById(R.id.vertical_job_title);

        }

        public RecyclerView getRecyclerRecommend(){
            return mRecyclerRecommend;
        }
    }

    private void bindMainItem(HomeMainItemViewHolder holder){
        holder.getRecyclerRecommend().setLayoutManager(new LinearLayoutManager(Jeeva.getAppContext(),
                LinearLayoutManager.HORIZONTAL, false));
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

        }

        @Override
        public void onClick(View v) {
            Log.d("Chloe", "adapterPosition: " + getAdapterPosition());
        }
    }

}
