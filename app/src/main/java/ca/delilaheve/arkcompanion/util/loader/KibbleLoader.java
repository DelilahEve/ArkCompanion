package ca.delilaheve.arkcompanion.util.loader;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import ca.delilaheve.arkcompanion.data.Kibble;
import ca.delilaheve.arkcompanion.util.asynctask.AsyncTaskImplementer;

public class KibbleLoader extends XMLLoader {

    private ArrayList<Kibble> kibbles;

    private Kibble kibble;

    public KibbleLoader(URL url, AsyncTaskImplementer implementer, String taskId, InputStream input) {
        super(url, implementer, taskId, input);
        kibbles = new ArrayList<>();
    }

    @Override
    public void startTagRead(String tag) {
        if(tag.equals("recipe"))
            kibble = new Kibble();
    }

    @Override
    public void textRead(String tag, String text) {
        switch (tag) {
            case "for_dino":
                kibble.setFavouriteFor(text);
                break;
            case "egg":
                kibble.setEgg(text);
                break;
            case "plant":
                kibble.setPlant(text);
                break;
            case "meat":
                kibble.setMeat(text);
                break;
        }
    }

    @Override
    public void endTagRead(String tag) {
        if(tag.equals("recipe"))
            kibbles.add(kibble);
    }

    public ArrayList<Kibble> getKibbles() {
        return kibbles;
    }
}
