package thhsu.chloe.jeeva.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
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

import thhsu.chloe.jeeva.Jeeva;
import thhsu.chloe.jeeva.R;
import thhsu.chloe.jeeva.SavedJobs.SavedJobContract;
import thhsu.chloe.jeeva.Utils.CircleTransform;
import thhsu.chloe.jeeva.api.model.Jobs;

/**
 * Created by Chloe on 5/1/2018.
 */

public class SavedJobsAdapter extends RecyclerView.Adapter<SavedJobsAdapter.SavedJobsItemViewHolder> {
    private SavedJobContract.Presenter mPresenter;
    private ArrayList<Jobs> mJobs;
    private Context mContext;


    public SavedJobsAdapter(ArrayList<Jobs> jobs, SavedJobContract.Presenter presenter){
        mPresenter = presenter;
        this.mJobs = jobs;
    }


    @Override
    public SavedJobsAdapter.SavedJobsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_saved_jobs_fragment, parent, false);
        return new SavedJobsItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SavedJobsItemViewHolder holder, int position) {
        if(holder instanceof SavedJobsItemViewHolder) {
            holder.getSavedJobTypeTag().setText(mJobs.get(position).getType());
            holder.getSavedJobCompanyName().setText(mJobs.get(position).getCompany());
            holder.getSavedJobLocationName().setText(mJobs.get(position).getLocation());
            holder.getSavedJobPostedDate().setText(mJobs.get(position).getDatePosted());
            holder.getSavedJobTitle().setText(mJobs.get(position).getTitle());
            if (mJobs.get(position).getType().equals("fulltime")) {
                (holder.getSavedJobTypeTag()).setText("Full-time");
//            (holder.getSavedJobTypeTag()).setBackgroundResource(R.drawable.yellow_rounded_shape);
            } else if (mJobs.get(position).getType().equals("parttime")) {
                (holder.getSavedJobTypeTag()).setText("Part-time");
            } else if (mJobs.get(position).getType().equals("intern")) {
                (holder.getSavedJobTypeTag()).setText("Intern");
            }
            if (mJobs.get(position).getUrgent()) {
                (holder.mSavedJobClockIcn).setImageResource(R.drawable.ic_access_alarms_black_24dp);
                (holder.getSavedJobUrgentOrNotText()).setText("Urgent");
                (holder.getSavedJobUrgentOrNotText()).setTextColor(Color.rgb(247, 59, 59));
            }

            if (holder.getSavedJobCompanyLogo() != null && mJobs.get(position).getLogo() != null) {
                Picasso.get().load(mJobs.get(position).getLogo()).transform(new CircleTransform()).into(holder.getSavedJobCompanyLogo());
            }

            if(Jeeva.getJeevaSQLHelper().getSavedJob(mJobs.get(position).getId())){
                Log.d("Chloe", "true");
                holder.getSavedJobIcnBtn().setImageResource(R.drawable.ic_bookmark_red_24dp);
            }else{
                Log.d("Chloe", "false");
                holder.getSavedJobIcnBtn().setImageResource(R.drawable.ic_bookmark_border_red_24dp);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mJobs.size();
    }

    public class SavedJobsItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mSavedJobTypeTag, mSavedJobTitle, mSavedJobPostedOnText,
                mSavedJobPostedDate, mSavedJobCompanyTitle, mSavedJobLocationTitle,
                mSavedJobCompanyName, mSavedJobLocationName, mSavedJobUrgentOrNotText ;
        public ImageView mSavedJobCompanyLogo, mSavedJobClockIcn;
        public ImageButton mSavedJobIcnBtn;

        public SavedJobsItemViewHolder(View itemView) {
            super(itemView);

            mSavedJobTypeTag = (TextView) itemView.findViewById(R.id.saved_job_type_tag);
            mSavedJobTitle = (TextView) itemView.findViewById(R.id.saved_job_title);
            mSavedJobPostedOnText = (TextView) itemView.findViewById(R.id.saved_job_posted_text);
            mSavedJobPostedDate = (TextView) itemView.findViewById(R.id.saved_job_posted_date);
            mSavedJobCompanyTitle = (TextView) itemView.findViewById(R.id.saved_job_company_title);
            mSavedJobLocationTitle = (TextView) itemView.findViewById(R.id.saved_job_location_title);
            mSavedJobCompanyName = (TextView) itemView.findViewById(R.id.saved_job_company_name);
            mSavedJobLocationName = (TextView) itemView.findViewById(R.id.saved_job_location_name);
            mSavedJobUrgentOrNotText = (TextView) itemView.findViewById(R.id.saved_job_urgentornot_text);
            mSavedJobCompanyLogo = (ImageView) itemView.findViewById(R.id.saved_job_company_logo);
            mSavedJobIcnBtn = (ImageButton) itemView.findViewById(R.id.saved_job_savedJob_icn_btn);
            mSavedJobClockIcn = (ImageView) itemView.findViewById(R.id.saved_clock_icn);

            mSavedJobIcnBtn.setOnClickListener(this);
            ((ConstraintLayout) itemView.findViewById(R.id.constraintlayout_saved_job_item)).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d("Chloe", "adapterPosition: " + getAdapterPosition());

            if(v.getId() == R.id.saved_job_savedJob_icn_btn){
                //setSOLite data here
//                if(Jeeva.getJeevaSQLHelper().getSavedJob(mJobs.get(getAdapterPosition()).getId())){
//                    mPresenter.updateSavedJob(mJobs.get(getAdapterPosition()), false);
//                    mSavedJobIcnBtn.setImageResource(R.drawable.ic_bookmark_border_red_24dp);
//
//                }else{
//                    mPresenter.updateSavedJob(mJobs.get(getAdapterPosition()), true);
//                    Log.d("Chloe", "is saved: " +Jeeva.getJeevaSQLHelper().getSavedJob(mJobs.get(getAdapterPosition()).getId()) );
//                    mSavedJobIcnBtn.setImageResource(R.drawable.ic_bookmark_red_24dp);
//                }

            }else{
                Log.d("Chloe", "getTitle in saved adapter: " + mJobs.get(getAdapterPosition()).getTitle());
                mPresenter.openJobDetails(mJobs.get(getAdapterPosition())); // setOpenJob here  getAdapterPosition()
                Log.d("Chloe", "GET WHAT: " + mJobs.get(getAdapterPosition()));
            }
        }

        public TextView getSavedJobTypeTag(){return mSavedJobTypeTag;}
        public TextView getSavedJobTitle(){return mSavedJobTitle;}
        public TextView getSavedJobPostedOnText(){return mSavedJobPostedOnText;}
        public TextView getSavedJobPostedDate(){return mSavedJobPostedDate;}
        public TextView getSavedJobCompanyTitle(){return mSavedJobCompanyTitle;}
        public TextView getSavedJobLocationTitle(){return mSavedJobLocationTitle;}
        public TextView getSavedJobCompanyName(){return mSavedJobCompanyName;}
        public TextView getSavedJobLocationName(){return mSavedJobLocationName;}
        public TextView getSavedJobUrgentOrNotText(){return mSavedJobUrgentOrNotText;}
        public ImageView getSavedJobCompanyLogo(){return mSavedJobCompanyLogo;}
        public ImageButton getSavedJobIcnBtn(){return mSavedJobIcnBtn;}
    }

    public void updateData(ArrayList<Jobs> jobs){
        mJobs = jobs;
        notifyDataSetChanged();
    }
}
