package thhsu.chloe.jeeva.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.security.PublicKey;
import java.util.ArrayList;

import thhsu.chloe.jeeva.Home.HomeContract;
//import thhsu.chloe.jeeva.Profile.ProfileFragment;
import thhsu.chloe.jeeva.R;
import thhsu.chloe.jeeva.Utils.CircleTransform;
import thhsu.chloe.jeeva.activities.JeevaActivity;
import thhsu.chloe.jeeva.api.model.Jobs;

/**
 * Created by Chloe on 5/2/2018.
 */

public class HomeJobRecommendAdapter extends RecyclerView.Adapter{
    private Context mContext;
    private HomeContract.Presenter mPresenter;
    private ArrayList<Jobs> mJobs;

    public HomeJobRecommendAdapter(HomeContract.Presenter presenter, ArrayList<Jobs> jobs){
        if (presenter != null){
            this.mPresenter = presenter;
        }
        this.mJobs = new ArrayList<Jobs>();

        for(Jobs job : jobs){
            if(job.getRecommended()){
                mJobs.add(job);
            }
        }
        Log.d("Chloe", "recommend adapter jobs : " + mJobs.size());
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizontal_home, parent, false);
        return new HomeJobRecommendItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof HomeJobRecommendItemViewHolder){
            if (mJobs.size() > 0){
//                DisplayMetrics displayMetrics = new DisplayMetrics();
//                ((JeevaActivity) mContext).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                (((HomeJobRecommendItemViewHolder) holder).getRecommendedJobCompanyName()).setText(mJobs.get(position).getCompany());
                (((HomeJobRecommendItemViewHolder) holder).getRecommendedJobPositionTitle()).setText(mJobs.get(position).getTitle());
                if (mJobs.get(position).getImage().equals("")){
                    Picasso.get().load(R.drawable.jeeva_color_font_edited).fit().into(((HomeJobRecommendItemViewHolder) holder).getRecommendedJobImage());

                }else {
                    Picasso.get().load(mJobs.get(position).getImage()).fit().placeholder(R.drawable.jeeva_color_font_edited).into(((HomeJobRecommendItemViewHolder) holder).getRecommendedJobImage());
                }

                if (mJobs.get(position).getLogo().equals("")){
                    Picasso.get().load(R.drawable.all_placeholder_avatar).transform(new CircleTransform()).into(((HomeJobRecommendItemViewHolder) holder).getRecommendedJobCompanyLogo());
                }else{
                    Picasso.get().load(mJobs.get(position).getLogo()).transform(new CircleTransform()).into(((HomeJobRecommendItemViewHolder) holder).getRecommendedJobCompanyLogo());
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return mJobs.size();
    }

    private class HomeJobRecommendItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView mRecommendedJobImage, mRecommendedJobCompanyLogo;
        public TextView mRecommendedJobPositionTitle, mRecommendedJobCompanyName;

        public HomeJobRecommendItemViewHolder(View itemView) {
            super(itemView);

            mRecommendedJobImage = (ImageView) itemView.findViewById(R.id.recommend_job_image);
            mRecommendedJobCompanyLogo = (ImageView) itemView.findViewById(R.id.recommend_company_logo);
            mRecommendedJobPositionTitle = (TextView) itemView.findViewById(R.id.recommend_job_title);
            mRecommendedJobCompanyName = (TextView) itemView.findViewById(R.id.recommend_company_name);
            ((CardView) itemView.findViewById(R.id.cardView_recommended_job_item)).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.cardView_recommended_job_item) mPresenter.openJobDetails(mJobs.get(getAdapterPosition())); // setOpenJob here  getAdapterPosition()
        }

        public ImageView getRecommendedJobImage(){return mRecommendedJobImage;}
        public ImageView getRecommendedJobCompanyLogo(){return mRecommendedJobCompanyLogo;}
        public TextView getRecommendedJobPositionTitle(){return mRecommendedJobPositionTitle;}
        public TextView getRecommendedJobCompanyName(){return mRecommendedJobCompanyName;}
    }
}
