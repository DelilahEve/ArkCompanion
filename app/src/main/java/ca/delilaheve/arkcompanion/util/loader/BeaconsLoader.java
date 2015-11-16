package ca.delilaheve.arkcompanion.util.loader;

import java.util.ArrayList;

import ca.delilaheve.arkcompanion.data.Beacon;

public class BeaconsLoader extends XMLLoader {

    private ArrayList<Beacon> beacons;

    private Beacon beacon;

    public BeaconsLoader() {
        beacons = new ArrayList<>();
    }

    @Override
    public void startTagRead(String tag) {
        if(tag.equals("beacon"))
            beacon = new Beacon();
    }

    @Override
    public void textRead(String tag, String text) {
        switch (tag) {
            case "colour":
                beacon.setColour(text);
                break;
            case "level":
                beacon.setLevel(text);
                break;
            case "item":
                beacon.addItem(text);
                break;
            case "blueprint":
                beacon.addBlueprint(text);
                break;
        }
    }

    @Override
    public void endTagRead(String tag) {
        if(tag.equals("beacon"))
            beacons.add(beacon);
    }

    public ArrayList<Beacon> getBeacons() {
        return beacons;
    }
}
