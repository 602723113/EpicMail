package cc.zoyn.epicmail.command.subcommand;

import cc.zoyn.epicmail.command.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MeCommand implements SubCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player!");
            return;
        }
        Player player = (Player) sender;
    }
}
