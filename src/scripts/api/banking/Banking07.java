package scripts.api.banking;

import org.tribot.api.General;
import org.tribot.api2007.Banking;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSItemDefinition;

import java.util.HashMap;

/**
 * Created by willb on 30/03/2018.
 */
public class Banking07 {

    public static HashMap<String, Integer> getBankCache() {
        HashMap<String, Integer> items = new HashMap<>();

        General.sleep(600, 900);

        if(Banking.isBankScreenOpen()) {
            for(RSItem i : Banking.getAll()) {
                RSItemDefinition def = i.getDefinition();
                String name = def != null ? def.getName() : null;
                if (name != null) {
                    items.put(i.getDefinition().getName(), i.getStack());
                }
            }
        }

        return items;
    }
}
