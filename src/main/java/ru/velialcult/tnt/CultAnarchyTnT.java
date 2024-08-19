package ru.velialcult.tnt;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import ru.velialcult.anarchyregions.CultAnarchyRegions;
import ru.velialcult.anarchyregions.api.AnarchyRegionsApi;
import ru.velialcult.library.bukkit.file.FileRepository;
import ru.velialcult.library.bukkit.utils.ConfigurationUtil;
import ru.velialcult.library.core.VersionAdapter;
import ru.velialcult.library.update.UpdateChecker;
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

    private AnarchyRegionsApi anarchyRegionsApi;

    @Override
    public void onEnable() {
        instance = this;

        try {

            Plugin plugin = Bukkit.getPluginManager().getPlugin("CultAnarchyRegions");
            if (plugin == null) {
                getLogger().warning(VersionAdapter.TextUtil().colorize("  &#DC143CОшибка! &fПлагин &#DC143CCultAnarchyRegions &fне найден, отключаю плагин!"));
                Bukkit.getPluginManager().disablePlugin(this);
            } else {
                getLogger().warning(VersionAdapter.TextUtil().colorize("  &#00FF7F&lВсё готово! &fПлагин &#DC143CCultAnarchyRegions &fнайден, продолжаю загрузку!"));
                anarchyRegionsApi = new AnarchyRegionsApi((CultAnarchyRegions) plugin);
            }

            this.saveDefaultConfig();
            loadConfigs();

            UpdateChecker updateChecker = new UpdateChecker(this, "CultAnarchyTNT");
            updateChecker.check();

            customTnTManager = new CustomTnTManager(this);
            customTnTManager.initialize();

            Bukkit.getPluginManager().registerEvents(new CustomTnTListener(this, customTnTManager), this);
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

    public AnarchyRegionsApi getAnarchyRegionsApi() {
        return anarchyRegionsApi;
    }
}
