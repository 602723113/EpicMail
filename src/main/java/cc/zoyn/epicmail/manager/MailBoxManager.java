package cc.zoyn.epicmail.manager;

import cc.zoyn.epicmail.model.MailBox;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.Validate;

import java.util.List;
import java.util.Optional;

/**
 * @author Zoyn
 * @since 2018-05-20
 */
public class MailBoxManager {

    private static volatile MailBoxManager instance;
    private List<MailBox> mailBoxes = Lists.newArrayList();

    /**
     * get the instance of MailBoxManager
     *
     * @return {@link MailBoxManager}
     */
    public static MailBoxManager getInstance() {
        if (instance == null) {
            synchronized (MailBoxManager.class) {
                if (instance == null) {
                    instance = new MailBoxManager();
                }
            }
        }
        return instance;
    }

    public void addMailBox(MailBox mailBox) {
        mailBoxes.add(Validate.notNull(mailBox));
    }

    public Optional<MailBox> getMailBoxByOwnerName(String ownerName) {
        return mailBoxes.stream()
                .filter(mailBox -> mailBox.getOwner().equals(ownerName))
                .findAny();
    }

    public Optional<MailBox> getMailBoxByUUID(String uuid) {
        return mailBoxes.stream()
                .filter(mailBox -> mailBox.getUuid().toString().equals(uuid))
                .findAny();
    }

}
