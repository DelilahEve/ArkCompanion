package ca.delilaheve.arkcompanion.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.net.URL;

import ca.delilaheve.arkcompanion.ArtifactDetailActivity;
import ca.delilaheve.arkcompanion.R;
import ca.delilaheve.arkcompanion.adapter.ArtifactAdapter;
import ca.delilaheve.arkcompanion.util.loader.ArtifactsLoader;
import ca.delilaheve.arkcompanion.util.asynctask.AsyncTaskImplementer;
import ca.delilaheve.arkcompanion.util.tools.AsyncFileRetriever;

public class ArtifactListFragment extends Fragment implements AsyncTaskImplementer {

    private AsyncFileRetriever retriever;

    public ArtifactListFragment() {}

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list, container, false);

        loadXml();

        return view;
    }

    private void loadXml(){
        // load XML files
        try {
            URL fileUrl = new URL("http://delilaheve.github.io/assets/ArkCompanion/xml/artifacts.xml");
            retriever = new AsyncFileRetriever(fileUrl, this, "fileRetriever");
            Thread thread = new Thread(retriever);
            thread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTaskComplete(String taskId) {
        ArtifactsLoader loader = new ArtifactsLoader();
        loader.load(retriever.getInputStream());

        ListView listView = (ListView) view.findViewById(R.id.item_list);
        final ArtifactAdapter adapter = new ArtifactAdapter(getActivity(), loader.getArtifacts());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), ArtifactDetailActivity.class);
                i.putExtra("artifactId", position);
                startActivity(i);
            }
        });
    }

    @Override
    public Activity getCurrentActivity() {
        return getActivity();
    }
}
