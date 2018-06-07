package thhsu.chloe.ModelHub.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import thhsu.chloe.ModelHub.R;

/**
 * Created by Chloe on 5/31/2018.
 */

public class ProfileWorkbookAdapter extends RecyclerView.Adapter<ProfileWorkbookAdapter.ProfileWorkbookViewHolder> {
    @NonNull
    @Override
    public ProfileWorkbookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_profile_workbook, parent, false);
        return new ProfileWorkbookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileWorkbookViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 1;
    }

    public class ProfileWorkbookViewHolder extends RecyclerView.ViewHolder{
        private TextView mComingSoonText;
        public ProfileWorkbookViewHolder(View itemView) {
            super(itemView);
            mComingSoonText = (TextView) itemView.findViewById(R.id.comingsoon_text);
        }
    }
}
