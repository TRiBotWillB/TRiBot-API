package scripts.utils;

import org.tribot.api.General;
import org.tribot.api2007.Game;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSItemDefinition;
import scripts.api.Timing07;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by willb on 15/01/2017.
 */
public class InventoryUtil {
    public static boolean hasNoted(String itemName) {
        for (RSItem i : Inventory.find(itemName)) {
            if (i == null)
                continue;

            RSItemDefinition def = i.getDefinition();

            if (def == null)
                continue;

            if (def.isNoted())
                return true;
        }
        return false;
    }

    public static boolean hasUnNoted(String itemName) {
        for (RSItem i : Inventory.find(itemName)) {
            if (i == null)
                continue;

            RSItemDefinition def = i.getDefinition();

            if (def == null)
                continue;

            if (!def.isNoted())
                return true;
        }
        return false;
    }

    public static boolean hasItemContaining(String str) {
        for (RSItem i : Inventory.getAll()) {
            if (i.name.contains(str))
                return true;
        }
        return false;
    }

    public static void selectItem(String itemName, boolean noted) {
        RSItem[] items = Inventory.find(itemName);

        for (RSItem i : items) {
            RSItemDefinition def = i.getDefinition();

            if (def == null || def.isNoted() != noted)
                continue;

            if (i.click("Use")) {
                Timing07.waitCondition(() -> Game.getItemSelectionState() == 1 && Game.getSelectedItemName().equals(itemName), General.random(3500, 4500));
                break;
            }
        }
    }

    public static boolean hasSelected(String itemName) {
        String selected = Game.getSelectedItemName();
        return (Game.getUptext().contains(itemName + " ->") || Game.getUptext().contains("Cancel")) && selected != null && selected.equals(itemName);
    }

    public static boolean containsAllNoted(HashMap<String, Integer> items) {
        return MapUtil.mapContainsMap(getRequiredItemsInInventory(true, items), items);
    }

    public static boolean containsAllUnNoted(HashMap<String, Integer> items) {
        return MapUtil.mapContainsMap(getRequiredItemsInInventory(false, items), items);
    }

    public static boolean containsAll(HashMap<String, Integer> items) {
        return MapUtil.mapContainsMap(getRequiredItemsInInventory(items), items);
    }

    public static HashMap<String, Integer> getRequiredItemsInInventory(HashMap<String, Integer> neededItems) {
        HashMap<String, Integer> hasItems = getRequiredItemsInInventory(false, neededItems);

        hasItems = (HashMap) MapUtil.combineMaps(hasItems, getRequiredItemsInInventory(true, neededItems));

        return hasItems;
    }


    public static HashMap<String, Integer> getRequiredItemsInInventory(boolean noted, HashMap<String, Integer> neededItems) {
        HashMap<String, Integer> hasItems = new HashMap<>();

        Iterator it = neededItems.entrySet().iterator();

        while (it.hasNext()) {
            HashMap.Entry<String, Integer> next = (HashMap.Entry<String, Integer>) it.next();

            RSItem[] item = Inventory.find(next.getKey());
            for (RSItem i : item) {
                RSItemDefinition def = i.getDefinition();

                if (def != null) {
                    if (def.isNoted() && noted) {
                        hasItems.put(next.getKey(), i.getStack());
                        break;
                    } else if (!noted && !def.isNoted()) {
                        if (hasItems.containsKey(next.getKey())) {
                            hasItems.put(next.getKey(), hasItems.get(next.getKey()) + 1);
                        } else {
                            hasItems.put(next.getKey(), 1);
                        }
                    }
                }
            }

        }
        return hasItems;
    }

}
