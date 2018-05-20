package cc.zoyn.epicmail.gui;

import cc.zoyn.epicmail.I18n;
import cc.zoyn.epicmail.manager.MailBoxManager;
import cc.zoyn.epicmail.model.MailBox;
import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Zoyn
 * @since 2018-05-20
 */
public class MailBoxGUI {

    private static ItemStack prePageItem;
    private static ItemStack nextPageItem;

    static {
        ItemMeta itemMeta;
        prePageItem = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
        itemMeta = prePageItem.getItemMeta();
        itemMeta.setDisplayName(I18n.GUI_PREVIOUS_PAGE.getMessage());
        prePageItem.setItemMeta(itemMeta);

        nextPageItem = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
        itemMeta = nextPageItem.getItemMeta();
        itemMeta.setDisplayName(I18n.GUI_NEXT_PAGE.getMessage());
        nextPageItem.setItemMeta(itemMeta);


    }

    public static void openMailBox(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 54, I18n.MAIL_BOX_NAME.getMessage());

        Optional<MailBox> mailBoxOptional = MailBoxManager.getInstance().getMailBoxByOwnerName(player.getName());
        MailBox mailBox;
        if (!mailBoxOptional.isPresent()) {
            mailBox = new MailBox(UUID.nameUUIDFromBytes(("MailBox:" + player.getName()).getBytes()), player.getName(), Lists.newArrayList());
            MailBoxManager.getInstance().addMailBox(mailBox);
        } else {
            mailBox = mailBoxOptional.get();
        }

        mailBox.getMails().forEach(System.out::println);

        inventory.setItem(45, prePageItem);
        inventory.setItem(53, nextPageItem);

        ItemStack page = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 0);
        ItemMeta itemMeta = page.getItemMeta();
        itemMeta.setDisplayName(I18n.GUI_PAGE.getMessage().replace("%page%", "1"));
        page.setItemMeta(itemMeta);

        int[] white = {46, 47, 48, 49, 50, 51, 52};
        for (int index : white) {
            inventory.setItem(index, page);
        }

        player.closeInventory();
        player.openInventory(inventory);
    }

}
