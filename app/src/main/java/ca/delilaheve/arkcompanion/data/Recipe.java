package ca.delilaheve.arkcompanion.data;

import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable {

    private String name;

    private ArrayList<String> materials;
    private ArrayList<String> effects;

    public Recipe(){
        materials = new ArrayList<>();
        effects = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addMaterial(String material) {
        materials.add(material);
    }

    public void addEffect(String effect) {
        effects.add(effect);
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
