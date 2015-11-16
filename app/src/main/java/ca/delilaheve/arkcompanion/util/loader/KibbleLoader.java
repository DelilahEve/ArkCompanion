package ca.delilaheve.arkcompanion.util.loader;

import java.util.ArrayList;

import ca.delilaheve.arkcompanion.data.Kibble;

public class KibbleLoader extends XMLLoader {

    private ArrayList<Kibble> kibbles;

    private Kibble kibble;

    public KibbleLoader() {
        kibbles = new ArrayList<>();
    }

    @Override
    public void startTagRead(String tag) {
        if(tag.equals("kibble"))
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
        if(tag.equals("kibble"))
            kibbles.add(kibble);
    }

    public ArrayList<Kibble> getKibbles() {
        return kibbles;
    }
}
