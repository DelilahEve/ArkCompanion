package ca.delilaheve.arkcompanion.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import ca.delilaheve.arkcompanion.R;
import ca.delilaheve.arkcompanion.data.Kibble;
import ca.delilaheve.arkcompanion.util.asynctask.AsyncTaskImplementer;
import ca.delilaheve.arkcompanion.util.loader.KibbleLoader;
import ca.delilaheve.arkcompanion.util.tools.AsyncFileRetriever;

public class KibbleFragment extends Fragment implements AsyncTaskImplementer {

    private View view;

    private AsyncFileRetriever retriever;

    private KibbleLoader loader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_kibble, container, false);

        loadXml();

        return view;
    }

    private void loadXml(){
        // load XML files
        try {
            URL fileUrl = new URL("http://delilaheve.github.io/assets/ArkCompanion/xml/kibble.xml");
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

                loader = new KibbleLoader(null, this, "loader", retriever.getInputStream());
                Thread thread = new Thread(loader);
                thread.start();

                break;
            case "loader":

                ArrayList<Kibble> kibbles = loader.getKibbles();

                LinearLayout kibbleTable = (LinearLayout) view.findViewById(R.id.kibble_table);
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                for(Kibble kibble : kibbles) {

                    View row = inflater.inflate(R.layout.item_kibble_row, null, false);

                    TextView favourite, egg, plant, meat;
                    favourite = (TextView) row.findViewById(R.id.favourite);
                    egg = (TextView) row.findViewById(R.id.egg);
                    plant = (TextView) row.findViewById(R.id.plant);
                    meat = (TextView) row.findViewById(R.id.meat);

                    favourite.setText(kibble.getFavouriteFor());
                    egg.setText(kibble.getEgg());
                    plant.setText(kibble.getPlant());
                    meat.setText(kibble.getMeat());

                    kibbleTable.addView(row);

                }

                kibbleTable.invalidate();
                kibbleTable.requestLayout();
                break;
        }
    }

    @Override
    public Activity getCurrentActivity() {
        return getActivity();
    }
}
