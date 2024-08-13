package ru.velialcult.tnt.custom;

import ru.velialcult.library.bukkit.inventory.LoreHolder;

import java.util.ArrayList;
import java.util.List;

public class CustomTnTMeta implements LoreHolder {

    private String displayName;
    private List<String> lore;
    private boolean glow;
    private int customModelData;

    public CustomTnTMeta(String displayName,
                         List<String> lore,
                         boolean glow,
                         int customModelData) {
        this.displayName = displayName;
        this.lore = lore;
        this.glow = glow;
        this.customModelData = customModelData;
    }

    public CustomTnTMeta() {
        this("Не указано", new ArrayList<>(), false, 1);
    }

    public boolean isGlow() {
        return glow;
    }

    public int getCustomModelData() {
        return customModelData;
    }

    public void setCustomModelData(int customModelData) {
        this.customModelData = customModelData;
    }

    public void setGlow(boolean glow) {
        this.glow = glow;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    public List<String> getLore() {
        return lore;
    }

    public String getDisplayName() {
        return displayName;
    }
}
