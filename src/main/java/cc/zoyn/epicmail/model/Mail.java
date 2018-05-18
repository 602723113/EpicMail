package cc.zoyn.epicmail.model;

import lombok.Data;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Data
public class Mail {

    private String title;
    private String sender;
    private String message;
    private List<ItemStack> items;
    private long sendTime;

}
