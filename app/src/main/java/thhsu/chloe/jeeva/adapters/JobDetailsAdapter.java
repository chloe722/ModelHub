package thhsu.chloe.jeeva.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import thhsu.chloe.jeeva.JobDetails.JobDetailsContract;
import thhsu.chloe.jeeva.R;
import thhsu.chloe.jeeva.api.model.Jobs;

/**
 * Created by Chloe on 5/6/2018.
 */

public class JobDetailsAdapter extends RecyclerView.Adapter<JobDetailsAdapter.JobDetailsViewHolder> {
    public Context mContext;
    public JobDetailsContract.Presenter mJobDetailsPresenter;
    public Jobs mJob;

    public JobDetailsAdapter(Context context, Jobs job, JobDetailsContract.Presenter presenter){
        this.mContext = context;
        mJobDetailsPresenter = presenter;
        this.mJob = job == null ? new Jobs() : job;
    }

    @NonNull
    @Override
        public JobDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fragment_job_details, parent, false);
        return new JobDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobDetailsViewHolder holder, int position) {
        Log.d("Chloe", "job title:" + mJob.getTitle());
        (holder.getDetailsJobTitle()).setText(mJob.getTitle());

    }

    public class JobDetailsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mDetailsJobType, mDetailsJobTitle, mDetailsJobPostedText, mDetailsJobDate,
                mDetailsCompanyTitle, mDetailsLocationTitle, mDetailsCompanyName, mDetailsLocationName,
                mDetailsSalaryTitle, mDetailsSalaryNum, mDetailsSalaryUnit, mDetailsDesTitle, mDetailsJobDesContent,
                mDetailsBenefitTitle, mDetailsChildCare, mDetailsCoffee, mDetailsCommute, mDetailsDental, mDetailsGym,
                mDetailsMaternity, mDetailsMedicalInsurance, mDetailsMentorProgram, mDetailsMovie, mDetailsPaidVacation,
                mDetailsParentalLeave, mDetailsReading, mDetailsSavingPlan, mDetailsSnacks, mDetailsTuition,
                mDetailsVisionInsurance, mDetailsWorkFromHome, mDetailsDisabilityInsurance, mDetailsTraining,
                mDetailsHiringResTitle, mDetailsHiringResFromTitle, mDetailsHiringContactNameTitle, mDetailsHiringOtherInfoTitle,
                mDetailsHiringContactEmailTitle,mDetailsHiringResFromText, mDetailsHiringContactNameText, mDetailsHiringOtherInfoText,
                mDetailsHiringContactEmailText;
        public ImageButton mDetailsShareBtn, mDetailsBookMark;
        public ImageView mDetailsCompanyLogo;
        public Button mDetailsReadMoreBtn, mDetailsApplyBtn;
        public ConstraintLayout mRequirementConstraint, mBenefitConstraint, mHiringResourceConstraint;

        public JobDetailsViewHolder(View itemView) {
            super(itemView);

            mDetailsJobType = (TextView) itemView.findViewById(R.id.job_details_type_tag);
            mDetailsJobTitle = (TextView) itemView.findViewById(R.id.home_job_title);
            mDetailsJobPostedText = (TextView) itemView.findViewById(R.id.job_details_posted_text);
            mDetailsJobDate = (TextView) itemView.findViewById(R.id.job_details_posted_date);
            mDetailsCompanyTitle = (TextView) itemView.findViewById(R.id.job_details_company_title);
            mDetailsLocationTitle = (TextView) itemView.findViewById(R.id.job_details_location_title);
            mDetailsCompanyName = (TextView) itemView.findViewById(R.id.job_details_company_name);
            mDetailsLocationName = (TextView) itemView.findViewById(R.id.job_details_location_name);
            mDetailsSalaryTitle = (TextView) itemView.findViewById(R.id.job_details_salary_title);
            mDetailsSalaryNum = (TextView) itemView.findViewById(R.id.job_details_salary_num);
            mDetailsSalaryUnit = (TextView) itemView.findViewById(R.id.job_details_salary_unit);
            mDetailsDesTitle = (TextView) itemView.findViewById(R.id.job_details_des_title);
            mDetailsJobDesContent = (TextView) itemView.findViewById(R.id.job_details_des_content);
            mDetailsBenefitTitle = (TextView) itemView.findViewById(R.id.job_details_benifit_title);
            mDetailsChildCare = (TextView) itemView.findViewById(R.id.job_details_benefit_childcare);
            mDetailsCoffee = (TextView) itemView.findViewById(R.id.job_details_benefit_cafe);
            mDetailsCommute = (TextView) itemView.findViewById(R.id.job_details_benefit_commute);
            mDetailsDental = (TextView) itemView.findViewById(R.id.job_details_benefit_dental);
            mDetailsGym = (TextView) itemView.findViewById(R.id.job_details_benefit_gym);
            mDetailsMaternity = (TextView) itemView.findViewById(R.id.job_details_benefit_preganent);
            mDetailsMedicalInsurance = (TextView) itemView.findViewById(R.id.job_details_benefit_hospital);
            mDetailsMentorProgram = (TextView) itemView.findViewById(R.id.job_details_benefit_mentor);
            mDetailsMovie = (TextView) itemView.findViewById(R.id.job_details_benefit_movie);
            mDetailsPaidVacation = (TextView) itemView.findViewById(R.id.job_details_benefit_vacation);
            mDetailsParentalLeave = (TextView) itemView.findViewById(R.id.job_details_benefit_parental);
            mDetailsReading = (TextView) itemView.findViewById(R.id.job_details_benefit_reading);
            mDetailsSavingPlan = (TextView) itemView.findViewById(R.id.job_details_benefit_saving_plan);
            mDetailsSnacks = (TextView) itemView.findViewById(R.id.job_details_benefit_food);
            mDetailsTuition = (TextView) itemView.findViewById(R.id.job_details_benefit_tuition);
            mDetailsVisionInsurance = (TextView) itemView.findViewById(R.id.job_details_benefit_vision);
            mDetailsWorkFromHome = (TextView) itemView.findViewById(R.id.job_details_benefit_workfromhome);
            mDetailsDisabilityInsurance = (TextView) itemView.findViewById(R.id.job_details_benefit_disability);
            mDetailsTraining = (TextView) itemView.findViewById(R.id.job_details_benefit_training);
            mDetailsHiringResTitle = (TextView) itemView.findViewById(R.id.job_details_hiring_resource_title);
            mDetailsHiringResFromTitle = (TextView) itemView.findViewById(R.id.job_details_hiring_resource_source_from_title);
            mDetailsHiringResFromText = (TextView) itemView.findViewById(R.id.job_details_hiring_resource_source_from_text);
            mDetailsHiringContactNameTitle = (TextView) itemView.findViewById(R.id.job_details_hiring_resource_contact_name_title);
            mDetailsHiringContactNameText = (TextView) itemView.findViewById(R.id.job_details_hiring_resource_contact_name_text);
            mDetailsHiringContactEmailTitle = (TextView) itemView.findViewById(R.id.job_details_hiring_resource_contact_email_title);
            mDetailsHiringContactEmailText = (TextView) itemView.findViewById(R.id.job_details_hiring_resource_contact_email_text);
            mDetailsHiringOtherInfoTitle = (TextView) itemView.findViewById(R.id.job_details_hiring_resource_other_info_title);
            mDetailsHiringOtherInfoText = (TextView) itemView.findViewById(R.id.job_details_hiring_resource_other_info_text);

            mDetailsShareBtn = (ImageButton) itemView.findViewById(R.id.job_details_share_btn);
            mDetailsBookMark = (ImageButton) itemView.findViewById(R.id.job_details_bookmark_btn);
            mDetailsApplyBtn = (Button) itemView.findViewById(R.id.job_details_apply_btn);
            mDetailsReadMoreBtn = (Button) itemView.findViewById(R.id.job_details_readmore_btn);

            mDetailsCompanyLogo = (ImageView) itemView.findViewById(R.id.job_details_company_logo);

            mRequirementConstraint = (ConstraintLayout) itemView.findViewById(R.id.job_details_requirement_constraint);
            mBenefitConstraint = (ConstraintLayout) itemView.findViewById(R.id.job_details_benefit_container_constraint);
            mHiringResourceConstraint = (ConstraintLayout) itemView.findViewById(R.id.job_details_hiring_resource_container_constraint);

            mDetailsReadMoreBtn.setOnClickListener(this);


        }

        public TextView getDetailsJobType(){return mDetailsJobType;}
        public TextView getDetailsJobTitle(){return mDetailsJobTitle;}
        public TextView getDetailsJobDate(){return mDetailsJobDate;}
        public TextView getDetailsCompanyName(){return mDetailsCompanyName;}
        public TextView getDetailsLocationName(){return mDetailsLocationName;}
        public TextView getDetailsSalaryNum(){return mDetailsSalaryNum;}
        public TextView getDetailsJobDesContent(){return mDetailsJobDesContent;}
        public TextView getDetailsChildCare(){return mDetailsChildCare;}
        public TextView getDetailsCoffee(){return mDetailsCoffee;}
        public TextView getDetailsCommute(){return mDetailsCommute;}
        public TextView getDetailsDental(){return mDetailsDental;}
        public TextView getDetailsGym(){return mDetailsGym;}
        public TextView getDetailsMaternity(){return mDetailsMaternity;}
        public TextView getDetailsMedicalInsurance(){return mDetailsMedicalInsurance;}
        public TextView getDetailsMentorProgram(){return mDetailsMentorProgram;}
        public TextView getDetailsPaidVacation(){return mDetailsPaidVacation;}
        public TextView getDetailsParentalLeave(){return mDetailsParentalLeave;}
        public TextView getDetailsReading(){return mDetailsReading;}
        public TextView getDetailsSavingPlan(){return mDetailsSavingPlan;}
        public TextView getDetailsSnacks(){return mDetailsSnacks;}
        public TextView getDetailsTuition(){return mDetailsTuition;}
        public TextView getDetailsVisionInsurance(){return mDetailsVisionInsurance;}
        public TextView getDetailsWorkFromHome(){return mDetailsWorkFromHome;}
        public TextView getDetailsDisabilityInsurance(){return mDetailsDisabilityInsurance;}
        public TextView getDetailsTraining(){return mDetailsTraining;}
        public TextView getDetailsHiringResFromText(){return mDetailsHiringResFromText;}
        public TextView getDetailsHiringContactNameText(){return mDetailsHiringContactNameText;}
        public TextView getDetailsHiringContactEmailText(){return mDetailsHiringContactEmailText;}
        public TextView getDetailsHiringOtherInfoText(){return mDetailsHiringOtherInfoText;}
        public ImageView getmDetailsCompanyLogo(){return mDetailsCompanyLogo;}

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.job_details_readmore_btn){
                mDetailsJobDesContent.setMaxLines(Integer.MAX_VALUE);
                mDetailsReadMoreBtn.setVisibility(View.INVISIBLE);
                mRequirementConstraint.setVisibility(View.VISIBLE);
                mBenefitConstraint.setVisibility(View.VISIBLE);
                mHiringResourceConstraint.setVisibility(View.VISIBLE);
            }
        }
    }

    public void updateJobs(Jobs job){
        mJob = job;
        notifyItemChanged(0);
        Log.d("Chloe", "mJob title: " + mJob.getTitle());
    }


    @Override
    public int getItemCount() {
        return 1;
    }
}
