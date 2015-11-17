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

import java.io.IOException;
import java.net.URL;

import ca.delilaheve.arkcompanion.R;
import ca.delilaheve.arkcompanion.data.Artifact;
import ca.delilaheve.arkcompanion.util.asynctask.AsyncTaskImplementer;
import ca.delilaheve.arkcompanion.util.loader.ArtifactsLoader;
import ca.delilaheve.arkcompanion.util.tools.AsyncFileRetriever;
import ca.delilaheve.arkcompanion.util.tools.AsyncImageRetriever;

public class ArtifactFragment extends Fragment implements AsyncTaskImplementer {

    private View view;

    private AsyncFileRetriever retriever;
    private AsyncImageRetriever imageRetriever;

    private Artifact artifact;

    private int artifactId;

    private ArtifactsLoader loader;

    public ArtifactFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_artifact_detail, container, false);

        artifactId = getArguments().getInt("artifactId");
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
        if(taskId.equals("fileRetriever")){
            loader = new ArtifactsLoader(null, this, "loader", retriever.getInputStream());
            Thread thread = new Thread(loader);
            thread.start();
        }
        else if(taskId.equals("imageRetriever")) {
            ImageView image = (ImageView) view.findViewById(R.id.artifact_image);
            image.setImageBitmap(imageRetriever.getImage());
        }
        else if(taskId.equals("loader")) {
            artifact = loader.getArtifacts().get(artifactId);

            final TextView name, level, location;
            final LinearLayout tipsContainer, tipsToggle;

            name = (TextView) view.findViewById(R.id.artifact_name);
            level = (TextView) view.findViewById(R.id.required_level);
            location = (TextView) view.findViewById(R.id.artifact_location);

            tipsToggle = (LinearLayout) view.findViewById(R.id.tips_toggle);
            final ImageView tipsToggleIcon = (ImageView) view.findViewById(R.id.tips_toggle_icon);
            tipsContainer = (LinearLayout) view.findViewById(R.id.tips_container);

            name.setText(artifact.getName());
            level.setText(artifact.getLevel());
            location.setText(artifact.getLocation());

            tipsToggle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (tipsContainer.getVisibility() == View.GONE){
                        tipsContainer.setVisibility(View.VISIBLE);
                        tipsToggleIcon.setImageResource(R.drawable.action_collapse);
                    }
                    else{
                        tipsContainer.setVisibility(View.GONE);
                        tipsToggleIcon.setImageResource(R.drawable.action_expand);
                    }
                }
            });

            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            for(String tip : artifact.getTips()) {
                if(tip == null)
                    continue;

                View item = inflater.inflate(R.layout.item_left_text, tipsContainer, false);
                TextView textView = (TextView) item.findViewById(R.id.item_text);
                textView.setText(tip);
                tipsContainer.addView(item);
            }

            try {
                URL imageUrl = new URL(artifact.getImageUrl());
                imageRetriever = new AsyncImageRetriever(imageUrl, this, "imageRetriever");
                Thread thread = new Thread(imageRetriever);
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Activity getCurrentActivity() {
        return getActivity();
    }
}
