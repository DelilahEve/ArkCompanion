package ca.delilaheve.arkcompanion.util.loader;

import java.util.ArrayList;

import ca.delilaheve.arkcompanion.data.Dye;

public class DyesLoader extends XMLLoader {

    private ArrayList<Dye> dyes;

    private Dye dye;

    public DyesLoader() {
        dyes = new ArrayList<>();
    }

    @Override
    public void startTagRead(String tag) {
        if(tag.equals("dye"))
            dye = new Dye();
    }

    @Override
    public void textRead(String tag, String text) {
        switch (tag) {
            case "colour":
                dye.setColourName(text);
                break;
            case "colour_code":
                dye.setColour(text);
                break;
            case "amarberry":
                dye.setAmarberries(text);
                break;
            case "azulberry":
                dye.setAzulberries(text);
                break;
            case "tintoberry":
                dye.setTintoberries(text);
                break;
            case "narcoberry":
                dye.setNarcoberries(text);
                break;
            case "stimberry":
                dye.setStimberries(text);
                break;
            case "charcoal":
                dye.setCharcoal(text);
                break;
            case "gunpowder":
                dye.setGunpowder(text);
                break;
            case "sparkpowder":
                dye.setSparkpowder(text);
                break;
        }
    }

    @Override
    public void endTagRead(String tag) {
        if(tag.equals("dye"))
            dyes.add(dye);
    }

    public ArrayList<Dye> getDyes() {
        return dyes;
    }
}
