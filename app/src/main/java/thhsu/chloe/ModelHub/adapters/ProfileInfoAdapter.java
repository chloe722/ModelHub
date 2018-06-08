//package thhsu.chloe.ModelHub.adapters;
//
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import thhsu.chloe.ModelHub.R;
//
///**
// * Created by Chloe on 5/31/2018.
// */
//
//public class ProfileInfoAdapter extends RecyclerView.Adapter<ProfileInfoAdapter.ProfileInfoViewHolder> {
//
//
//    @NonNull
//    @Override
//    public ProfileInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_profile_info, parent, false);
//        return new ProfileInfoViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ProfileInfoViewHolder holder, int position) {
//
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return 1;
//    }
//
//    public class ProfileInfoViewHolder extends RecyclerView.ViewHolder{
//        private TextView profileLanguageTitle, profileLanguageText, profileExperienceTitle, profileExperienceText;
//
//
//        public ProfileInfoViewHolder(View itemView) {
//            super(itemView);
//            profileLanguageTitle = (TextView) itemView.findViewById(R.id.profile_user_language_title);
//            profileLanguageText = (TextView) itemView.findViewById(R.id.profile_user_language_text);
//            profileExperienceTitle = (TextView) itemView.findViewById(R.id.profile_user_experience_title);
//            profileExperienceText = (TextView) itemView.findViewById(R.id.profile_user_experience_text);
//
//        }
//    }
//}
