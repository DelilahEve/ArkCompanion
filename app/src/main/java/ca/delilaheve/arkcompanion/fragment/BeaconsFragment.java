package ca.delilaheve.arkcompanion.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Console;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import ca.delilaheve.arkcompanion.R;
import ca.delilaheve.arkcompanion.data.Beacon;
import ca.delilaheve.arkcompanion.util.asynctask.AsyncTaskImplementer;
import ca.delilaheve.arkcompanion.util.loader.BeaconsLoader;
import ca.delilaheve.arkcompanion.util.tools.AsyncFileRetriever;

public class BeaconsFragment extends Fragment implements AsyncTaskImplementer {

    private View view;

    private AsyncFileRetriever retriever;

    private BeaconsLoader loader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_beacons, container, false);

        loadXml();

        return view;
    }

    private void loadXml(){
        // load XML files
        try {
            URL fileUrl = new URL("http://delilaheve.github.io/assets/ArkCompanion/xml/beacons.xml");
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
                loader = new BeaconsLoader(null, this, "beaconLoad", retriever.getInputStream());
                Thread thread = new Thread(loader);
                thread.start();
                break;
            case "beaconLoad":
                // Get Beacons
                ArrayList<Beacon> beacons = loader.getBeacons();

                // Grab tables
                LinearLayout beaconTable, lootList;
                beaconTable = (LinearLayout) view.findViewById(R.id.beacon_table);
                lootList = (LinearLayout) view.findViewById(R.id.loot_list);

                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                for(Beacon beacon : beacons) {

                    // create rows for this beacon
                    View beaconRow, lootRow;
                    beaconRow = inflater.inflate(R.layout.item_beacon_row, null, false);
                    lootRow = inflater.inflate(R.layout.item_loot_row, null, false);

                    // get controls for beacon table
                    TextView colour, level;
                    colour = (TextView) beaconRow.findViewById(R.id.colour);
                    level = (TextView) beaconRow.findViewById(R.id.level);

                    // get controls for loot list
                    TextView name = (TextView) lootRow.findViewById(R.id.colour);
                    final ImageView icon = (ImageView) lootRow.findViewById(R.id.header_icon);
                    final LinearLayout lootContainer, lootContainerHeader, lootRowHeader;
                    lootContainer = (LinearLayout) lootRow.findViewById(R.id.loot_table);
                    lootContainerHeader = (LinearLayout) lootRow.findViewById(R.id.loot_table_header);
                    lootRowHeader = (LinearLayout) lootRow.findViewById(R.id.loot_section_header);

                    // set beacon row values
                    colour.setText(beacon.getColour());
                    level.setText(beacon.getLevel());

                    // set loot list values
                    ArrayList<String> lootListItems = beacon.getItems();
                    ArrayList<String> lootListBlueprints = beacon.getBlueprints();
                    int i = 0;
                    while(!(i > lootListItems.size() && i > lootListBlueprints.size())) {
                        // inflate row
                        View lootItemRow = inflater.inflate(R.layout.item_beacon_row, null, false);
                        TextView item, blueprint;
                        item = (TextView) lootItemRow.findViewById(R.id.colour);
                        blueprint = (TextView) lootItemRow.findViewById(R.id.level);

                        if(i < lootListItems.size()-1)
                            item.setText(lootListItems.get(i));
                        if(i < lootListBlueprints.size()-1)
                            blueprint.setText(lootListBlueprints.get(i));

                        // add items to row
                        lootContainer.addView(lootItemRow);

                        i++;
                    }

                    // set beacon loot row header
                    name.setText(beacon.getColour());

                    // set header click listener
                    lootRowHeader.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(lootContainerHeader.getVisibility() == View.GONE) {
                                lootContainerHeader.setVisibility(View.VISIBLE);
                                lootContainer.setVisibility(View.VISIBLE);
                                icon.setImageResource(R.drawable.action_collapse);
                            }
                            else {
                                lootContainerHeader.setVisibility(View.GONE);
                                lootContainer.setVisibility(View.GONE);
                                icon.setImageResource(R.drawable.action_expand);
                            }
                        }
                    });

                    // hide loot by default
                    lootContainerHeader.setVisibility(View.GONE);
                    lootContainer.setVisibility(View.GONE);

                    // add rows to tables
                    beaconTable.addView(beaconRow);
                    lootList.addView(lootRow);
                }

                beaconTable.invalidate();
                lootList.invalidate();

                break;
        }
    }

    @Override
    public Activity getCurrentActivity() {
        return getActivity();
    }
}
