package thhsu.chloe.ModelHub.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import thhsu.chloe.ModelHub.home.HomeContract;
//import thhsu.chloe.jeeva.Profile.ProfileFragment;
import thhsu.chloe.ModelHub.R;
import thhsu.chloe.ModelHub.utils.CircleTransform;
import thhsu.chloe.ModelHub.api.model.Jobs;

/**
 * Created by Chloe on 5/2/2018.
 */

public class HomeJobUrgentAdapter extends RecyclerView.Adapter{
    private HomeContract.Presenter mPresenter;
    private ArrayList<Jobs> mJobs;

    HomeJobUrgentAdapter(HomeContract.Presenter presenter, ArrayList<Jobs> jobs){
        if (presenter != null){
            this.mPresenter = presenter;
        }
        this.mJobs = new ArrayList<Jobs>();

        for(Jobs job : jobs){
            if(job.getRecommended()){
                mJobs.add(job);
            }
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizontal_home, parent, false);
        return new HomeJobUrgentItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof HomeJobUrgentItemViewHolder){
            if (mJobs.size() > 0){
                (((HomeJobUrgentItemViewHolder) holder).getUrgentJobTitle()).setText(mJobs.get(position).getTitle());
                (((HomeJobUrgentItemViewHolder) holder).getUrgentJobLocation()).setText(mJobs.get(position).getLocation());
                (((HomeJobUrgentItemViewHolder) holder).getUrgentJobDate()).setText(mJobs.get(position).getShootingDate());

                if (mJobs.get(position).getImage().equals("")){
                    Picasso.get().load(R.drawable.modelhub_color_font_edited).fit().
                            into(((HomeJobUrgentItemViewHolder) holder).getUrgentJobImage());

                }else {
                    Picasso.get().load(mJobs.get(position).getImage()).fit().placeholder(R.drawable.modelhub_color_font_edited).
                            into(((HomeJobUrgentItemViewHolder) holder).getUrgentJobImage());
                }

                if (mJobs.get(position).getLogo().equals("")){
                    Picasso.get().load(R.drawable.all_placeholder_avatar).transform(new CircleTransform()).
                            into(((HomeJobUrgentItemViewHolder) holder).getUrgentJobCompanyLogo());
                }else{
                    Picasso.get().load(mJobs.get(position).getLogo()).transform(new CircleTransform()).
                            into(((HomeJobUrgentItemViewHolder) holder).getUrgentJobCompanyLogo());
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return mJobs.size();
    }

    private class HomeJobUrgentItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView mImageViewUrgentJobImage, mImageViewUrgentJobCompanyLogo;
        private TextView mTextViewUrgentJobTitle, mTextViewUrgentJobLocation, mTextViewUrgentJobDate;

        private HomeJobUrgentItemViewHolder(View itemView) {
            super(itemView);

            mTextViewUrgentJobDate = (TextView) itemView.findViewById(R.id.textview_urgent_job_date);
            mTextViewUrgentJobTitle = (TextView) itemView.findViewById(R.id.textview_urgent_job_title);
            mTextViewUrgentJobLocation = (TextView) itemView.findViewById(R.id.textview_urgent_job_location);
            mImageViewUrgentJobImage = (ImageView) itemView.findViewById(R.id.imageview_urgent_job_image);
            mImageViewUrgentJobCompanyLogo = (ImageView) itemView.findViewById(R.id.imageview_urgent_company_logo);
            ((CardView) itemView.findViewById(R.id.cardView_urgent_job_item)).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.cardView_urgent_job_item) mPresenter.openJobDetails(mJobs.get(getAdapterPosition())); // setOpenCase here  getAdapterPosition()
        }

        private TextView getUrgentJobDate(){return mTextViewUrgentJobDate;}
        private TextView getUrgentJobTitle(){return mTextViewUrgentJobTitle;}
        private TextView getUrgentJobLocation(){return mTextViewUrgentJobLocation;}
        private ImageView getUrgentJobImage(){return mImageViewUrgentJobImage;}
        private ImageView getUrgentJobCompanyLogo(){return mImageViewUrgentJobCompanyLogo;}

    }
}
