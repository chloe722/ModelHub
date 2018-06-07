package thhsu.chloe.ModelHub.profile;



//import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import thhsu.chloe.ModelHub.ModelHub;
import thhsu.chloe.ModelHub.R;
import thhsu.chloe.ModelHub.adapters.ProfileWorkbookAdapter;

/**
 * Created by Chloe on 5/31/2018.
 */

public class ProfileWorkbookFragment extends Fragment {
    ProfileWorkbookAdapter mAdapter;
    public ProfileWorkbookFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new ProfileWorkbookAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile_workbook, container, false);

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.profile_workbook_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(ModelHub.getAppContext()));
        recyclerView.setAdapter(mAdapter);

        return root;
    }
}
