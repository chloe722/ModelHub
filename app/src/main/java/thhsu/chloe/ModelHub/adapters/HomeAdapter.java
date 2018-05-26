package thhsu.chloe.ModelHub.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rbrooks.indefinitepagerindicator.IndefinitePagerIndicator;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import thhsu.chloe.ModelHub.Home.HomeContract;
import thhsu.chloe.ModelHub.ModelHub;
import thhsu.chloe.ModelHub.R;
import thhsu.chloe.ModelHub.Utils.CircleTransform;
import thhsu.chloe.ModelHub.Utils.Constants;
import thhsu.chloe.ModelHub.api.model.Cases;

/**
 * Created by Chloe on 5/1/2018.
 */

public class HomeAdapter extends RecyclerView.Adapter {

    private HomeContract.Presenter mPresenter;
    private ArrayList<Cases> mCases;
    SharedPreferences sharedPreferences;
    String token;
    private int mNextPaging;
    public IndefinitePagerIndicator indefinitePagerIndicator;

    public HomeAdapter(HomeContract.Presenter presenter, ArrayList<Cases> cases){
        mPresenter = presenter;
        this.mCases = cases;
        this.mNextPaging = Constants.FIRST_PAGING;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        sharedPreferences = ModelHub.getAppContext().getSharedPreferences(Constants.USER_DATA, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(Constants.USER_TOKEN, "");

        if(viewType == Constants.VIEWTYPE_HOME_MAIN){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_home, parent, false);
            return new HomeMainItemViewHolder(view);
        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vertical_home, parent, false);
            return new HomeCasesItemViewHolder(view);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof HomeMainItemViewHolder){
            bindMainItem((HomeMainItemViewHolder) holder);
        }else if (holder instanceof HomeCasesItemViewHolder){
            bindHomeCasesItem((HomeCasesItemViewHolder) holder, position-1);
        }
    }

    @Override
    public int getItemCount() {
        return (isNextPagingExist())? mCases.size() +1 : mCases.size();}

    @Override
    public int getItemViewType(int position) {
        return (position == 0)? Constants.VIEWTYPE_HOME_MAIN : Constants.VIEWTYPE_HOME_CASE_LIST;
    }

    private class HomeMainItemViewHolder extends RecyclerView.ViewHolder{
        private RecyclerView mRecyclerUrgent;
        public TextView mUrgentTitle, mCaseTitle;


        public HomeMainItemViewHolder(View itemView) {
            super(itemView);

            mRecyclerUrgent = (RecyclerView) itemView.findViewById(R.id.home_horizontal_recyclerview);
            indefinitePagerIndicator = (IndefinitePagerIndicator) itemView.findViewById(R.id.recyclerview_pager_indicator);
            mUrgentTitle = (TextView) itemView.findViewById(R.id.horizontal_urgent_title);
        }
        public RecyclerView getRecyclerRecommend(){return mRecyclerUrgent;}
        public TextView getUrgentTitle(){return mUrgentTitle;}
        private TextView getCaseTitle(){return mCaseTitle;}
    }

    private void bindMainItem(HomeMainItemViewHolder holder){
        holder.getRecyclerRecommend().setLayoutManager(new LinearLayoutManager(ModelHub.getAppContext(),
                LinearLayoutManager.HORIZONTAL, false));
        holder.getRecyclerRecommend().setOnFlingListener(null);
        new LinearSnapHelper().attachToRecyclerView(holder.getRecyclerRecommend());
        holder.getRecyclerRecommend().setAdapter(new HomeCaseUrgentAdapter(mPresenter, mCases));
        indefinitePagerIndicator.attachToRecyclerView(holder.getRecyclerRecommend());
    }

    private class HomeCasesItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mHomeCaseTitle, mHomeCaseDate, mHomeCaseLocation, mHomeCaseWhom, mHomeCasePay;
        public ImageView mHomeCaseCompanyLogo;
        public ImageButton mInterestIcnBtn;

        public HomeCasesItemViewHolder(View itemView) {
            super(itemView);

            mHomeCaseTitle = (TextView) itemView.findViewById(R.id.home_case_title);
            mHomeCaseDate = (TextView) itemView.findViewById(R.id.home_case_date);
            mHomeCaseLocation = (TextView) itemView.findViewById(R.id.home_case_location);
            mHomeCaseWhom = (TextView) itemView.findViewById(R.id.home_case_whom);
            mHomeCasePay = (TextView) itemView.findViewById(R.id.home_case_pay);
            mHomeCaseCompanyLogo = (ImageView) itemView.findViewById(R.id.home_case_company_logo);
            mInterestIcnBtn = (ImageButton) itemView.findViewById(R.id.home_case_interest_icn_btn);

            mInterestIcnBtn.setOnClickListener(this);
//            ((ConstraintLayout) itemView.findViewById(R.id.constraintlayout_interest_item)).setOnClickListener(this);
            ((CardView) itemView.findViewById(R.id.cardView_main_case_item)).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Cases cases = mCases.get(getAdapterPosition()-1);
            Log.d("Chloe", "adapterPosition: " + (getAdapterPosition()-1));

            switch (v.getId()){
                case R.id.home_case_interest_icn_btn:
                    if(!token.equals("")){
                        if(ModelHub.getModelHubSQLHelper().getInterest(cases.getId())){
                            mPresenter.updateInterest(cases, false);
                            mInterestIcnBtn.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                            break;
                        }else{
                            mPresenter.updateInterest(cases, true);
                            Log.d("Chloe", "is saved: " + ModelHub.getModelHubSQLHelper().getInterest(cases.getId()) );
                            mInterestIcnBtn.setImageResource(R.drawable.ic_favorite_black_24dp);
                            break;
                        }
                    }else{
                        Toast.makeText(ModelHub.getAppContext(), "You are not logged in yet", Toast.LENGTH_SHORT).show();
                    }
                break;

                case R.id.constraintlayout_interest_item:
                    Log.d("Chloe", "getTitle in home adapter: " + mCases.get(getAdapterPosition()).getTitle());
                    mPresenter.openCaseDetails(mCases.get(getAdapterPosition()-1)); // setOpenJob here  getAdapterPosition()
                    break;
            }

        }

        public TextView getHomeCaseTitle(){return mHomeCaseTitle;}
        public TextView getHomeCaseDate(){return mHomeCaseDate;}
        public TextView getHomeCaseLocation(){return mHomeCaseLocation;}
        public TextView getHomeCaseWhom(){return mHomeCaseWhom;}
        public TextView getHomeCasePay(){return mHomeCasePay;}
        public ImageView getHomeCaseCompanyLogo(){return mHomeCaseCompanyLogo;}
        public ImageButton getInterestIcnBtn(){return mInterestIcnBtn;}
    }

    private void bindHomeCasesItem(HomeCasesItemViewHolder holder, int position){
        holder.getHomeCaseTitle().setText(mCases.get(position).getTitle());

//            (holder.getHomeCaseTypeTag()).setBackgroundResource(R.drawable.yellow_rounded_shape);

        if(holder.getHomeCaseCompanyLogo() != null && mCases.get(position).getLogo() != null) {
            Picasso.get().load(mCases.get(position).getLogo()).transform(new CircleTransform()).into(holder.getHomeCaseCompanyLogo());
        }


        Log.d("Chloe", "postiton: " + position );
        Log.d("Chloe", "postiton ID: " + mCases.get(position).getId());
        Log.d("Chloe", "postiton title: " + mCases.get(position).getTitle());
        if( !token.equals("")){
            if(ModelHub.getModelHubSQLHelper().getInterest(mCases.get(position).getId())){
                Log.d("Chloe", "true");
                holder.getInterestIcnBtn().setImageResource(R.drawable.ic_favorite_black_24dp);
            }else{
                Log.d("Chloe", "false");
                holder.getInterestIcnBtn().setImageResource(R.drawable.ic_favorite_border_black_24dp);
            }
        }else{
            holder.getInterestIcnBtn().setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }
    }

    public void updateData(ArrayList<Cases> cases){
        Log.d("Chloe", "HomeAdapter update data");
//        for (Cases job : cases){
//            mCases.add(job);
//        }
        mCases = cases;
        setNextPaging(Constants.FIRST_PAGING);
        notifyDataSetChanged();

    }

    public void clearCases(){
        mCases.clear();
        notifyDataSetChanged();
    }

    public boolean isNextPagingExist(){
        return (mNextPaging == -1)? false:true;
    }
    private void setNextPaging(int nextPaging){mNextPaging = nextPaging;}
}
