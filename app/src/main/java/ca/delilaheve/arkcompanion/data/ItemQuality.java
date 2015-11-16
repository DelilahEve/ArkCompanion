package ca.delilaheve.arkcompanion.data;

import java.io.Serializable;

public class ItemQuality implements Serializable {

    private String name;

    private String durability;

    private String damage;

    public String getName() {
        return name;
    }

    public String getDurability() {
        return durability;
    }

    public String getDamage() {
        return damage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDurability(String durability) {
        this.durability = durability;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }
}
