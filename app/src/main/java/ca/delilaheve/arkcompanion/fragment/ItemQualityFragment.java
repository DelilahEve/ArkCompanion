package ca.delilaheve.arkcompanion.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
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
import ca.delilaheve.arkcompanion.data.ItemQuality;
import ca.delilaheve.arkcompanion.util.asynctask.AsyncTaskImplementer;
import ca.delilaheve.arkcompanion.util.loader.ItemQualityLoader;
import ca.delilaheve.arkcompanion.util.tools.AsyncFileRetriever;

public class ItemQualityFragment extends Fragment implements AsyncTaskImplementer {

    private View view;

    private AsyncFileRetriever retriever;

    private ItemQualityLoader loader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_item_quality, container, false);

        loadXml();

        return view;
    }

    private void loadXml(){
        // load XML files
        try {
            URL fileUrl = new URL("http://delilaheve.github.io/assets/ArkCompanion/xml/item_quality.xml");
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

                loader = new ItemQualityLoader(null, this, "loader", retriever.getInputStream());
                Thread thread = new Thread(loader);
                thread.start();

                break;
            case "loader":

                ArrayList<ItemQuality> qualities = loader.getQualities();
                ArrayList<String> affectedItems = loader.getItemsAffected();

                LinearLayout qualityTable = (LinearLayout) view.findViewById(R.id.quality_table_container);
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                for(ItemQuality quality : qualities) {

                    View row = inflater.inflate(R.layout.item_quality_row, null, false);

                    TextView name, durability, damage;
                    name = (TextView) row.findViewById(R.id.level);
                    durability = (TextView) row.findViewById(R.id.durability);
                    damage = (TextView) row.findViewById(R.id.damage);

                    name.setText(quality.getName());
                    durability.setText(quality.getDurability());
                    damage.setText(quality.getDamage());

                    qualityTable.addView(row);

                }

                LinearLayout itemsAffected = (LinearLayout) view.findViewById(R.id.affected_items);

                for(String item : affectedItems) {

                    View row = inflater.inflate(R.layout.item_left_text, null, false);

                    TextView itemText = (TextView) row.findViewById(R.id.item_text);

                    itemText.setText(item);

                    itemsAffected.addView(row);

                }

                break;
        }
    }

    @Override
    public Activity getCurrentActivity() {
        return getActivity();
    }
}
