package thhsu.chloe.ModelHub.adapters;

import android.content.Context;
import android.content.SharedPreferences;
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
import thhsu.chloe.ModelHub.api.model.Jobs;

/**
 * Created by Chloe on 5/1/2018.
 */

public class HomeAdapter extends RecyclerView.Adapter {

    private HomeContract.Presenter mPresenter;
    private ArrayList<Jobs> mJobs;
    SharedPreferences sharedPreferences;
    String token;
    private int mNextPaging;
    public IndefinitePagerIndicator indefinitePagerIndicator;

    public HomeAdapter(HomeContract.Presenter presenter, ArrayList<Jobs> jobs){
        mPresenter = presenter;
        this.mJobs = jobs;
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
        return (isNextPagingExist())? mJobs.size() +1 : mJobs.size();}

    @Override
    public int getItemViewType(int position) {
        return (position == 0)? Constants.VIEWTYPE_HOME_MAIN : Constants.VIEWTYPE_HOME_JOB_LIST;
    }

    private class HomeMainItemViewHolder extends RecyclerView.ViewHolder{
        private RecyclerView mRecyclerUrgent;
        private TextView mUrgentTitle, mJobTitle;


        private HomeMainItemViewHolder(View itemView) {
            super(itemView);

            mRecyclerUrgent = (RecyclerView) itemView.findViewById(R.id.home_horizontal_recyclerview);
            indefinitePagerIndicator = (IndefinitePagerIndicator) itemView.findViewById(R.id.recyclerview_pager_indicator);
            mUrgentTitle = (TextView) itemView.findViewById(R.id.horizontal_urgent_title);
        }
        private RecyclerView getRecyclerRecommend(){return mRecyclerUrgent;}
        private TextView getUrgentTitle(){return mUrgentTitle;}
        private TextView getCaseTitle(){return mJobTitle;}
    }

    private void bindMainItem(HomeMainItemViewHolder holder){
        holder.getRecyclerRecommend().setLayoutManager(new LinearLayoutManager(ModelHub.getAppContext(),
                LinearLayoutManager.HORIZONTAL, false));
        holder.getRecyclerRecommend().setOnFlingListener(null);
        new LinearSnapHelper().attachToRecyclerView(holder.getRecyclerRecommend());
        holder.getRecyclerRecommend().setAdapter(new HomeJobUrgentAdapter(mPresenter, mJobs));
        indefinitePagerIndicator.attachToRecyclerView(holder.getRecyclerRecommend());
    }

    private class HomeCasesItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mHomeCaseTitle, mHomeCaseDate, mHomeCaseLocation, mHomeCaseWhom, mHomeCasePay;
        private ImageView mHomeCaseCompanyLogo;
        private ImageButton mInterestIcnBtn;

        private HomeCasesItemViewHolder(View itemView) {
            super(itemView);

            mHomeCaseTitle = (TextView) itemView.findViewById(R.id.home_case_title);
            mHomeCaseLocation = (TextView) itemView.findViewById(R.id.home_case_location);
            mHomeCaseWhom = (TextView) itemView.findViewById(R.id.home_case_whom);
            mHomeCasePay = (TextView) itemView.findViewById(R.id.home_case_pay);
            mHomeCaseCompanyLogo = (ImageView) itemView.findViewById(R.id.home_case_company_logo);
            mInterestIcnBtn = (ImageButton) itemView.findViewById(R.id.home_case_interest_icn_btn);

            mInterestIcnBtn.setOnClickListener(this);
            ((CardView) itemView.findViewById(R.id.cardView_main_case_item)).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Jobs jobs = mJobs.get(getAdapterPosition()-1);
            Log.d("Chloe", "adapterPosition: " + (getAdapterPosition()-1));

            switch (v.getId()){
                case R.id.home_case_interest_icn_btn:
                    if(!token.equals("")){
                        if(ModelHub.getModelHubSQLHelper().getInterest(jobs.getId())){
                            mPresenter.updateInterest(jobs, false);
                            mInterestIcnBtn.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                            break;
                        }else{
                            mPresenter.updateInterest(jobs, true);
                            Log.d("Chloe", "is saved: " + ModelHub.getModelHubSQLHelper().getInterest(jobs.getId()) );
                            mInterestIcnBtn.setImageResource(R.drawable.ic_favorite_black_24dp);
                            break;
                        }
                    }else{
                        Toast.makeText(ModelHub.getAppContext(), "You are not logged in yet", Toast.LENGTH_SHORT).show();
                    }
                break;

                case R.id.cardView_main_case_item:
                    Log.d("Chloe", "getTitle in home adapter: " + mJobs.get(getAdapterPosition()-1).getTitle());
                    mPresenter.openCaseDetails(mJobs.get(getAdapterPosition()-1)); // setOpenJob here  getAdapterPosition()
                    break;
            }

        }

        private TextView getHomeCaseTitle(){return mHomeCaseTitle;}
//        private TextView getHomeCaseDate(){return mHomeCaseDate;}
        private TextView getHomeCaseLocation(){return mHomeCaseLocation;}
        private TextView getHomeCaseWhom(){return mHomeCaseWhom;}
        private TextView getHomeCasePay(){return mHomeCasePay;}
        private ImageView getHomeCaseCompanyLogo(){return mHomeCaseCompanyLogo;}
        private ImageButton getInterestIcnBtn(){return mInterestIcnBtn;}
    }

    public void bindHomeCasesItem(HomeCasesItemViewHolder holder, int position){
        holder.getHomeCaseTitle().setText(mJobs.get(position).getTitle());
        holder.getHomeCaseLocation().setText(mJobs.get(position).getLocation());
        holder.getHomeCaseWhom().setText(mJobs.get(position).getWhom());
        holder.getHomeCasePay().setText(mJobs.get(position).getIsPaid());


        if(holder.getHomeCaseCompanyLogo() != null && mJobs.get(position).getLogo() != null) {
            Picasso.get().load(mJobs.get(position).getLogo()).transform(new CircleTransform()).into(holder.getHomeCaseCompanyLogo());
        }


        Log.d("Chloe", "postiton: " + position );
        Log.d("Chloe", "postiton ID: " + mJobs.get(position).getId());
        Log.d("Chloe", "postiton title: " + mJobs.get(position).getTitle());
        if( !token.equals("")){
            if(ModelHub.getModelHubSQLHelper().getInterest(mJobs.get(position).getId())){
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

    public void updateData(ArrayList<Jobs> jobs){
        Log.d("Chloe", "HomeAdapter update data");
//        for (Jobs job : jobs){
//            mJobs.add(job);
//        }
        mJobs = jobs;
        setNextPaging(Constants.FIRST_PAGING);
        notifyDataSetChanged();

    }

    public void clearJobs(){
        mJobs.clear();
        notifyDataSetChanged();
    }

    public boolean isNextPagingExist(){
        return (mNextPaging == -1)? false:true;
    }
    private void setNextPaging(int nextPaging){mNextPaging = nextPaging;}
}
