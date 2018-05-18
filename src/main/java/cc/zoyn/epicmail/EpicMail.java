package cc.zoyn.epicmail;

import cc.zoyn.epicmail.command.CommandHandler;
import cc.zoyn.epicmail.model.Logger;
import cc.zoyn.epicmail.util.ConfigurationUtils;
import cc.zoyn.epicmail.util.StreamUtils;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

import static org.bukkit.Bukkit.getPluginCommand;

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

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        // default is zh-CN
        languageFolder = new File(getDataFolder(), "language");
        if (!languageFolder.exists()) {
            languageFolder.mkdirs();
            try {
                StreamUtils.writeToLocal(languageFolder.getAbsolutePath() + File.separator + "zh-CN.yml", getResource("zh-CN.yml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        language = getConfig().getString("language", "zh-CN");

        // command registe
        getPluginCommand("epicmail").setExecutor(new CommandHandler());

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
