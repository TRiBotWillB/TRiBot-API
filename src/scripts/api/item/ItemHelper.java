package scripts.api.item;

import org.tribot.api.General;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSItemDefinition;

/**
 * Created by willb on 08/08/2018.
 */
public class ItemHelper {

    public static boolean isCharged(RSItem i) {
        RSItemDefinition def = i.getDefinition();

        String name = def != null ? def.getName() : null;

        return name != null && name.contains("(");
    }

    public static int getCharges(RSItem i) {
        RSItemDefinition def = i.getDefinition();

        String name = def != null ? def.getName() : null;

        if (name == null || !isCharged(i)) return 0;

        return getCharges(name);
    }

    public static int getCharges(String itemName) {
        return Integer.parseInt(itemName.substring(itemName.indexOf("(") + 1, itemName.indexOf(")")));
    }

}
