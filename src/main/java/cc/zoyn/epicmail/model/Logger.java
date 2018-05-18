package cc.zoyn.epicmail.model;

import cc.zoyn.epicmail.I18n;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

import java.util.Arrays;

/**
 * this logger is for plugin itself
 *
 * @author Zoyn
 * @since 2018-05-18
 */
public final class Logger {

    private static final ConsoleCommandSender logger = Bukkit.getConsoleSender();

    public static void info(String... msg) {
        logger.sendMessage(msg);
    }

    public static void warn(String msg) {
        logger.sendMessage(I18n.MESSAGE_PREFIX + " §f| §c§lWARN§7] §r" + msg);
    }

    public static void warn(String... msg) {
        Arrays.stream(msg).forEach(Logger::warn);
    }

    public static void error(String msg) {
        logger.sendMessage(I18n.MESSAGE_PREFIX + " §f| §4§lERROR§7] §r" + msg);
    }

    public static void error(String... msg) {
        Arrays.stream(msg).forEach(Logger::error);
    }


}
