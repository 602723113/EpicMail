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
    GUI_MAIL_MESSAGE("gui-mail-message"),
    GUI_MAIL_ITEMS("gui-mail-items"),
    GUI_MAIL_SENDER("gui-mail-sender"),
    GUI_MAIL_LEFT_CLICK("gui-mail-left-click"),
    GUI_MAIL_SHIFT_CLICK("gui-mail-shift-click"),
    HELP("help");

    private Object message;

    I18n(String key) {
        FileConfiguration config = EpicMail.getInstance().getLanguageConfig();
        if (config.isString(key)) {
            this.message = translateColorCode(config.getString(key));
        } else if (config.isList(key)) {
            this.message = translateColorCode(config.getStringList(key));
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

    @Override
    public String toString() {
        return super.toString();
    }
}
