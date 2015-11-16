package ca.delilaheve.arkcompanion.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import ca.delilaheve.arkcompanion.FragmentContainerActivity;
import ca.delilaheve.arkcompanion.R;
import ca.delilaheve.arkcompanion.adapter.MainAdapter;

public class MainListFragment extends Fragment {

    // Empty constructor required by Fragment
    public MainListFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ListView view = (ListView) inflater.inflate(R.layout.fragment_list, container, false);
        view.setAdapter(new MainAdapter(getActivity(), false));

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
