package ca.delilaheve.arkcompanion.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import ca.delilaheve.arkcompanion.FragmentContainerActivity;
import ca.delilaheve.arkcompanion.R;
import ca.delilaheve.arkcompanion.adapter.MainAdapter;

public class MainGridFragment extends Fragment {

    // Empty constructor required by Fragment
    public MainGridFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        GridView view = (GridView) inflater.inflate(R.layout.fragment_grid, container, false);
        view.setAdapter(new MainAdapter(getActivity(), true));

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Start activity with extra for item chosen
                Intent intent = new Intent(getActivity(), FragmentContainerActivity.class);
                intent.putExtra("ItemPos", position);
                startActivity(intent);
            }
        });

        return view;
    }
}
