package cc.zoyn.epicmail.command.subcommand;

import cc.zoyn.epicmail.I18n;
import cc.zoyn.epicmail.command.SubCommand;
import org.bukkit.command.CommandSender;

/**
 * @author Zoyn
 * @since 2018-5-18
 */
public class HelpCommand implements SubCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("epicmail.help")) {
            I18n.HELP.getAsStringList().forEach(sender::sendMessage);
        } else {
            sender.sendMessage(I18n.NO_PERMISSION.getMessage());
        }
    }
}
