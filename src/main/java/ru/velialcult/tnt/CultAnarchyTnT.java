package ru.velialcult.tnt;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ru.velialcult.library.bukkit.file.FileRepository;
import ru.velialcult.library.bukkit.utils.ConfigurationUtil;
import ru.velialcult.tnt.command.CultAnarchyTnTCommand;
import ru.velialcult.tnt.custom.manager.CustomTnTManager;
import ru.velialcult.tnt.file.ConfigFile;
import ru.velialcult.tnt.file.MessagesFile;
import ru.velialcult.tnt.listener.CustomTnTListener;
import ru.velialcult.tnt.listener.handler.spawner.SpawnerListener;

public class CultAnarchyTnT extends JavaPlugin {

    private static CultAnarchyTnT instance;
    private CustomTnTManager customTnTManager;

    private ConfigFile configFile;
    private MessagesFile messagesFile;

    @Override
    public void onEnable() {
        instance = this;

        try {

            this.saveDefaultConfig();
            loadConfigs();

            customTnTManager = new CustomTnTManager(this);
            customTnTManager.initialize();

            Bukkit.getPluginManager().registerEvents(new CustomTnTListener(customTnTManager), this);
            Bukkit.getPluginManager().registerEvents(new SpawnerListener(this), this);
            CultAnarchyTnTCommand command = new CultAnarchyTnTCommand(messagesFile, customTnTManager);
            Bukkit.getPluginCommand("cultanarchytnt").setExecutor(command);
            Bukkit.getPluginCommand("cultanarchytnt").setTabCompleter(command);

        } catch (Exception e) {
            getLogger().severe("Произошла ошибка при инициализации плагина: " + e.getMessage());
        }
    }

    private void loadConfigs() {
        ConfigurationUtil.loadConfigurations(this, "messages.yml");
        FileRepository.load(this);
        configFile = new ConfigFile(this);
        configFile.load();
        messagesFile = new MessagesFile(this);
        messagesFile.load();
    }

    public static CultAnarchyTnT getInstance() {
        return instance;
    }

    public CustomTnTManager getCustomTnTManager() {
        return customTnTManager;
    }

    public ConfigFile getConfigFile() {
        return configFile;
    }

    public MessagesFile getMessagesFile() {
        return messagesFile;
    }
}
