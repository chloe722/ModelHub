package thhsu.chloe.ModelHub.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import thhsu.chloe.ModelHub.ModelHub;
import thhsu.chloe.ModelHub.details.JobDetailsContract;
import thhsu.chloe.ModelHub.R;
import thhsu.chloe.ModelHub.utils.CircleTransform;
import thhsu.chloe.ModelHub.utils.Constants;
import thhsu.chloe.ModelHub.api.model.Jobs;

/**
 * Created by Chloe on 5/6/2018.
 */

public class JobDetailsAdapter extends RecyclerView.Adapter<JobDetailsAdapter.JobDetailsViewHolder> {
    private Jobs mJobs;
    private Context mContext;
    private String mUserToken;
    private JobDetailsContract.Presenter mCaseDetailsPresenter;

    public JobDetailsAdapter(Context context, Jobs job, JobDetailsContract.Presenter presenter){
        mContext = context;
        mCaseDetailsPresenter = presenter;
        mJobs = job == null ? new Jobs() : job;
    }

    @NonNull
    @Override
        public JobDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fragment_job_details, parent, false);
        SharedPreferences sharedPreferences = ModelHub.getAppContext().getSharedPreferences(Constants.USER_DATA, Context.MODE_PRIVATE);
        mUserToken = sharedPreferences.getString(Constants.USER_TOKEN, "");
        return new JobDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobDetailsViewHolder holder, int position) {
        (holder.getDetailsJobTitle()).setText(mJobs.getTitle());
        (holder.getDetailsLocationName()).setText(mJobs.getLocation());
        (holder.getDetailsAdvertiserName()).setText(mJobs.getCompany());
        (holder.getDetailsJobDesContent()).setText(mJobs.getDescription());
        (holder.getDetailsContactNameText()).setText(mJobs.getContactName());
        (holder.getDetailsJobWantedContent()).setText(mJobs.getWhomContent());
        (holder.getDetailsJobShootingDateText()).setText(mJobs.getShootingDate());
        (holder.getDetailsJobCompensationContent()).setText(mJobs.getJobCompensation());
        (holder.getDetailsJobShootingDurationText()).setText(mJobs.getShootingDuration());
        (holder.getDetailsCoverTravelExpenseText()).setText(mJobs.getTravelExpensesContent());
        (holder.getDetailsJobPay()).setText(mJobs.getJobCompensation());

        if (!mUserToken.equals("")) {
            if (ModelHub.getModelHubSQLHelper().getInterest(mJobs.getId())) {
                holder.getDetailsBookMark().setImageResource(R.drawable.ic_favorite_black_24dp);
            } else {

                holder.getDetailsBookMark().setImageResource(R.drawable.ic_favorite_border_black_24dp);
            }
        } else {

            holder.getDetailsBookMark().setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }


        if ((holder.getDetailsCompanyLogo() != null) && mJobs.getLogo().equals("")) {
            Picasso.get().load(R.drawable.all_placeholder_avatar).transform(new CircleTransform()).into(holder.getDetailsCompanyLogo());
        } else {
            Picasso.get().load(mJobs.getLogo()).transform(new CircleTransform()).into(holder.getDetailsCompanyLogo());
        }

        if (mJobs.getImage() == null || mJobs.getImage().equals("")) {
            holder.getImageFrameLayout().setVisibility(View.GONE);
        } else {
            Picasso.get().load(mJobs.getImage()).into(holder.getDetailJobImage());
        }

    }

    public class JobDetailsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTextViewDetailsJobTitle, mTextViewDetailsAdvertiserName, mTextViewDetailsLocationText,
                mTextViewDetailsJobDesContent, mTextViewDetailsJobShootingDateText, mTextViewDetailsJobShootingDurationText,
                mTextViewDetailsJobPay, mTextViewDetailsTravelExpenseText, mTextViewDetailsContactNameText,
                mTextViewDetailsJobWantedContent, mTextViewDetailsJobCompensationContent;
        private ImageButton mImageBtnDetailsShare, mImageBtnDetailsBookMark;
        private ImageView mImageViewDetailsCompanyLogo, mImageViewDetailsJobImage;
        private FrameLayout mImageFrameLayout;

        JobDetailsViewHolder(View itemView) {
            super(itemView);

            mTextViewDetailsJobPay = (TextView) itemView.findViewById(R.id.textview_job_details_pay);
            mTextViewDetailsJobTitle = (TextView) itemView.findViewById(R.id.textview_home_job_title);
            mTextViewDetailsLocationText = (TextView) itemView.findViewById(R.id.textview_job_details_location);
            mTextViewDetailsJobDesContent = (TextView) itemView.findViewById(R.id.textview_job_details_des_content);
            mTextViewDetailsAdvertiserName = (TextView) itemView.findViewById(R.id.textview_job_details_advertiser_text);
            mTextViewDetailsJobWantedContent = (TextView) itemView.findViewById(R.id.textview_job_details_wanted_content);
            mTextViewDetailsJobShootingDateText = (TextView) itemView.findViewById(R.id.textview_job_details_shooting_date_text);
            mTextViewDetailsJobCompensationContent = (TextView) itemView.findViewById(R.id.textview_job_details_compensation_content);
            mTextViewDetailsContactNameText = (TextView) itemView.findViewById(R.id.textview_job_details_other_info_contact_name_text);
            mTextViewDetailsJobShootingDurationText = (TextView) itemView.findViewById(R.id.textview_job_details_shooting_duration_text);
            mTextViewDetailsTravelExpenseText = (TextView) itemView.findViewById(R.id.textview_job_details_other_info_travel_expenses_text);


            mImageBtnDetailsShare = (ImageButton) itemView.findViewById(R.id.imagebtn_job_details_share);
            mImageViewDetailsJobImage = (ImageView) itemView.findViewById(R.id.imageview_job_details_image);
            mImageBtnDetailsBookMark = (ImageButton) itemView.findViewById(R.id.imagebtn_job_details_bookmark);
            mImageViewDetailsCompanyLogo = (ImageView) itemView.findViewById(R.id.imageview_job_details_company_logo);

            mImageFrameLayout = (FrameLayout) itemView.findViewById(R.id.framlayout_job_details_image);

            mImageBtnDetailsBookMark.setOnClickListener(this);
            mImageBtnDetailsShare.setOnClickListener(this);

        }


        private TextView getDetailsJobPay(){return mTextViewDetailsJobPay;}
        private TextView getDetailsJobTitle(){return mTextViewDetailsJobTitle;}
        private TextView getDetailsLocationName(){return mTextViewDetailsLocationText;}
        private TextView getDetailsJobDesContent(){return mTextViewDetailsJobDesContent;}
        private TextView getDetailsAdvertiserName(){return mTextViewDetailsAdvertiserName;}
        private TextView getDetailsContactNameText(){return mTextViewDetailsContactNameText;}
        private TextView getDetailsJobWantedContent(){return mTextViewDetailsJobWantedContent;}
        private TextView getDetailsJobShootingDateText(){return mTextViewDetailsJobShootingDateText;}
        private TextView getDetailsCoverTravelExpenseText(){return mTextViewDetailsTravelExpenseText;}
        private TextView getDetailsJobCompensationContent(){return mTextViewDetailsJobCompensationContent;}
        private TextView getDetailsJobShootingDurationText(){return mTextViewDetailsJobShootingDurationText;}
        private ImageView getDetailJobImage(){return mImageViewDetailsJobImage;}
        private ImageView getDetailsCompanyLogo(){return mImageViewDetailsCompanyLogo;}
        private ImageButton getDetailsBookMark(){return mImageBtnDetailsBookMark;}
        private FrameLayout getImageFrameLayout(){return mImageFrameLayout;}

        @Override
        public void onClick(View v) {
            switch (v.getId()){

                case R.id.imagebtn_job_details_bookmark:
                    if (!mUserToken.equals("")) {
                        if (ModelHub.getModelHubSQLHelper().getInterest(mJobs.getId())) {
                            mCaseDetailsPresenter.updateInterestJob(mJobs, false);
                            mImageBtnDetailsBookMark.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                            break;
                        } else {
                            mCaseDetailsPresenter.updateInterestJob(mJobs, true);
                            mImageBtnDetailsBookMark.setImageResource(R.drawable.ic_favorite_black_24dp);
                            break;
                        }
                    } else {
                        Toast.makeText(ModelHub.getAppContext(), "You are not logged in yet", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.imagebtn_job_details_share:
                    Intent intentToShare = new Intent(Intent.ACTION_SEND);
                    intentToShare.setType("text/plain");
                    intentToShare.putExtra(Intent.EXTRA_SUBJECT, "Testing");
                    intentToShare.putExtra(Intent.EXTRA_TEXT, "https://ModelHub.tw");
                    mContext.startActivity(Intent.createChooser(intentToShare, "Title of the dialog the system will open"));
                    break;


            }
        }
    }

    public void updateJobs(Jobs job){
        mJobs = job;
        notifyItemChanged(0);
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
