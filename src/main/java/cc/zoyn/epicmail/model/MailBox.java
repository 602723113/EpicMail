package cc.zoyn.epicmail.model;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Zoyn
 * @since 2018-05-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailBox implements ConfigurationSerializable {

    private UUID uuid;
    private String owner;
    private List<Mail> mails;

    @Nonnull
    public static MailBox deserialize(Map<String, Object> map) {
        MailBox box = new MailBox();
        box.setUuid(UUID.fromString((String) map.get("uuid")));
        box.setOwner((String) map.get("owner"));
        box.setMails((List<Mail>) map.get("mails"));

        return box;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> box = Maps.newHashMap();
        box.put("uuid", uuid.toString());
        box.put("owner", owner);
        box.put("mails", mails);

        return box;
    }
}
