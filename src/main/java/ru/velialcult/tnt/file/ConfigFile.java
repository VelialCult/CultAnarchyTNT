package ru.velialcult.tnt.file;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import ru.velialcult.library.bukkit.file.FileOperations;
import ru.velialcult.tnt.CultAnarchyTnT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ConfigFile {
    private FileOperations fileOperations;
    private final CultAnarchyTnT plugin;
    private final Map<String, String> strings;
    private FileConfiguration config;

    public ConfigFile(CultAnarchyTnT plugin) {
        this.plugin = plugin;
        strings = new HashMap<>();
        config = plugin.getConfig();
    }

    public void load() {
        strings.clear();
        config = plugin.getConfig();
        ConfigurationSection section = config.getConfigurationSection("settings");
        if (section != null) {
            Objects.requireNonNull(section).getKeys(true).forEach(path -> {
                String fullPath = "settings." + path;
                if (config.isList(fullPath)) {
                    List<String> lines = config.getStringList(fullPath);
                    strings.put(fullPath, String.join("\n", lines));
                } else {
                    String value = config.getString(fullPath);
                    strings.put(fullPath, value);
                }
            });
        }
        this.fileOperations = new FileOperations(strings);
    }

    public FileOperations getFileOperations() {
        return fileOperations;
    }
}
