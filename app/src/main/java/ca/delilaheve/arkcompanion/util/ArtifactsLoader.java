package ca.delilaheve.arkcompanion.util;

import java.io.File;
import java.util.ArrayList;

import ca.delilaheve.arkcompanion.data.Artifact;
import ca.delilaheve.arkcompanion.data.XMLLoader;

public class ArtifactsLoader extends XMLLoader {

    private File file;

    private ArrayList<Artifact> artifacts;

    public ArtifactsLoader(File file) {
        artifacts = new ArrayList<>();
        this.file = file;
    }

    @Override
    public void startTagRead(String tag) {

    }

    @Override
    public void textRead(String tag, String text) {

    }

    @Override
    public void endTagRead() {

    }

    public ArrayList<Artifact> getArtifacts() {
        if(artifacts.isEmpty())
            load(file);

        return artifacts;
    }
}
