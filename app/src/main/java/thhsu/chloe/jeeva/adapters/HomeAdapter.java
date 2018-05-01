package thhsu.chloe.jeeva.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import thhsu.chloe.jeeva.R;

/**
 * Created by Chloe on 5/1/2018.
 */

public class HomeAdapter extends RecyclerView.Adapter {

    public ArrayList<String> recommendedJobTitleList;
    public ArrayList<String> recommendedJobCompanyNameList;
    boolean isHorizontalList;
    private ArrayList<String> jobCompanyNameList;
    private ArrayList<String> jobTitleList;
    private ArrayList<String> jobTitle;

    public HomeAdapter(ArrayList<String> recommendedJobTitleList, ArrayList<String> recommendedJobCompanyNameList, boolean isHorizontalList){
        this.recommendedJobTitleList = recommendedJobTitleList;
        this.recommendedJobCompanyNameList = recommendedJobCompanyNameList;
        this.isHorizontalList = isHorizontalList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        if(isHorizontalList){
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizontal_home, parent, false);
        }else{
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vertical_home, parent, false);
        }
//        return new RecyclerView.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
