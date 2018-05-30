package cc.zoyn.epicmail.model;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bukkit.inventory.ItemStack;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

import java.util.List;

@Data
@Table("epicmail_mail")
@NoArgsConstructor
@AllArgsConstructor
public class Mail extends Model {

    private String title;
    private String sender;
    private String reciver;
    private String message;
    private List<ItemStack> items = Lists.newArrayList();
    private long sendTime;

    public boolean hasItem() {
        return !items.isEmpty();
    }

}
