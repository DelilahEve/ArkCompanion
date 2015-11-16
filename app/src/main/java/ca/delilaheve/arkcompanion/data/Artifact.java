package ca.delilaheve.arkcompanion.data;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

public class Artifact implements Serializable {

    private Bitmap image;

    private String imageUrl;
    private String location;

    private String name;

    private String level;

    private ArrayList<String> tips;

    public Artifact() {
        tips = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addTip(String tip) {
        tips.add(tip);
    }

    public ArrayList<String> getTips() {
        return tips;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}
