package ca.delilaheve.arkcompanion.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
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
import ca.delilaheve.arkcompanion.data.Dye;
import ca.delilaheve.arkcompanion.util.asynctask.AsyncTaskImplementer;
import ca.delilaheve.arkcompanion.util.loader.DyesLoader;
import ca.delilaheve.arkcompanion.util.tools.AsyncFileRetriever;

public class DyesFragment extends Fragment implements AsyncTaskImplementer {

    private View view;

    private AsyncFileRetriever retriever;

    private DyesLoader loader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dyes, container, false);

        loadXml();

        return view;
    }

    private void loadXml(){
        // load XML files
        try {

            URL fileUrl = new URL("http://delilaheve.github.io/assets/ArkCompanion/xml/dyes.xml");
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

                loader = new DyesLoader(null, this, "loader", retriever.getInputStream());
                Thread thread = new Thread(loader);
                thread.start();

                break;
            case "loader":

                ArrayList<Dye> dyes = loader.getDyes();

                LinearLayout dyeTable = (LinearLayout) view.findViewById(R.id.dye_table);
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                for(Dye dye : dyes) {

                    View row = inflater.inflate(R.layout.item_dye_row, null, false);

                    TextView colour, amarberry, azulberry, tintoberry, narcoberry, stimberry, charcoal, gunpowder, sparkpowder;
                    colour = (TextView) row.findViewById(R.id.colour);
                    amarberry = (TextView) row.findViewById(R.id.amarberry_count);
                    azulberry = (TextView) row.findViewById(R.id.azulberry_count);
                    tintoberry = (TextView) row.findViewById(R.id.tintoberry_count);
                    narcoberry = (TextView) row.findViewById(R.id.narcoberry_count);
                    stimberry = (TextView) row.findViewById(R.id.stimberry_count);
                    charcoal = (TextView) row.findViewById(R.id.charcoal_count);
                    gunpowder = (TextView) row.findViewById(R.id.gunpowder_count);
                    sparkpowder = (TextView) row.findViewById(R.id.sparkpowder_count);

                    colour.setText(dye.getColourName());
                    amarberry.setText(dye.getAmarberries());
                    azulberry.setText(dye.getAzulberries());
                    tintoberry.setText(dye.getTintoberries());
                    narcoberry.setText(dye.getNarcoberries());
                    stimberry.setText(dye.getStimberries());
                    charcoal.setText(dye.getCharcoal());
                    gunpowder.setText(dye.getGunpowder());
                    sparkpowder.setText(dye.getSparkpowder());

                    if(dye.getAmarberries().equals("0"))
                        amarberry.setTextColor(Color.argb(68, 68, 68, 68));
                    if(dye.getAzulberries().equals("0"))
                        azulberry.setTextColor(Color.argb(68, 68, 68, 68));
                    if(dye.getTintoberries().equals("0"))
                        tintoberry.setTextColor(Color.argb(68, 68, 68, 68));
                    if(dye.getNarcoberries().equals("0"))
                        narcoberry.setTextColor(Color.argb(68, 68, 68, 68));
                    if(dye.getStimberries().equals("0"))
                        stimberry.setTextColor(Color.argb(68, 68, 68, 68));
                    if(dye.getCharcoal().equals("0"))
                        charcoal.setTextColor(Color.argb(68, 68, 68, 68));
                    if(dye.getGunpowder().equals("0"))
                        gunpowder.setTextColor(Color.argb(68, 68, 68, 68));
                    if(dye.getSparkpowder().equals("0"))
                        sparkpowder.setTextColor(Color.argb(68, 68, 68, 68));

                    dyeTable.addView(row);

                }

                break;
        }
    }

    @Override
    public Activity getCurrentActivity() {
        return getActivity();
    }
}
