package ca.delilaheve.arkcompanion.util.loader;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import ca.delilaheve.arkcompanion.data.Artifact;
import ca.delilaheve.arkcompanion.util.asynctask.AsyncTaskImplementer;

public class ArtifactsLoader extends XMLLoader {


    private ArrayList<Artifact> artifacts;

    private Artifact artifact;

    public ArtifactsLoader(URL url, AsyncTaskImplementer implementer, String taskId, InputStream input) {
        super(url, implementer, taskId, input);
        artifacts = new ArrayList<>();
    }

    @Override
    public void startTagRead(String tag) {
        if(tag.equals("artifact"))
            artifact = new Artifact();
    }

    @Override
    public void textRead(String tag, String text) {
        switch (tag) {
            case "image":
                artifact.setImageUrl(text);
                break;
            case "name":
                artifact.setName(text);
                break;
            case "level":
                artifact.setLevel(text);
                break;
            case "location":
                artifact.setLocation(text);
                break;
            case "tip":
                artifact.addTip(text);
                break;
        }
    }

    @Override
    public void endTagRead(String tag) {
        if(tag.equals("artifact"))
            artifacts.add(artifact);
    }

    public ArrayList<Artifact> getArtifacts() {
        return artifacts;
    }
}
