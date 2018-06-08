package thhsu.chloe.ModelHub.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
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

import thhsu.chloe.ModelHub.ModelHub;
import thhsu.chloe.ModelHub.R;
import thhsu.chloe.ModelHub.interest.InterestContract;
import thhsu.chloe.ModelHub.utils.CircleTransform;
import thhsu.chloe.ModelHub.api.model.Jobs;

/**
 * Created by Chloe on 5/1/2018.
 */

public class InterestAdapter extends RecyclerView.Adapter<InterestAdapter.InterestItemViewHolder> {
    private ArrayList<Jobs> mJobs;
    private InterestContract.Presenter mPresenter;

    public InterestAdapter(ArrayList<Jobs> jobs, InterestContract.Presenter presenter){
        mJobs = jobs;
        mPresenter = presenter;
    }


    @NonNull
    @Override
    public InterestItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_interest_fragment, parent, false);
        return new InterestItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InterestItemViewHolder holder, int position) {
        if (holder instanceof InterestItemViewHolder) {

            holder.getInterestWhom().setText(mJobs.get(position).getWhom());
            holder.getInterestPay().setText(mJobs.get(position).getIsPaid());
            holder.getInterestTitle().setText(mJobs.get(position).getTitle());
            holder.getInterestLocation().setText(mJobs.get(position).getLocation());

            if (holder.getInterestCompanyLogo() != null && mJobs.get(position).getLogo() != null) {
                Picasso.get().load(mJobs.get(position).getLogo()).transform(new CircleTransform()).into(holder.getInterestCompanyLogo());
            }

            if (ModelHub.getModelHubSQLHelper().getInterest(mJobs.get(position).getId())) {
                holder.getInterestIcnBtn().setImageResource(R.drawable.ic_favorite_black_24dp);

            } else {
                holder.getInterestIcnBtn().setImageResource(R.drawable.ic_favorite_border_black_24dp);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mJobs.size();
    }

    public class InterestItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageButton mImageBtnInterestIcn;
        private ImageView mImageViewInterestCompanyLogo;
        private TextView mTextViewInterestJobTitle, mTextViewInterestJobLocation, mTextViewInterestJobWhom, mTextViewInterestJobPay;


        private InterestItemViewHolder(View itemView) {
            super(itemView);

            mTextViewInterestJobWhom = (TextView) itemView.findViewById(R.id.textview_interest_whom);
            mTextViewInterestJobPay = (TextView) itemView.findViewById(R.id.textview_interest_job_pay);
            mTextViewInterestJobTitle = (TextView) itemView.findViewById(R.id.textview_interest_job_title);
            mTextViewInterestJobLocation = (TextView) itemView.findViewById(R.id.textview_interest_location);
            mImageBtnInterestIcn = (ImageButton) itemView.findViewById(R.id.imagebtn_interest_interest_icn);
            mImageViewInterestCompanyLogo = (ImageView) itemView.findViewById(R.id.imageview_interest_company_logo);

            mImageBtnInterestIcn.setOnClickListener(this);
            ((CardView) itemView.findViewById(R.id.cardview_interest_job)).setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            Jobs jobs = mJobs.get(getAdapterPosition());

            if (v.getId() == R.id.imagebtn_interest_interest_icn) {
                //setSOLite data here
                if (ModelHub.getModelHubSQLHelper().getInterest(jobs.getId())) {
                    mPresenter.updateInterest(jobs, false);
                    mImageBtnInterestIcn.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    mPresenter.refreshJobs();
                } else {
                    mPresenter.updateInterest(jobs, true);
                    mImageBtnInterestIcn.setImageResource(R.drawable.ic_favorite_black_24dp);
                }

            } else {
                mPresenter.openJobDetails(jobs); // setOpenCase here  getAdapterPosition()
            }
        }

        private TextView getInterestPay(){return mTextViewInterestJobPay;}
        private TextView getInterestWhom(){return mTextViewInterestJobWhom;}
        private TextView getInterestTitle(){return mTextViewInterestJobTitle;}
        private TextView getInterestLocation(){return mTextViewInterestJobLocation;}
        private ImageView getInterestCompanyLogo(){return mImageViewInterestCompanyLogo;}
        private ImageButton getInterestIcnBtn(){return mImageBtnInterestIcn;}

    }

    public void updateData(ArrayList<Jobs> jobs) {
        mJobs = jobs;
        notifyDataSetChanged();
    }
}
