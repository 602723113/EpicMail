package cc.zoyn.epicmail.listener;

import cc.zoyn.epicmail.EpicMail;
import cc.zoyn.epicmail.manager.MailBoxManager;
import cc.zoyn.epicmail.model.MailBox;
import com.google.common.collect.Lists;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Zoyn
 * @since 2018-05-20
 */
public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        File file = new File(EpicMail.getInstance().getMailFolder(), event.getPlayer().getName() + ".yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            MailBox mailBox = new MailBox(UUID.nameUUIDFromBytes(("MailBox:" + player.getName()).getBytes()), player.getName(), Lists.newArrayList());
            MailBoxManager.getInstance().addMailBox(mailBox);
        }
    }

}
