package ca.delilaheve.arkcompanion.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import ca.delilaheve.arkcompanion.R;
import ca.delilaheve.arkcompanion.util.asynctask.AsyncTaskImplementer;
import ca.delilaheve.arkcompanion.util.loader.PatchNotesLoader;
import ca.delilaheve.arkcompanion.util.tools.AsyncFileRetriever;

public class PatchNotesFragment extends Fragment implements AsyncTaskImplementer {

    private View view;

    private AsyncFileRetriever retriever;

    private PatchNotesLoader loader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_patch_notes, container, false);

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
        switch (taskId) {
            case "fileRetriever":

                loader = new PatchNotesLoader(null, this, "loader", retriever.getInputStream());
                Thread thread = new Thread(loader);
                thread.start();

                break;
            case "loader":

                HashMap<String, ArrayList<String>> map = loader.getNotesMap();



                break;
        }
    }

    @Override
    public Activity getCurrentActivity() {
        return getActivity();
    }
}
