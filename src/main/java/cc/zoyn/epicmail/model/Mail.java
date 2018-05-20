package cc.zoyn.epicmail.model;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mail implements ConfigurationSerializable {

    private String title;
    private String sender;
    private String reciver;
    private String message;
    private List<ItemStack> items;
    private long sendTime;

    @Nonnull
    public static Mail deserialize(Map<String, Object> map) {
        Mail mail = new Mail();
        mail.setTitle((String) map.get("title"));
        mail.setSender((String) map.get("sender"));
        mail.setReciver((String) map.get("reciver"));
        mail.setMessage((String) map.get("message"));
        mail.setItems((List<ItemStack>) map.get("items"));
        mail.setSendTime((long) map.get("sendTime"));

        return mail;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> mail = Maps.newHashMap();
        mail.put("title", title);
        mail.put("sender", sender);
        mail.put("reciver", reciver);
        mail.put("message", message);
        mail.put("items", items);
        mail.put("sendTime", sendTime);

        return mail;
    }
}
