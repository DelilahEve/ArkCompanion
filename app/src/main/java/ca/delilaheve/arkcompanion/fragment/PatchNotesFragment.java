package ca.delilaheve.arkcompanion.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import ca.delilaheve.arkcompanion.R;
import ca.delilaheve.arkcompanion.adapter.PatchNoteAdapter;
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
            URL fileUrl = new URL("http://steamcommunity.com/app/346110/discussions/0/594820656447032287/");
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

                final HashMap<String, ArrayList<String>> map = loader.getNotesMap();

                final String[] titles = map.keySet().toArray(new String[map.keySet().size()]);
                final PatchNoteAdapter adapter = new PatchNoteAdapter(titles, getActivity());

                Spinner notesSelector = (Spinner) view.findViewById(R.id.patch_version_picker);
                notesSelector.setAdapter(adapter);

                notesSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        // change out the notes shown
                        TextView patchNotes = (TextView) PatchNotesFragment.this.view.findViewById(R.id.patch_notes);

                        String notes = "";
                        ArrayList<String> noteList = map.get(adapter.getItem(position));
                        for (String note : noteList) {
                            System.out.println(note);
                            notes += note + "\n\n";
                        }

                        patchNotes.setText(notes);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // do nothing
                    }
                });



                break;
        }
    }

    @Override
    public Activity getCurrentActivity() {
        return getActivity();
    }
}
