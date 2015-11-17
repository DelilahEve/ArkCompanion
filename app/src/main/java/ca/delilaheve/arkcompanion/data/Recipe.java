package ca.delilaheve.arkcompanion.data;

import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable {

    private String name;

    private ArrayList<String> materials;
    private ArrayList<String> effects;

    public Recipe(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public void addMaterial(String material) {

    }

    public void addEffect(String effect) {

    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getMaterials() {
        return materials;
    }

    public ArrayList<String> getEffects() {
        return effects;
    }
}
