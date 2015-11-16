package ca.delilaheve.arkcompanion.util.loader;

import java.util.ArrayList;

import ca.delilaheve.arkcompanion.data.ItemQuality;

public class ItemQualityLoader extends XMLLoader {

    private ArrayList<ItemQuality> qualities;

    private ArrayList<String> itemsAffected;

    private ItemQuality quality;

    public ItemQualityLoader() {
        qualities = new ArrayList<>();
        itemsAffected = new ArrayList<>();
    }

    @Override
    public void startTagRead(String tag) {
        if(tag.equals("level"))
            quality = new ItemQuality();
    }

    @Override
    public void textRead(String tag, String text) {
        switch (tag) {
            case "name":
                quality.setName(text);
                break;
            case "durability":
                quality.setDurability(text);
                break;
            case "damage":
                quality.setDamage(text);
                break;
            case "item":
                itemsAffected.add(text);
                break;
        }
    }

    @Override
    public void endTagRead(String tag) {
        if(tag.equals("level"))
            qualities.add(quality);
    }

    public ArrayList<ItemQuality> getQualities() {
        return qualities;
    }

    public ArrayList<String> getItemsAffected() {
        return itemsAffected;
    }
}
