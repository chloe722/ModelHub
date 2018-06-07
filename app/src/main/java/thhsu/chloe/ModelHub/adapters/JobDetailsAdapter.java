package thhsu.chloe.ModelHub.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import thhsu.chloe.ModelHub.ModelHub;
import thhsu.chloe.ModelHub.jobDetails.JobDetailsContract;
import thhsu.chloe.ModelHub.R;
import thhsu.chloe.ModelHub.utils.CircleTransform;
import thhsu.chloe.ModelHub.utils.Constants;
import thhsu.chloe.ModelHub.api.model.Jobs;

/**
 * Created by Chloe on 5/6/2018.
 */

public class JobDetailsAdapter extends RecyclerView.Adapter<JobDetailsAdapter.CaseDetailsViewHolder> {
    public Context mContext;
    public JobDetailsContract.Presenter mCaseDetailsPresenter;
    public Jobs mJobs;
    SharedPreferences sharedPreferences;
    String token;

    public JobDetailsAdapter(Context context, Jobs job, JobDetailsContract.Presenter presenter){
        this.mContext = context;
        mCaseDetailsPresenter = presenter;
        this.mJobs = job == null ? new Jobs() : job;
    }

    @NonNull
    @Override
        public CaseDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fragment_job_details, parent, false);
        sharedPreferences = ModelHub.getAppContext().getSharedPreferences(Constants.USER_DATA, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(Constants.USER_TOKEN, "");
        return new CaseDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CaseDetailsViewHolder holder, int position) {
        Log.d("Chloe", "job title:" + mJobs.getTitle());
        (holder.getDetailsCaseTitle()).setText(mJobs.getTitle());
        (holder.getDetailsCaseDesContent()).setText(mJobs.getDescription());
        (holder.getDetailsAdvertiserName()).setText(mJobs.getCompany());
        (holder.getDetailsLocationName()).setText(mJobs.getLocation());
        (holder.getDetailsCaseShootingDateText()).setText(mJobs.getShootingDate());
        (holder.getDetailsCaseShootingDurationText()).setText(mJobs.getShootingDuration());
        (holder.getDetailsCaseWantedContent()).setText(mJobs.getWhomContent());
        (holder.getDetailsCaseCompensationContent()).setText(mJobs.getJobCompensation());
        (holder.getDetailsOtherInfoCoverTravelExpenseText()).setText(mJobs.getTravelExpensesContent());
        (holder.getDetailsOtherInfoContactNameText()).setText(mJobs.getContactName());

        if(!token.equals("")){
            if(ModelHub.getModelHubSQLHelper().getInterest(mJobs.getId())){
                Log.d("Chloe", "job details true");
                holder.getDetailsBookMark().setImageResource(R.drawable.ic_favorite_black_24dp);
            }else{
                Log.d("Chloe", "false");
                holder.getDetailsBookMark().setImageResource(R.drawable.ic_favorite_border_black_24dp);
            }
        }else{
            holder.getDetailsBookMark().setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }


        if(mJobs.getLogo().equals("") &&  (holder.getDetailsCompanyLogo() != null) ){
            Picasso.get().load(R.drawable.all_placeholder_avatar).transform(new CircleTransform()).into(holder.getDetailsCompanyLogo());
        }else{
            Picasso.get().load(mJobs.getLogo()).transform(new CircleTransform()).into(holder.getDetailsCompanyLogo());
        }

        if(mJobs.getImage() == null || mJobs.getImage().equals("")){
            holder.getImageFramlayout().setVisibility(View.GONE);
        }else{
            Picasso.get().load(mJobs.getImage()).into(holder.getDetailCaseImage());
        }



    }

    public class CaseDetailsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mDetailsCaseTitle, mDetailsCaseShootingDateTitle, mDetailsAdvertiserName, mDetailsLocationText,mDetailsCaseOtherInfoTravelExpensesTitle,
                mDetailsWantedTitle, mDetailsCaseDesContent, mDetailsCaseShootingDateText, mDetailsCaseShootingDurationTitle, mDetailsCaseShootingDurationText,
                mDetailsCasePay, mDetailsOtherInfoCoverTravelExpenseText, mDetailsOtherInfoContactNameText, mDetailsOtherInfoContactNameTitle,
                mDetailsCaseWantedContent, mDetailsCaseCompensationTitle, mDetailsCaseCompensationContent;
//        mDetailsOtherInfoContactEmailTitle, mDetailsOtherInfoContactEmailText
        private ImageButton mDetailsShareBtn, mDetailsBookMark;
        private ImageView mDetailsCompanyLogo, mDetailsCaseImage;
        private Button mDetailsReadMoreBtn, mDetailsApplyBtn;
        private ConstraintLayout mDesConstraint, mOtherInfoConstraint, mCompensationConstraint;
        private FrameLayout mImageConstraint;

        public CaseDetailsViewHolder(View itemView) {
            super(itemView);

            mDetailsCaseTitle = (TextView) itemView.findViewById(R.id.textview_home_job_title);
            mDetailsCaseShootingDateTitle = (TextView) itemView.findViewById(R.id.textview_job_details_shooting_day_title);
            mDetailsAdvertiserName = (TextView) itemView.findViewById(R.id.textview_job_details_advertiser_text);
            mDetailsLocationText = (TextView) itemView.findViewById(R.id.textview_job_details_location);
            mDetailsWantedTitle = (TextView) itemView.findViewById(R.id.textview_job_details_wanted_title);
            mDetailsCaseDesContent = (TextView) itemView.findViewById(R.id.textview_job_details_des_content);
            mDetailsCaseShootingDateText = (TextView) itemView.findViewById(R.id.textview_job_details_shooting_date_text);
            mDetailsCaseShootingDurationTitle = (TextView) itemView.findViewById(R.id.textview_job_details_shooting_duration_title);
            mDetailsCaseShootingDurationText = (TextView) itemView.findViewById(R.id.textview_job_details_shooting_duration_text);
            mDetailsCasePay = (TextView) itemView.findViewById(R.id.textview_job_details_pay);
            mDetailsCaseOtherInfoTravelExpensesTitle = (TextView) itemView.findViewById(R.id.textview_job_details_other_info_travel_expenses_title);
            mDetailsOtherInfoContactNameTitle = (TextView) itemView.findViewById(R.id.textview_job_details_other_info_contact_name_title);
            mDetailsCaseWantedContent = (TextView) itemView.findViewById(R.id.textview_job_details_wanted_content);
            mDetailsOtherInfoCoverTravelExpenseText = (TextView) itemView.findViewById(R.id.textview_job_details_other_info_travel_expenses_text);
            mDetailsOtherInfoContactNameText = (TextView) itemView.findViewById(R.id.textview_job_details_other_info_contact_name_text);
            mDetailsCaseCompensationTitle = (TextView) itemView.findViewById(R.id.textview_job_details_compensation_title);
            mDetailsCaseCompensationContent = (TextView) itemView.findViewById(R.id.textview_job_details_compensation_content);


            mDetailsShareBtn = (ImageButton) itemView.findViewById(R.id.imagebtn_job_details_share);
            mDetailsBookMark = (ImageButton) itemView.findViewById(R.id.imagebtn_job_details_bookmark);
            mDetailsApplyBtn = (Button) itemView.findViewById(R.id.btn_job_details_apply);

            mDetailsCompanyLogo = (ImageView) itemView.findViewById(R.id.imageview_job_details_company_logo);
            mDetailsCaseImage = (ImageView) itemView.findViewById(R.id.imageview_job_details_image);

            mDesConstraint = (ConstraintLayout) itemView.findViewById(R.id.constraintlayout_job_details_des);
            mOtherInfoConstraint = (ConstraintLayout) itemView.findViewById(R.id.constraintlayout_job_details_other_info);
            mCompensationConstraint = (ConstraintLayout) itemView.findViewById(R.id.constraintlayout_job_details_compensation);
            mImageConstraint = (FrameLayout) itemView.findViewById(R.id.framlayout_job_details_image);

            mDetailsBookMark.setOnClickListener(this);
            mDetailsShareBtn.setOnClickListener(this);

        }

        private TextView getDetailsCaseTitle(){return mDetailsCaseTitle;}
        private TextView getDetailsCaseDateTitle(){return mDetailsCaseShootingDateTitle;}
        private TextView getDetailsAdvertiserName(){return mDetailsAdvertiserName;}
        private TextView getDetailsLocationName(){return mDetailsLocationText;}
        private TextView getDetailsCaseDesContent(){return mDetailsCaseDesContent;}
        private TextView getDetailsWantedTitle(){return  mDetailsWantedTitle;}
        private TextView getDetailsCaseWantedContent(){return mDetailsCaseWantedContent;}
        private TextView getDetailsCaseShootingDateTitle(){return  mDetailsCaseShootingDateTitle;}
        private TextView getDetailsCaseShootingDateText(){return  mDetailsCaseShootingDateText;}
        private TextView getDetailsCaseShootingDurationTitle(){return  mDetailsCaseShootingDurationTitle;}
        private TextView getDetailsCaseShootingDurationText(){return  mDetailsCaseShootingDurationText;}
        private TextView getDetailsCasePay(){return mDetailsCasePay;}
        private TextView getDetailsOtherInfoCoverTravelExpenseText(){return mDetailsOtherInfoCoverTravelExpenseText;}
        private TextView getDetailsCaseCompensationContent(){return  mDetailsCaseCompensationContent;}
        private ImageView getDetailCaseImage(){return mDetailsCaseImage;}
        private TextView getDetailsOtherInfoContactNameText(){return mDetailsOtherInfoContactNameText;}
        private ImageView getDetailsCompanyLogo(){return mDetailsCompanyLogo;}
        private ImageButton getDetailsBookMark(){return mDetailsBookMark;}
        private FrameLayout getImageFramlayout(){return mImageConstraint;}

        @Override
        public void onClick(View v) {
            switch (v.getId()){
//                case R.id.case_details_readmore_btn:
//                    mDetailsCaseDesContent.setMaxLines(Integer.MAX_VALUE);
//                    mDetailsReadMoreBtn.setVisibility(View.INVISIBLE);
//                    mDesConstraint.setVisibility(View.VISIBLE);
//                    mOtherInfoConstraint.setVisibility(View.VISIBLE);
//                    mCompensationConstraint.setVisibility(View.VISIBLE);
//
//                    break;
                case R.id.imagebtn_job_details_bookmark:
                    if(!token.equals("")){
                        if(ModelHub.getModelHubSQLHelper().getInterest(mJobs.getId())){
                            mCaseDetailsPresenter.updateInterestJob(mJobs, false);
                            mDetailsBookMark.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                            break;
                        }else{
                            mCaseDetailsPresenter.updateInterestJob(mJobs, true);
                            Log.d("Chloe", "is saved: " + ModelHub.getModelHubSQLHelper().getInterest(mJobs.getId()) );
                            mDetailsBookMark.setImageResource(R.drawable.ic_favorite_black_24dp);
                            break;
                        }
                    }else{
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
        Log.d("Chloe", "mJobs title: " + mJobs.getTitle());
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
