package thhsu.chloe.jeeva.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.security.PublicKey;

import thhsu.chloe.jeeva.R;
import thhsu.chloe.jeeva.activities.JeevaActivity;

/**
 * Created by Chloe on 5/2/2018.
 */

public class HomeJobRecommedAdapter extends RecyclerView.Adapter{
    private Context mContext;

    public HomeJobRecommedAdapter(){
    }  // pass parameter (write data)


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

            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((JeevaActivity) mContext).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

            //Implemente data here
        }

    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
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
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }

        public ImageView getRecommendedJobImage(){return mRecommendedJobImage;}
        public ImageView getRecommendedJobCompanyLogo(){return mRecommendedJobCompanyLogo;}
        public TextView getRecommendedJobPositionTitle(){return mRecommendedJobPositionTitle;}
        public TextView getRecommendedJobCompanyName(){return mRecommendedJobCompanyName;}
    }
}
