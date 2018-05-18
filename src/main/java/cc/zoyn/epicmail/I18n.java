package cc.zoyn.epicmail;

import lombok.NonNull;
import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public enum I18n {

    MESSAGE_PREFIX(translateColorCode(EpicMail.getInstance().getLanguageConfig().getString("message-prefix"))),
    UNKNOW_COMMAND(translateColorCode(EpicMail.getInstance().getLanguageConfig().getString("unknown-command"))),
    NO_PERMISSION(translateColorCode(EpicMail.getInstance().getLanguageConfig().getString("no-permission"))),
    HELP(translateColorCode(EpicMail.getInstance().getLanguageConfig().getStringList("help")));

    private Object message;

    I18n(Object message) {
        this.message = message;
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
