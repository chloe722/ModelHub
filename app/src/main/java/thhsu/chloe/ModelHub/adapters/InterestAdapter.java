package thhsu.chloe.ModelHub.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
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
import thhsu.chloe.ModelHub.api.model.Cases;

/**
 * Created by Chloe on 5/1/2018.
 */

public class InterestAdapter extends RecyclerView.Adapter<InterestAdapter.InterestItemViewHolder> {
    private InterestContract.Presenter mPresenter;
    private ArrayList<Cases> mCases;
    private Context mContext;


    public InterestAdapter(ArrayList<Cases> cases, InterestContract.Presenter presenter){
        mPresenter = presenter;
        this.mCases = cases;
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
            holder.getInterestTypeTag().setText(mCases.get(position).getType());
            holder.getInterestCompanyName().setText(mCases.get(position).getCompany());
            holder.getInterestLocationName().setText(mCases.get(position).getLocation());
            holder.getInterestPostedDate().setText(mCases.get(position).getDatePosted());
            holder.getInterestTitle().setText(mCases.get(position).getTitle());
            if (mCases.get(position).getType().equals("fulltime")) {
                (holder.getInterestTypeTag()).setText("Full-time");
//            (holder.getInterestTypeTag()).setBackgroundResource(R.drawable.yellow_rounded_shape);
            } else if (mCases.get(position).getType().equals("parttime")) {
                (holder.getInterestTypeTag()).setText("Part-time");
            } else if (mCases.get(position).getType().equals("intern")) {
                (holder.getInterestTypeTag()).setText("Intern");
            }


            if (mCases.get(position).getUrgent()) {
                (holder.mInterestClockIcn).setImageResource(R.drawable.ic_access_alarms_black_24dp);
                (holder.getInterestUrgentOrNotText()).setText("Urgent");
                (holder.getInterestUrgentOrNotText()).setTextColor(Color.rgb(247, 59, 59));
            }

            if (holder.getInterestCompanyLogo() != null && mCases.get(position).getLogo() != null) {
                Picasso.get().load(mCases.get(position).getLogo()).transform(new CircleTransform()).into(holder.getInterestCompanyLogo());
            }

            if(ModelHub.getModelHubSQLHelper().getInterest(mCases.get(position).getId())){
                Log.d("Chloe", "true");
                holder.getInterestIcnBtn().setImageResource(R.drawable.ic_bookmark_red_24dp);
            }else{
                Log.d("Chloe", "false");
                holder.getInterestIcnBtn().setImageResource(R.drawable.ic_bookmark_border_red_24dp);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mCases.size();
    }

    public class InterestItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mInterestTypeTag, mInterestTitle, mInterestPostedOnText,
                mInterestPostedDate, mInterestCompanyTitle, mInterestLocationTitle,
                mInterestCompanyName, mInterestLocationName, mInterestUrgentOrNotText;
        public ImageView mInterestCompanyLogo, mInterestClockIcn;
        public ImageButton mInterestIcnBtn;

        public InterestItemViewHolder(View itemView) {
            super(itemView);

            mInterestTypeTag = (TextView) itemView.findViewById(R.id.interest_type_tag);
            mInterestTitle = (TextView) itemView.findViewById(R.id.home_case_title);
            mInterestPostedOnText = (TextView) itemView.findViewById(R.id.interest_posted_text);
            mInterestPostedDate = (TextView) itemView.findViewById(R.id.interest_posted_date);
            mInterestCompanyTitle = (TextView) itemView.findViewById(R.id.interest_company_title);
            mInterestLocationTitle = (TextView) itemView.findViewById(R.id.interest_location_title);
            mInterestCompanyName = (TextView) itemView.findViewById(R.id.interest_company_name);
            mInterestLocationName = (TextView) itemView.findViewById(R.id.interest_location_name);
            mInterestUrgentOrNotText = (TextView) itemView.findViewById(R.id.interest_urgentornot_text);
            mInterestCompanyLogo = (ImageView) itemView.findViewById(R.id.interest_company_logo);
            mInterestIcnBtn = (ImageButton) itemView.findViewById(R.id.interest_interest_icn_btn);
            mInterestClockIcn = (ImageView) itemView.findViewById(R.id.interest_clock_icn);

            mInterestIcnBtn.setOnClickListener(this);
            ((ConstraintLayout) itemView.findViewById(R.id.constraintlayout_interest_item)).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d("Chloe", "adapterPosition: " + getAdapterPosition());

            if(v.getId() == R.id.interest_interest_icn_btn){
                //setSOLite data here
//                if(ModelHub.getModelHubSQLHelper().getInterest(mCases.get(getAdapterPosition()).getId())){
//                    mPresenter.updateInterest(mCases.get(getAdapterPosition()), false);
//                    mInterestIcnBtn.setImageResource(R.drawable.ic_bookmark_border_red_24dp);
//
//                }else{
//                    mPresenter.updateInterest(mCases.get(getAdapterPosition()), true);
//                    Log.d("Chloe", "is saved: " +ModelHub.getModelHubSQLHelper().getInterest(mCases.get(getAdapterPosition()).getId()) );
//                    mInterestIcnBtn.setImageResource(R.drawable.ic_bookmark_red_24dp);
//                }

            }else{
                Log.d("Chloe", "getTitle in saved adapter: " + mCases.get(getAdapterPosition()).getTitle());
                mPresenter.openCaseDetails(mCases.get(getAdapterPosition())); // setOpenCase here  getAdapterPosition()
                Log.d("Chloe", "GET WHAT: " + mCases.get(getAdapterPosition()));
            }
        }

        public TextView getInterestTypeTag(){return mInterestTypeTag;}
        public TextView getInterestTitle(){return mInterestTitle;}
        public TextView getInterestPostedOnText(){return mInterestPostedOnText;}
        public TextView getInterestPostedDate(){return mInterestPostedDate;}
        public TextView getInterestCompanyTitle(){return mInterestCompanyTitle;}
        public TextView getInterestLocationTitle(){return mInterestLocationTitle;}
        public TextView getInterestCompanyName(){return mInterestCompanyName;}
        public TextView getInterestLocationName(){return mInterestLocationName;}
        public TextView getInterestUrgentOrNotText(){return mInterestUrgentOrNotText;}
        public ImageView getInterestCompanyLogo(){return mInterestCompanyLogo;}
        public ImageButton getInterestIcnBtn(){return mInterestIcnBtn;}
    }

    public void updateData(ArrayList<Cases> cases){
        mCases = cases;
        notifyDataSetChanged();
    }
}
