package ca.delilaheve.arkcompanion.data;

import java.io.Serializable;

public class Dye implements Serializable {

    private String colourName;
    private String colour;

    private String amarberries = "0";
    private String azulberries = "0";
    private String tintoberries = "0";
    private String narcoberries = "0";
    private String stimberries = "0";
    private String charcoal = "0";
    private String gunpowder = "0";
    private String sparkpowder = "0";


    public void setColourName(String colourName) {
        this.colourName = colourName;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setAmarberries(String amarberries) {
        this.amarberries = amarberries;
    }

    public void setAzulberries(String azulberries) {
        this.azulberries = azulberries;
    }

    public void setTintoberries(String tintoberries) {
        this.tintoberries = tintoberries;
    }

    public void setNarcoberries(String narcoberries) {
        this.narcoberries = narcoberries;
    }

    public void setStimberries(String stimberries) {
        this.stimberries = stimberries;
    }

    public void setCharcoal(String charcoal) {
        this.charcoal = charcoal;
    }

    public void setGunpowder(String gunpowder) {
        this.gunpowder = gunpowder;
    }

    public void setSparkpowder(String sparkpowder) {
        this.sparkpowder = sparkpowder;
    }

    public String getColourName() {
        return colourName;
    }

    public String getColour() {
        return colour;
    }

    public String getAmarberries() {
        return amarberries;
    }

    public String getAzulberries() {
        return azulberries;
    }

    public String getTintoberries() {
        return tintoberries;
    }

    public String getNarcoberries() {
        return narcoberries;
    }

    public String getStimberries() {
        return stimberries;
    }

    public String getCharcoal() {
        return charcoal;
    }

    public String getGunpowder() {
        return gunpowder;
    }

    public String getSparkpowder() {
        return sparkpowder;
    }
}
