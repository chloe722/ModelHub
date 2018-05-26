package thhsu.chloe.ModelHub.adapters;

import android.content.Context;
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

import retrofit2.http.PUT;
import thhsu.chloe.ModelHub.Home.HomeContract;
//import thhsu.chloe.jeeva.Profile.ProfileFragment;
import thhsu.chloe.ModelHub.R;
import thhsu.chloe.ModelHub.Utils.CircleTransform;
import thhsu.chloe.ModelHub.api.model.Cases;

/**
 * Created by Chloe on 5/2/2018.
 */

public class HomeCaseUrgentAdapter extends RecyclerView.Adapter{
    private Context mContext;
    private HomeContract.Presenter mPresenter;
    private ArrayList<Cases> mCases;

    public HomeCaseUrgentAdapter(HomeContract.Presenter presenter, ArrayList<Cases> cases){
        if (presenter != null){
            this.mPresenter = presenter;
        }
        this.mCases = new ArrayList<Cases>();

        for(Cases acase : cases){
            if(acase.getRecommended()){
                mCases.add(acase);
            }
        }
        Log.d("Chloe", "recommend adapter cases : " + mCases.size());
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizontal_home, parent, false);
        return new HomeCaseUrgentItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof HomeCaseUrgentItemViewHolder){
            if (mCases.size() > 0){
                (((HomeCaseUrgentItemViewHolder) holder).getUrgentCaseTitle()).setText(mCases.get(position).getTitle());
                (((HomeCaseUrgentItemViewHolder) holder).getUrgentCaseLocation()).setText(mCases.get(position).getLocation());
//                (((HomeCaseUrgentItemViewHolder) holder).getUrgentCaseDate()).setText(mCases.get(position).getDate());

                if (mCases.get(position).getImage().equals("")){
                    Picasso.get().load(R.drawable.modelhub_color_font_edited).fit().into(((HomeCaseUrgentItemViewHolder) holder).getUrgentCaseImage());

                }else {
                    Picasso.get().load(mCases.get(position).getImage()).fit().placeholder(R.drawable.modelhub_color_font_edited).into(((HomeCaseUrgentItemViewHolder) holder).getUrgentCaseImage());
                }

                if (mCases.get(position).getLogo().equals("")){
                    Picasso.get().load(R.drawable.all_placeholder_avatar).transform(new CircleTransform()).into(((HomeCaseUrgentItemViewHolder) holder).getUrgentCaseCompanyLogo());
                }else{
                    Picasso.get().load(mCases.get(position).getLogo()).transform(new CircleTransform()).into(((HomeCaseUrgentItemViewHolder) holder).getUrgentCaseCompanyLogo());
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return mCases.size();
    }

    private class HomeCaseUrgentItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView mUrgentCaseImage, mUrgentCaseCompanyLogo;
        private TextView mUrgentCaseTitle, mUrgentCaseLocation, mUrgentCaseDate;

        private HomeCaseUrgentItemViewHolder(View itemView) {
            super(itemView);

            mUrgentCaseImage = (ImageView) itemView.findViewById(R.id.urgent_case_image);
            mUrgentCaseCompanyLogo = (ImageView) itemView.findViewById(R.id.urgent_company_logo);
            mUrgentCaseTitle = (TextView) itemView.findViewById(R.id.urgent_case_title);
            mUrgentCaseLocation = (TextView) itemView.findViewById(R.id.urgent_case_location);
            mUrgentCaseDate = (TextView) itemView.findViewById(R.id.urgent_case_date);
            ((CardView) itemView.findViewById(R.id.cardView_urgent_case_item)).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.cardView_urgent_case_item) mPresenter.openCaseDetails(mCases.get(getAdapterPosition())); // setOpenCase here  getAdapterPosition()
        }

        private ImageView getUrgentCaseImage(){return mUrgentCaseImage;}
        private ImageView getUrgentCaseCompanyLogo(){return mUrgentCaseCompanyLogo;}
        private TextView getUrgentCaseTitle(){return mUrgentCaseTitle;}
        private TextView getUrgentCaseLocation(){return mUrgentCaseLocation;}
        private TextView getUrgentCaseDate(){return mUrgentCaseDate;}
    }
}
