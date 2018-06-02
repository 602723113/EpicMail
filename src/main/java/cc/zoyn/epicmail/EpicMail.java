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
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.DB;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

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
    private File sqliteFile;

    @Getter
    private DB db;

    private String databaseHost;
    private String databasePort;
    private String databaseUserName;
    private String databasePassword;
    private String database;

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
        Logger.info("set language: " + language);

        // check storage type
        storageType = StorageType.valueOf(getConfig().getString("storageType"));

        databaseHost = getConfig().getString("databaseOption.ip");
        databasePort = getConfig().getString("databaseOption.port");
        databaseUserName = getConfig().getString("databaseOption.user");
        databasePassword = getConfig().getString("databaseOption.password");
        database = getConfig().getString("databaseOption.database");

        boolean tableIsCreated = false;
        if (storageType.equals(StorageType.SQLITE)) {
            sqliteFile = new File(getDataFolder(), "mail.db");
            if (!sqliteFile.exists()) {
                try {
                    tableIsCreated = sqliteFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            db = Base.open("org.sqlite.JDBC", "jdbc:sqlite:" + sqliteFile.getAbsolutePath(), databaseUserName, databasePassword);
            // check the table is created
            if (tableIsCreated) {
                try {
                    db.startBatch("CREATE TABLE IF NOT EXISTS epicmail_mail( " +
                            "id INT PRIMARY KEY       NOT NULL, " +
                            "title          TEXT      NOT NULL, " +
                            "sender         TEXT      NOT NULL, " +
                            "reciver        CHAR(50)  NOT NULL, " +
                            "message        TEXT      NOT NULL, " +
                            "items          TEXT      NOT NULL, " +
                            "send_time      REAL      NOT NULL);")
                            .execute();
                    db.startBatch("CREATE TABLE IF NOT EXISTS epicmail_mailbox( " +
                            "id INT PRIMARY KEY       NOT NULL, " +
                            "uuid          TEXT      NOT NULL, " +
                            "owner         TEXT      NOT NULL);")
                            .execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        } else if (storageType.equals(StorageType.MYSQL)) {
            // jdbc:mysql//localhost:3306/mc
            db = Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://" + databaseHost + ":" + databasePort + "/" + database, databaseUserName, databasePassword);
        }

        Logger.info("setting storage type: " + storageType.toString());

        // command registe
        getPluginCommand("epicmail").setExecutor(new CommandHandler());
        // listener registe
        getPluginManager().registerEvents(new PlayerListener(), this);
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
