package thhsu.chloe.jeeva.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import thhsu.chloe.jeeva.R;

/**
 * Created by Chloe on 5/15/2018.
 */

public class AboutPageTermOfUseAdapter extends RecyclerView.Adapter<AboutPageTermOfUseAdapter.termsOfUseViewHolder> {

    @NonNull
    @Override
    public termsOfUseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_aboutpage_termofuse, parent, false);
        return new termsOfUseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull termsOfUseViewHolder holder, int position) {
    }


    public class termsOfUseViewHolder extends RecyclerView.ViewHolder{
        public termsOfUseViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
