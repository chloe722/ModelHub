package thhsu.chloe.ModelHub.AboutPage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import thhsu.chloe.ModelHub.ModelHub;
import thhsu.chloe.ModelHub.R;
import thhsu.chloe.ModelHub.adapters.AboutPageTermOfUseAdapter;

/**
 * Created by Chloe on 5/15/2018.
 */

public class AboutTermOfUseFragment extends Fragment {
    private AboutPageTermOfUseAdapter mTermOfUseAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTermOfUseAdapter = new AboutPageTermOfUseAdapter();
    }

    public static AboutTermOfUseFragment newInstance () { return new AboutTermOfUseFragment();}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_aboutpage_termofuse_recyyclerview, container, false);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.fragment_aboutpage_termofuse_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(ModelHub.getAppContext()));
        recyclerView.setAdapter(mTermOfUseAdapter);

        return root;
    }
}
