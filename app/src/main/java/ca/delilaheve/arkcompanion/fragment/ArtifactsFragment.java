package ca.delilaheve.arkcompanion.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.delilaheve.arkcompanion.R;

public class ArtifactsFragment extends Fragment {

    public ArtifactsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artifact_detail, container, false);

        return view;
    }
}
