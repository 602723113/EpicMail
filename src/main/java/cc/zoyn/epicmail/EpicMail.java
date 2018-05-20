package cc.zoyn.epicmail;

import cc.zoyn.epicmail.command.CommandHandler;
import cc.zoyn.epicmail.listener.PlayerListener;
import cc.zoyn.epicmail.model.Logger;
import cc.zoyn.epicmail.model.StorageType;
import cc.zoyn.epicmail.util.ConfigurationUtils;
import cc.zoyn.epicmail.util.StreamUtils;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

import static org.bukkit.Bukkit.getPluginCommand;
import static org.bukkit.Bukkit.getPluginManager;

/**
 * Mail Class
 *
 * @author Zoyn
 */
public class EpicMail extends JavaPlugin {

    private static EpicMail instance;

    @Getter
    private String language;
    private File languageFolder;
    private File languageFile;

    @Getter
    private StorageType storageType;
    @Getter
    private File mailFolder;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();

        // language folder detect
        languageFolder = new File(getDataFolder(), "language");
        if (!languageFolder.exists()) {
            languageFolder.mkdirs();
            try {
                StreamUtils.writeToLocal(languageFolder.getAbsolutePath() + File.separator + "en-US.yml", getResource("en-US.yml"));
                StreamUtils.writeToLocal(languageFolder.getAbsolutePath() + File.separator + "zh-CN.yml", getResource("zh-CN.yml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // default language is zh-CN
        language = getConfig().getString("language", "zh-CN");

        // check storage type
        storageType = StorageType.valueOf(getConfig().getString("storageType"));
        if (storageType.equals(StorageType.YAML)) {
            mailFolder = new File(getDataFolder(), "mail");
            if (!mailFolder.exists()) {
                mailFolder.mkdirs();
            }
        }

        // command registe
        getPluginCommand("epicmail").setExecutor(new CommandHandler());

        // listener registe
        getPluginManager().registerEvents(new PlayerListener(), this);

        Logger.info("set language: " + language);
    }

    /**
     * get the language config
     *
     * @return {@link FileConfiguration}
     */
    public FileConfiguration getLanguageConfig() {
        if (languageFile == null) {
            languageFile = new File(languageFolder, language + ".yml");
        }
        return ConfigurationUtils.loadYml(languageFile);
    }

    public static EpicMail getInstance() {
        return instance;
    }

}
