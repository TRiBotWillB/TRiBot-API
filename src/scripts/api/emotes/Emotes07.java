package scripts.api.emotes;

import org.tribot.api2007.GameTab;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.types.RSInterface;

/**
 * Created by willb on 31/03/2018.
 */
public class Emotes07 {

    public static final int MASTER_ID = 216;
    public static final int CHILD_ID = 1;


    public static RSInterface[] getEmoteInterfaces() {
        RSInterface emotes = Interfaces.get(MASTER_ID, CHILD_ID);

        return emotes != null ? emotes.getChildren() : null;
    }

    public static RSInterface getEmoteInterface(String emoteName) {
        for (RSInterface i : getEmoteInterfaces()) {
            String[] actions = i.getActions();

            if (actions.length > 0 && actions[0] != null && actions[0].equals(emoteName)) {
                return i;
            }
        }
        return null;
    }

    public static boolean clickEmote(String emoteName) {
        if (!GameTab.TABS.EMOTES.isOpen())
            GameTab.TABS.EMOTES.open();

        RSInterface emoteInterface = getEmoteInterface(emoteName);

        return emoteInterface.click();
    }
}
