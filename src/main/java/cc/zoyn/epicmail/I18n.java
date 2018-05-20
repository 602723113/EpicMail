package cc.zoyn.epicmail;

import lombok.NonNull;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.stream.Collectors;

public enum I18n {

    MESSAGE_PREFIX("message-prefix"),
    UNKNOW_COMMAND("unknown-command"),
    NO_PERMISSION("no-permission"),
    MAIL_BOX_NAME("mail-box-name"),
    GUI_PREVIOUS_PAGE("gui-previous-page"),
    GUI_NEXT_PAGE("gui-next-page"),
    GUI_PAGE("gui-page"),
    HELP("help");

    private Object message;

    I18n(String message) {
        FileConfiguration config = EpicMail.getInstance().getLanguageConfig();
        if (config.isString(message)) {
            this.message = translateColorCode(config.getString(message));
        } else if (config.isList(message)) {
            this.message = translateColorCode(config.getStringList(message));
        }

    }

    public String getMessage() {
        return (String) this.message;
    }

    public List<String> getAsStringList() {
        return (List<String>) this.message;
    }

    private static String translateColorCode(@NonNull String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    private static List<String> translateColorCode(@NonNull List<String> messages) {
        return messages.stream().map(I18n::translateColorCode).collect(Collectors.toList());
    }

}
