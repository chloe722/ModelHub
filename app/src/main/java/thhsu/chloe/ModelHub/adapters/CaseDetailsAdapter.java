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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import thhsu.chloe.ModelHub.ModelHub;
import thhsu.chloe.ModelHub.CaseDetails.CaseDetailsContract;
import thhsu.chloe.ModelHub.R;
import thhsu.chloe.ModelHub.Utils.CircleTransform;
import thhsu.chloe.ModelHub.Utils.Constants;
import thhsu.chloe.ModelHub.api.model.Cases;

/**
 * Created by Chloe on 5/6/2018.
 */

public class CaseDetailsAdapter extends RecyclerView.Adapter<CaseDetailsAdapter.CaseDetailsViewHolder> {
    public Context mContext;
    public CaseDetailsContract.Presenter mCaseDetailsPresenter;
    public Cases mCases;
    SharedPreferences sharedPreferences;
    String token;

    public CaseDetailsAdapter(Context context, Cases acase, CaseDetailsContract.Presenter presenter){
        this.mContext = context;
        mCaseDetailsPresenter = presenter;
        this.mCases = acase == null ? new Cases() : acase;
    }

    @NonNull
    @Override
        public CaseDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fragment_case_details, parent, false);
        sharedPreferences = ModelHub.getAppContext().getSharedPreferences(Constants.USER_DATA, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(Constants.USER_TOKEN, "");
        return new CaseDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CaseDetailsViewHolder holder, int position) {
        Log.d("Chloe", "case title:" + mCases.getTitle());
        (holder.getDetailsCaseTitle()).setText(mCases.getTitle());
        (holder.getDetailsCaseDate()).setText(mCases.getDatePosted());
        (holder.getDetailsCaseDesContent()).setText(mCases.getDescription());
        (holder.getDetailsCompanyName()).setText(mCases.getCompany());
        (holder.getDetailsSalaryNum()).setText(mCases.getSalary());
        (holder.getDetailsLocationName()).setText(mCases.getLocation());
        (holder.getDetailsHiringContactNameText()).setText(mCases.getHiring_contact_name());
        (holder.getDetailsHiringContactEmailText()).setText(mCases.getHiring_contact_email());
        (holder.getDetailsHiringOtherInfoText()).setText(mCases.getHiring_other_info());
        (holder.getDetailsRequirement()).setText(mCases.getRequirements());

        if(!token.equals("")){
            if(ModelHub.getModelHubSQLHelper().getInterest(mCases.getId())){
                Log.d("Chloe", "case details true");
                holder.getDetailsBookMark().setImageResource(R.drawable.ic_bookmark_red_24dp);
            }else{
                Log.d("Chloe", "false");
                holder.getDetailsBookMark().setImageResource(R.drawable.ic_bookmark_border_red_24dp);
            }
        }else{
            holder.getDetailsBookMark().setImageResource(R.drawable.ic_bookmark_border_red_24dp);
        }

        for(String benefit : mCases.getBenefits()){
            switch (benefit){
                case "child_care":
                    holder.getDetailsChildCare().setVisibility(View.VISIBLE);
                    break;
                case "local_cafe":
                    holder.getDetailsCoffee().setVisibility(View.VISIBLE);
                    break;
                case "commute":
                    holder.getDetailsCommute().setVisibility(View.VISIBLE);
                    break;
                case "gym":
                    holder.getDetailsGym().setVisibility(View.VISIBLE);
                    break;
                case"dental_insurance":
                    holder.getDetailsDental().setVisibility(View.VISIBLE);
                    break;
                case "life_insurance":
                    holder.getDetailsLifeInsurance().setVisibility(View.VISIBLE);
                    break;
                case "paid_vacation":
                    holder.getDetailsPaidVacation().setVisibility(View.VISIBLE);
                    break;
                case "movie":
                    holder.getDetailsMovie().setVisibility(View.VISIBLE);
                    break;
                case "tuition":
                    holder.getDetailsTuition().setVisibility(View.VISIBLE);
                    break;
                case "saving_plan":
                    holder.getDetailsSavingPlan().setVisibility(View.VISIBLE);
                    break;
                case "reading_room":
                    holder.getDetailsReading().setVisibility(View.VISIBLE);
                    break;
                case "vision_insurance":
                    holder.getDetailsVisionInsurance().setVisibility(View.VISIBLE);
                    break;
                case "work_from_home":
                    holder.getDetailsWorkFromHome().setVisibility(View.VISIBLE);
                    break;
                case "disability_insurance":
                    holder.getDetailsDisabilityInsurance().setVisibility(View.VISIBLE);
                    break;
                case "maternity_leave":
                    holder.getDetailsMaternity().setVisibility(View.VISIBLE);
                    break;
                case "medical_insurance":
                    holder.getDetailsMedicalInsurance().setVisibility(View.VISIBLE);
                    break;
                case "mentor_program":
                    holder.getDetailsMentorProgram().setVisibility(View.VISIBLE);
                    break;
                case "parental_leave":
                    holder.getDetailsParentalLeave().setVisibility(View.VISIBLE);
                    break;
                case "snacks_drinks":
                    holder.getDetailsSnacks().setVisibility(View.VISIBLE);
                    break;
                case "training":
                    holder.getDetailsTraining().setVisibility(View.VISIBLE);
                    break;
            }
        }


        if(mCases.getLogo() == "" &&  (holder.getDetailsCompanyLogo() != null) ){
            Picasso.get().load(R.drawable.all_placeholder_avatar).transform(new CircleTransform()).into(holder.getDetailsCompanyLogo());
        }else{
            Picasso.get().load(mCases.getLogo()).transform(new CircleTransform()).into(holder.getDetailsCompanyLogo());
        }


        if (mCases.getType().equals("fulltime")){
            (holder.getDetailsCaseType()).setText("Full-time");
            (holder.getDetailsCaseType()).setBackgroundResource(R.drawable.yellow_rounded_shape);
        }else if(mCases.getType().equals("parttime")){
            (holder.getDetailsCaseType()).setText("Part-time");
        }else if(mCases.getType().equals("intern")){
            (holder.getDetailsCaseType()).setText("Intern");
        }

    }

    public class CaseDetailsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mDetailsCaseType, mDetailsCaseTitle, mDetailsCasePostedText, mDetailsCaseDate,
                mDetailsCompanyTitle, mDetailsLocationTitle, mDetailsCompanyName, mDetailsLocationName,
                mDetailsSalaryTitle, mDetailsSalaryNum, mDetailsSalaryUnit, mDetailsDesTitle, mDetailsCaseDesContent,
                mDetailsBenefitTitle, mDetailsChildCare, mDetailsCoffee, mDetailsCommute, mDetailsDental, mDetailsGym,
                mDetailsMaternity, mDetailsMedicalInsurance, mDetailsMentorProgram, mDetailsMovie, mDetailsPaidVacation,
                mDetailsParentalLeave, mDetailsReading, mDetailsSavingPlan, mDetailsSnacks, mDetailsTuition,mDetailsRequirement,
                mDetailsVisionInsurance, mDetailsWorkFromHome, mDetailsDisabilityInsurance, mDetailsTraining,
                mDetailsLifeInsurance, mDetailsHiringResTitle, mDetailsHiringResFromTitle, mDetailsHiringContactNameTitle, mDetailsHiringOtherInfoTitle,
                mDetailsHiringContactEmailTitle,mDetailsHiringResFromText, mDetailsHiringContactNameText, mDetailsHiringOtherInfoText,
                mDetailsHiringContactEmailText;
        public ImageButton mDetailsShareBtn, mDetailsBookMark;
        public ImageView mDetailsCompanyLogo;
        public Button mDetailsReadMoreBtn, mDetailsApplyBtn;
        public ConstraintLayout mRequirementConstraint, mBenefitConstraint, mHiringResourceConstraint;

        public CaseDetailsViewHolder(View itemView) {
            super(itemView);

            mDetailsRequirement = (TextView) itemView.findViewById(R.id.case_details_requirement_content);
            mDetailsCaseType = (TextView) itemView.findViewById(R.id.case_details_type_tag);
            mDetailsCaseTitle = (TextView) itemView.findViewById(R.id.home_case_title);
            mDetailsCasePostedText = (TextView) itemView.findViewById(R.id.case_details_posted_text);
            mDetailsCaseDate = (TextView) itemView.findViewById(R.id.case_details_posted_date);
            mDetailsCompanyTitle = (TextView) itemView.findViewById(R.id.case_details_company_title);
            mDetailsLocationTitle = (TextView) itemView.findViewById(R.id.case_details_location_title);
            mDetailsCompanyName = (TextView) itemView.findViewById(R.id.case_details_company_name);
            mDetailsLocationName = (TextView) itemView.findViewById(R.id.case_details_location_name);
            mDetailsSalaryTitle = (TextView) itemView.findViewById(R.id.case_details_salary_title);
            mDetailsSalaryNum = (TextView) itemView.findViewById(R.id.case_details_salary_num);
            mDetailsSalaryUnit = (TextView) itemView.findViewById(R.id.case_details_salary_unit);
            mDetailsDesTitle = (TextView) itemView.findViewById(R.id.case_details_des_title);
            mDetailsCaseDesContent = (TextView) itemView.findViewById(R.id.case_details_des_content);
            mDetailsBenefitTitle = (TextView) itemView.findViewById(R.id.case_details_benifit_title);
            mDetailsChildCare = (TextView) itemView.findViewById(R.id.case_details_benefit_childcare);
            mDetailsCoffee = (TextView) itemView.findViewById(R.id.case_details_benefit_cafe);
            mDetailsCommute = (TextView) itemView.findViewById(R.id.case_details_benefit_commute);
            mDetailsDental = (TextView) itemView.findViewById(R.id.case_details_benefit_dental);
            mDetailsGym = (TextView) itemView.findViewById(R.id.case_details_benefit_gym);
            mDetailsLifeInsurance = (TextView) itemView.findViewById(R.id.case_details_benefit_life_insurance);
            mDetailsMaternity = (TextView) itemView.findViewById(R.id.case_details_benefit_preganent);
            mDetailsMedicalInsurance = (TextView) itemView.findViewById(R.id.case_details_benefit_hospital);
            mDetailsMentorProgram = (TextView) itemView.findViewById(R.id.case_details_benefit_mentor);
            mDetailsMovie = (TextView) itemView.findViewById(R.id.case_details_benefit_movie);
            mDetailsPaidVacation = (TextView) itemView.findViewById(R.id.case_details_benefit_vacation);
            mDetailsParentalLeave = (TextView) itemView.findViewById(R.id.case_details_benefit_parental);
            mDetailsReading = (TextView) itemView.findViewById(R.id.case_details_benefit_reading);
            mDetailsSavingPlan = (TextView) itemView.findViewById(R.id.case_details_benefit_saving_plan);
            mDetailsSnacks = (TextView) itemView.findViewById(R.id.case_details_benefit_food);
            mDetailsTuition = (TextView) itemView.findViewById(R.id.case_details_benefit_tuition);
            mDetailsVisionInsurance = (TextView) itemView.findViewById(R.id.case_details_benefit_vision);
            mDetailsWorkFromHome = (TextView) itemView.findViewById(R.id.case_details_benefit_workfromhome);
            mDetailsDisabilityInsurance = (TextView) itemView.findViewById(R.id.case_details_benefit_disability);
            mDetailsTraining = (TextView) itemView.findViewById(R.id.case_details_benefit_training);
            mDetailsHiringResTitle = (TextView) itemView.findViewById(R.id.case_details_hiring_resource_title);
            mDetailsHiringResFromTitle = (TextView) itemView.findViewById(R.id.case_details_hiring_resource_source_from_title);
            mDetailsHiringResFromText = (TextView) itemView.findViewById(R.id.case_details_hiring_resource_source_from_text);
            mDetailsHiringContactNameTitle = (TextView) itemView.findViewById(R.id.case_details_hiring_resource_contact_name_title);
            mDetailsHiringContactNameText = (TextView) itemView.findViewById(R.id.case_details_hiring_resource_contact_name_text);
            mDetailsHiringContactEmailTitle = (TextView) itemView.findViewById(R.id.case_details_hiring_resource_contact_email_title);
            mDetailsHiringContactEmailText = (TextView) itemView.findViewById(R.id.case_details_hiring_resource_contact_email_text);
            mDetailsHiringOtherInfoTitle = (TextView) itemView.findViewById(R.id.case_details_hiring_resource_other_info_title);
            mDetailsHiringOtherInfoText = (TextView) itemView.findViewById(R.id.case_details_hiring_resource_other_info_text);

            mDetailsShareBtn = (ImageButton) itemView.findViewById(R.id.case_details_share_btn);
            mDetailsBookMark = (ImageButton) itemView.findViewById(R.id.case_details_bookmark_btn);
            mDetailsApplyBtn = (Button) itemView.findViewById(R.id.case_details_apply_btn);
            mDetailsReadMoreBtn = (Button) itemView.findViewById(R.id.case_details_readmore_btn);

            mDetailsCompanyLogo = (ImageView) itemView.findViewById(R.id.case_details_company_logo);

            mRequirementConstraint = (ConstraintLayout) itemView.findViewById(R.id.case_details_requirement_constraint);
            mBenefitConstraint = (ConstraintLayout) itemView.findViewById(R.id.case_details_benefit_container_constraint);
            mHiringResourceConstraint = (ConstraintLayout) itemView.findViewById(R.id.case_details_hiring_resource_container_constraint);

            mDetailsReadMoreBtn.setOnClickListener(this);
            mDetailsBookMark.setOnClickListener(this);
            mDetailsShareBtn.setOnClickListener(this);


        }

        public TextView getDetailsCaseType(){return mDetailsCaseType;}
        public TextView getDetailsCaseTitle(){return mDetailsCaseTitle;}
        public TextView getDetailsCaseDate(){return mDetailsCaseDate;}
        public TextView getDetailsCompanyName(){return mDetailsCompanyName;}
        public TextView getDetailsLocationName(){return mDetailsLocationName;}
        public TextView getDetailsSalaryNum(){return mDetailsSalaryNum;}
        public TextView getDetailsCaseDesContent(){return mDetailsCaseDesContent;}
        public TextView getDetailsChildCare(){return mDetailsChildCare;}
        public TextView getDetailsCoffee(){return mDetailsCoffee;}
        public TextView getDetailsCommute(){return mDetailsCommute;}
        public TextView getDetailsDental(){return mDetailsDental;}
        public TextView getDetailsGym(){return mDetailsGym;}
        public TextView getDetailsLifeInsurance(){return mDetailsLifeInsurance; }
        public TextView getDetailsMaternity(){return mDetailsMaternity;}
        public TextView getDetailsMedicalInsurance(){return mDetailsMedicalInsurance;}
        public TextView getDetailsMentorProgram(){return mDetailsMentorProgram;}
        public TextView getDetailsPaidVacation(){return mDetailsPaidVacation;}
        public TextView getDetailsMovie(){return mDetailsMovie;}
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
        public TextView getDetailsRequirement(){return mDetailsRequirement;}
        public ImageView getDetailsCompanyLogo(){return mDetailsCompanyLogo;}
        public ImageButton getDetailsBookMark(){return mDetailsBookMark;}

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.case_details_readmore_btn:
                    mDetailsCaseDesContent.setMaxLines(Integer.MAX_VALUE);
                    mDetailsReadMoreBtn.setVisibility(View.INVISIBLE);
                    mRequirementConstraint.setVisibility(View.VISIBLE);
                    mBenefitConstraint.setVisibility(View.VISIBLE);
                    mHiringResourceConstraint.setVisibility(View.VISIBLE);
                    break;
                case R.id.case_details_bookmark_btn:
                    if(!token.equals("")){
                        if(ModelHub.getModelHubSQLHelper().getInterest(mCases.getId())){
                            mCaseDetailsPresenter.updateInterestCase(mCases, false);
                            mDetailsBookMark.setImageResource(R.drawable.ic_bookmark_border_red_24dp);
                            break;
                        }else{
                            mCaseDetailsPresenter.updateInterestCase(mCases, true);
                            Log.d("Chloe", "is saved: " + ModelHub.getModelHubSQLHelper().getInterest(mCases.getId()) );
                            mDetailsBookMark.setImageResource(R.drawable.ic_bookmark_red_24dp);
                            break;
                        }
                    }else{
                        Toast.makeText(ModelHub.getAppContext(), "You are not logged in yet", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.case_details_share_btn:
                    Intent intentToShare = new Intent(Intent.ACTION_SEND);
                    intentToShare.setType("text/plain");
                    intentToShare.putExtra(Intent.EXTRA_SUBJECT, "Testing");
                    intentToShare.putExtra(Intent.EXTRA_TEXT, "https://wetogether.skijur.com/cases/123");
                    mContext.startActivity(Intent.createChooser(intentToShare, "Title of the dialog the system will open"));
                    break;


            }
        }
    }

    public void updateCases(Cases acase){
        mCases = acase;
        notifyItemChanged(0);
        Log.d("Chloe", "mCases title: " + mCases.getTitle());
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
