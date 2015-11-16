package ca.delilaheve.arkcompanion.data;

import java.io.Serializable;

public class Kibble implements Serializable {

    private String favouriteFor;

    private String egg;

    private String plant;

    private String meat;

    public void setFavouriteFor(String favouriteFor) {
        this.favouriteFor = favouriteFor;
    }

    public void setEgg(String egg) {
        this.egg = egg;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public void setMeat(String meat) {
        this.meat = meat;
    }

    public String getFavouriteFor() {
        return favouriteFor;
    }

    public String getEgg() {
        return egg;
    }

    public String getPlant() {
        return plant;
    }

    public String getMeat() {
        return meat;
    }
}
