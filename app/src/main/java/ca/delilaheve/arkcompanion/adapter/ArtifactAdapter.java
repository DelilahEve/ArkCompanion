package ca.delilaheve.arkcompanion.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import ca.delilaheve.arkcompanion.R;
import ca.delilaheve.arkcompanion.data.Artifact;
import ca.delilaheve.arkcompanion.util.asynctask.AsyncTaskImplementer;
import ca.delilaheve.arkcompanion.util.tools.AsyncImageRetriever;

public class ArtifactAdapter extends BaseAdapter implements AsyncTaskImplementer {

    private Context context;

    private ArrayList<Artifact> artifacts;

    private AsyncImageRetriever[] retrievers;

    int loaded = 0;

    public ArtifactAdapter(Context context, ArrayList<Artifact> artifacts) {
        this.context = context;
        this.artifacts = artifacts;
        retrievers = new AsyncImageRetriever[artifacts.size()];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_main_list, (ViewGroup)convertView, false);

        ImageView image = (ImageView) itemView.findViewById(R.id.item_image);
        TextView text = (TextView) itemView.findViewById(R.id.item_text);

        if(artifacts.get(position).getImage() == null){
            try {
                loadImage(new URL(artifacts.get(position).getImageUrl()), position);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            image.setImageBitmap(artifacts.get(position).getImage());
        }

        text.setText(artifacts.get(position).getName());

        return itemView;
    }

    private void loadImage(URL url, int position) {
        retrievers[position] = new AsyncImageRetriever(url, this, String.valueOf(position));
        Thread thread = new Thread(retrievers[position]);
        thread.start();
    }

    @Override
    public Artifact getItem(int position) {
        return artifacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return artifacts.size();
    }

    @Override
    public void onTaskComplete(String taskId) {
        if(loaded <= artifacts.size()){
            loaded++;
            int position = Integer.parseInt(taskId);
            Artifact artifact = artifacts.get(position);
            artifact.setImage(retrievers[position].getImage());
        }

        if(loaded >= artifacts.size()){
            notifyDataSetChanged();
        }
    }

    @Override
    public Activity getCurrentActivity() {
        return (Activity)context;
    }
}
