package ru.velialcult.tnt.custom;

import java.util.ArrayList;
import java.util.List;

public class CustomTnTOptions {

    private boolean breakObsidian;
    private boolean breakRegions;
    private boolean breakBlocksInWater;
    private boolean breakSpawners;
    private boolean breakSpawnersInRegion;
    private int explosionInterval;
    private List<String> breakRegionsList;
    private int radius_explosion;
    private boolean breakUnbreakableRegions;
    private boolean autoIgnite;
    private boolean gravity;

    public CustomTnTOptions(boolean breakObsidian,
                            boolean breakRegions,
                            boolean breakSpawners,
                            boolean breakBlocksInWater,
                            boolean breakSpawnersInRegion,
                            int explosionInterval,
                            List<String> breakRegionsList,
                            int radius_explosion,
                            boolean breakUnbreakableRegions,
                            boolean autoIgnite,
                            boolean gravity) {
        this.breakObsidian = breakObsidian;
        this.breakRegions = breakRegions;
        this.breakSpawners = breakSpawners;
        this.breakBlocksInWater = breakBlocksInWater;
        this.breakSpawnersInRegion = breakSpawnersInRegion;
        this.explosionInterval = explosionInterval;
        this.breakRegionsList = breakRegionsList;
        this.radius_explosion = radius_explosion;
        this.breakUnbreakableRegions = breakUnbreakableRegions;
        this.autoIgnite = autoIgnite;
        this.gravity = gravity;
    }

    public CustomTnTOptions() {
        this(false,
             false,
             false,
             false,
             false,
             0,
             new ArrayList<>(),
             1,
             false,
             false,
             false);
    }

    public boolean isGravity() {
        return gravity;
    }

    public void setGravity(boolean gravity) {
        this.gravity = gravity;
    }

    public boolean isAutoIgnite() {
        return autoIgnite;
    }

    public void setAutoIgnite(boolean autoIgnite) {
        this.autoIgnite = autoIgnite;
    }

    public void setBreakBlocksInWater(boolean breakBlocksInWater) {
        this.breakBlocksInWater = breakBlocksInWater;
    }

    public void setBreakObsidian(boolean breakObsidian) {
        this.breakObsidian = breakObsidian;
    }

    public void setBreakRegions(boolean breakRegions) {
        this.breakRegions = breakRegions;
    }

    public void setBreakRegionsList(List<String> breakRegionsList) {
        this.breakRegionsList = breakRegionsList;
    }

    public void setBreakSpawners(boolean breakSpawners) {
        this.breakSpawners = breakSpawners;
    }

    public void setBreakSpawnersInRegion(boolean breakSpawnersInRegion) {
        this.breakSpawnersInRegion = breakSpawnersInRegion;
    }

    public void setBreakUnbreakableRegions(boolean breakUnbreakableRegions) {
        this.breakUnbreakableRegions = breakUnbreakableRegions;
    }

    public void setExplosionInterval(int explosionInterval) {
        this.explosionInterval = explosionInterval;
    }

    public void setRadius_explosion(int radius_explosion) {
        this.radius_explosion = radius_explosion;
    }

    public boolean isBreakUnbreakableRegions() {
        return breakUnbreakableRegions;
    }

    public int getRadius_explosion() {
        return radius_explosion;
    }

    public boolean isBreakBlocksInWater() {
        return breakBlocksInWater;
    }

    public boolean isBreakObsidian() {
        return breakObsidian;
    }

    public boolean isBreakRegions() {
        return breakRegions;
    }

    public boolean isBreakSpawners() {
        return breakSpawners;
    }

    public boolean isBreakSpawnersInRegion() {
        return breakSpawnersInRegion;
    }

    public int getExplosionInterval() {
        return explosionInterval;
    }

    public List<String> getBreakRegionsList() {
        return breakRegionsList;
    }
}
