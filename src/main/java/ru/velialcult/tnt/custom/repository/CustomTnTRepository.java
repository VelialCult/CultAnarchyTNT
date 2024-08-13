package ru.velialcult.tnt.custom.repository;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import ru.velialcult.library.bukkit.utils.ConfigurationUtil;
import ru.velialcult.library.core.VersionAdapter;
import ru.velialcult.tnt.CultAnarchyTnT;
import ru.velialcult.tnt.custom.CustomTnT;
import ru.velialcult.tnt.custom.CustomTnTMeta;
import ru.velialcult.tnt.custom.CustomTnTOptions;

import java.util.ArrayList;
import java.util.List;

public class CustomTnTRepository {

    private final List<CustomTnT> customTnTList;

    private final FileConfiguration config;

    public CustomTnTRepository(CultAnarchyTnT nightTnT) {
        this.customTnTList = new ArrayList<>();
        this.config = nightTnT.getConfig();
    }

    public CustomTnT getByKey(String key) {
        return customTnTList.stream().filter(customTnT -> customTnT.getKey().equals(key))
                .findAny()
                .orElse(null);
    }

    public void deleteCustomTnT(CustomTnT customTnT) {
        config.set("settings.tnt." + customTnT.getKey(), null);
        ConfigurationUtil.saveFile(config, CultAnarchyTnT.getInstance().getDataFolder().getAbsolutePath(), "config.yml");

        customTnTList.remove(customTnT);
    }

    public void saveCustomTnT(CustomTnT customTnT) {
        config.set("settings.tnt." + customTnT.getKey() + ".meta.displayName", customTnT.getMeta().getDisplayName());
        config.set("settings.tnt." + customTnT.getKey() + ".meta.lore", customTnT.getMeta().getLore());
        config.set("settings.tnt." + customTnT.getKey() + ".meta.glow", customTnT.getMeta().isGlow());
        config.set("settings.tnt." + customTnT.getKey() + ".meta.customModelData", customTnT.getMeta().getCustomModelData());

        config.set("settings.tnt." + customTnT.getKey() + ".breakObsidian", customTnT.getCustomTnTOptions().isBreakObsidian());
        config.set("settings.tnt." + customTnT.getKey() + ".breakRegions", customTnT.getCustomTnTOptions().isBreakRegions());
        config.set("settings.tnt." + customTnT.getKey() + ".breakBlocksInWater", customTnT.getCustomTnTOptions().isBreakBlocksInWater());
        config.set("settings.tnt." + customTnT.getKey() + ".breakSpawners", customTnT.getCustomTnTOptions().isBreakSpawners());
        config.set("settings.tnt." + customTnT.getKey() + ".breakSpawnersInRegion", customTnT.getCustomTnTOptions().isBreakSpawnersInRegion());
        config.set("settings.tnt." + customTnT.getKey() + ".explosionInterval", customTnT.getCustomTnTOptions().getExplosionInterval());
        config.set("settings.tnt." + customTnT.getKey() + ".radiusExplosion", customTnT.getCustomTnTOptions().getRadius_explosion());
        config.set("settings.tnt." + customTnT.getKey() + ".breakUnbreakableRegions", customTnT.getCustomTnTOptions().isBreakUnbreakableRegions());
        config.set("settings.tnt." + customTnT.getKey() + ".break-regions", customTnT.getCustomTnTOptions().getBreakRegionsList());
        config.set("settings.tnt." + customTnT.getKey() + ".autoIgnite", customTnT.getCustomTnTOptions().isAutoIgnite());
        config.set("settings.tnt." + customTnT.getKey() + ".gravity", customTnT.getCustomTnTOptions().isGravity());

        ConfigurationUtil.saveFile(config, CultAnarchyTnT.getInstance().getDataFolder().getAbsolutePath(), "config.yml");

        if (!customTnTList.contains(customTnT)) {
            customTnTList.add(customTnT);
        }
    }

    public void loadCustomTnT() {
        ConfigurationSection section = config.getConfigurationSection("settings.tnt");
        if (section != null) {
            for (String key : section.getKeys(false)) {
                String displayName = VersionAdapter.TextUtil().colorize(config.getString("settings.tnt." + key + ".meta.displayName", "Не указано"));
                List<String> lore = VersionAdapter.TextUtil().colorize(config.getStringList("settings.tnt." + key + ".meta.lore"));
                boolean glow = config.getBoolean("settings.tnt." + key + ".meta.glow", false);
                int customModelData = config.getInt("settings.tnt." + key + ".meta.customModelData", 0);
                CustomTnTMeta customTnTMeta = new CustomTnTMeta(displayName,
                                                                lore,
                                                                glow,
                                                                customModelData);

                boolean breakObsidian = config.getBoolean("settings.tnt." + key + ".breakObsidian", false);
                boolean breakRegions = config.getBoolean("settings.tnt." + key + ".breakRegions", false);
                boolean breakBlocksInWater = config.getBoolean("settings.tnt." + key + ".breakBlocksInWater", false);
                boolean breakSpawners = config.getBoolean("settings.tnt." + key + ".breakSpawners", false);
                boolean breakSpawnersInRegion = config.getBoolean("settings.tnt." + key + ".breakSpawnersInRegion", false);
                int explosionInterval = config.getInt("settings.tnt." + key + ".explosionInterval", 0);
                int explosionRadius = config.getInt("settings.tnt." + key + ".radiusExplosion", 1);
                boolean breakUnbreakableRegions = config.getBoolean("settings.tnt." + key + ".breakUnbreakableRegions", false);
                boolean autoIgnite = config.getBoolean("settings.tnt." + key + ".autoIgnite", false);
                boolean gravity = config.getBoolean("settings.tnt." + key + ".gravity", false);
                List<String> psProtectBlocks = new ArrayList<>(config.getStringList("settings.tnt." + key + ".break-regions"));

                CustomTnTOptions customTnTOptions = new CustomTnTOptions(breakObsidian,
                                                                         breakRegions,
                                                                         breakSpawners,
                                                                         breakBlocksInWater,
                                                                         breakSpawnersInRegion,
                                                                         explosionInterval,
                                                                         psProtectBlocks,
                                                                         explosionRadius,
                                                                         breakUnbreakableRegions,
                                                                         autoIgnite,
                                                                         gravity);

                CustomTnT customTnT = new CustomTnT(key, customTnTMeta, customTnTOptions);

                customTnTList.add(customTnT);
            }
        }
    }

    public List<CustomTnT> getCustomTnTList() {
        return customTnTList;
    }
}

