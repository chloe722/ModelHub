package thhsu.chloe.ModelHub.adapters;

import android.content.Context;
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
import thhsu.chloe.ModelHub.Interest.InterestContract;
import thhsu.chloe.ModelHub.Utils.CircleTransform;
import thhsu.chloe.ModelHub.api.model.Jobs;

/**
 * Created by Chloe on 5/1/2018.
 */

public class InterestAdapter extends RecyclerView.Adapter<InterestAdapter.InterestItemViewHolder> {
    private InterestContract.Presenter mPresenter;
    private ArrayList<Jobs> mJobs;
    private Context mContext;


    public InterestAdapter(ArrayList<Jobs> jobs, InterestContract.Presenter presenter){
        mPresenter = presenter;
        this.mJobs = jobs;
    }


    @Override
    public InterestItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_interest_fragment, parent, false);
        return new InterestItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InterestItemViewHolder holder, int position) {
        if(holder instanceof InterestItemViewHolder) {

            holder.getInterestLocation().setText(mJobs.get(position).getLocation());
            holder.getInterestTitle().setText(mJobs.get(position).getTitle());
            holder.getInterestWhom().setText(mJobs.get(position).getWhom());
            holder.getInterestPay().setText(mJobs.get(position).getIsPaid());


            if (holder.getInterestCompanyLogo() != null && mJobs.get(position).getLogo() != null) {
                Picasso.get().load(mJobs.get(position).getLogo()).transform(new CircleTransform()).into(holder.getInterestCompanyLogo());
            }

            if(ModelHub.getModelHubSQLHelper().getInterest(mJobs.get(position).getId())){
                Log.d("Chloe", "true");
                holder.getInterestIcnBtn().setImageResource(R.drawable.ic_favorite_black_24dp);
            }else{
                Log.d("Chloe", "false");
                holder.getInterestIcnBtn().setImageResource(R.drawable.ic_favorite_border_black_24dp);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mJobs.size();
    }

    public class InterestItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mInterestTypeTag, mInterestTitle, mInterestLocation, mInterestWhom, mInterestPay, mInterestPostedOnText,
                mInterestPostedDate, mInterestCompanyTitle, mInterestLocationTitle,
                mInterestCompanyName, mInterestUrgentOrNotText;
        private ImageView mInterestCompanyLogo;
        private ImageButton mInterestIcnBtn;

        private InterestItemViewHolder(View itemView) {
            super(itemView);

            mInterestTitle = (TextView) itemView.findViewById(R.id.interest_title);
            mInterestLocation = (TextView) itemView.findViewById(R.id.interest_location);
            mInterestWhom = (TextView) itemView.findViewById(R.id.interest_whom);
            mInterestPay = (TextView) itemView.findViewById(R.id.interest_pay);
            mInterestCompanyLogo = (ImageView) itemView.findViewById(R.id.interest_company_logo);
            mInterestIcnBtn = (ImageButton) itemView.findViewById(R.id.interest_interest_icn_btn);

            mInterestIcnBtn.setOnClickListener(this);

            ((CardView) itemView.findViewById(R.id.interest_cardview_layout)).setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            Jobs jobs = mJobs.get(getAdapterPosition());
            Log.d("Chloe", "adapterPosition: " + getAdapterPosition());

            if(v.getId() == R.id.interest_interest_icn_btn){
                //setSOLite data here
                if(ModelHub.getModelHubSQLHelper().getInterest(jobs.getId())){
                    mPresenter.updateInterest(jobs, false);
                    mInterestIcnBtn.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    mPresenter.refreshJobs();
                }else{
                    mPresenter.updateInterest(jobs, true);
                    Log.d("Chloe", "is saved: " + ModelHub.getModelHubSQLHelper().getInterest(jobs.getId()));
                    mInterestIcnBtn.setImageResource(R.drawable.ic_favorite_black_24dp);
                }

            }else{
                Log.d("Chloe", "getTitle in saved adapter: " + mJobs.get(getAdapterPosition()).getTitle());
                mPresenter.openCaseDetails(mJobs.get(getAdapterPosition())); // setOpenCase here  getAdapterPosition()
                Log.d("Chloe", "GET WHAT: " + mJobs.get(getAdapterPosition()));
            }
        }

        private TextView getInterestTitle(){return mInterestTitle;}
        private TextView getInterestLocation(){return mInterestLocation;}
        private TextView getInterestWhom(){return mInterestWhom;}
        private TextView getInterestPay(){return mInterestPay;}
        private ImageView getInterestCompanyLogo(){return mInterestCompanyLogo;}
        private ImageButton getInterestIcnBtn(){return mInterestIcnBtn;}

    }

    public void updateData(ArrayList<Jobs> jobs){
        mJobs = jobs;
        notifyDataSetChanged();
    }
}
