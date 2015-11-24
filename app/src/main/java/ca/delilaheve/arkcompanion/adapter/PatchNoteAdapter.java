package ca.delilaheve.arkcompanion.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import ca.delilaheve.arkcompanion.R;

public class PatchNoteAdapter extends BaseAdapter {

    private String[] keys;

    private Context context;

    public PatchNoteAdapter(String[] keys, Context context) {
        this.keys = keys;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_left_text, (ViewGroup) convertView, false);

        TextView text = (TextView) view.findViewById(R.id.item_text);
        text.setText(keys[position]);

        return view;
    }

    @Override
    public String getItem(int position) {
        return keys[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return keys.length;
    }
}
