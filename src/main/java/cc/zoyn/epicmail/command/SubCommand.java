package cc.zoyn.epicmail.command;

import org.bukkit.command.CommandSender;

/**
 * Represent a sub command
 *
 * @author Zoyn
 * @since 2017-3-11
 */
@FunctionalInterface
public interface SubCommand {

    void execute(CommandSender sender, String[] args);

}
