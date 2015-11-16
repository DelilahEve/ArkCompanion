package ca.delilaheve.arkcompanion.data;

import java.io.Serializable;
import java.util.ArrayList;

public class Beacon implements Serializable {

    private String colour;

    private String level;

    private ArrayList<String> items;
    private ArrayList<String> blueprints;

    public Beacon() {
        items = new ArrayList<>();
        blueprints = new ArrayList<>();
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void addItem(String item) {
        items.add(item);
    }

    public void addBlueprint(String blueprint) {
        blueprints.add(blueprint);
    }

    public String getColour() {
        return colour;
    }

    public String getLevel() {
        return level;
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public ArrayList<String> getBlueprints() {
        return blueprints;
    }
}
