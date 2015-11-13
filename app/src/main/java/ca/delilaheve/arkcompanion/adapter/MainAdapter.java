package ca.delilaheve.arkcompanion.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ca.delilaheve.arkcompanion.R;

public class MainAdapter extends BaseAdapter {

    public enum MainMenuItems {
        ARTIFACTS(R.string.artifacts, R.drawable.artifact),
        BEACONS(R.string.beacons, R.drawable.beacon),
        ITEM_QUALITY(R.string.item_quality, R.drawable.tool),
        DOSSIER(R.string.dossier, R.drawable.dossier),
        KIBBLE(R.string.kibble, R.drawable.kibble),
        DYES(R.string.dyes, R.drawable.dye),
        RECIPES(R.string.recipes, R.drawable.recipe),
        PATCH_NOTES(R.string.patch_notes, R.drawable.note),
        SERVER_MANAGER(R.string.server_manager, R.drawable.server);

        public int stringId;
        public int imageId;

        MainMenuItems(int stringId, int imageId){
            this.stringId = stringId;
            this.imageId = imageId;
        }
    }

    private ArrayList<MainMenuItems> menuItems;

    private Context context;

    private boolean isGridView;

    public MainAdapter(Context context, boolean isGridView) {
        this.context = context;
        this.isGridView = isGridView;

        menuItems = new ArrayList<>();
        menuItems.add(MainMenuItems.ARTIFACTS);
        menuItems.add(MainMenuItems.BEACONS);
        menuItems.add(MainMenuItems.ITEM_QUALITY);
        menuItems.add(MainMenuItems.DOSSIER);
        menuItems.add(MainMenuItems.KIBBLE);
        menuItems.add(MainMenuItems.DYES);
        menuItems.add(MainMenuItems.RECIPES);
        menuItems.add(MainMenuItems.PATCH_NOTES);
        menuItems.add(MainMenuItems.SERVER_MANAGER);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(isGridView ? R.layout.item_main_grid : R.layout.item_main_list, (ViewGroup)convertView, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.item_image);
        TextView textView = (TextView) view.findViewById(R.id.item_text);

        imageView.setImageResource(menuItems.get(position).imageId);
        textView.setText(menuItems.get(position).stringId);

        return view;
    }

    @Override
    public MainMenuItems getItem(int position) {
        return menuItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return menuItems.size();
    }
}
