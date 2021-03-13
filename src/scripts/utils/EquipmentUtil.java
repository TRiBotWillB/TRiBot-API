package scripts.utils;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Equipment;
import org.tribot.api2007.GameTab;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSItemDefinition;

public class EquipmentUtil {

    public static int EQUIPMENT_INTERFACE_MASTER_ID = 387;
    public static int EQUIPMENT_INTERFACE_VIEW_ID = 1;

    public static int EQUIPMENT_VIEW_INTERFACE_ID = 84;

    public static void openEquipmentView() {
        if (GameTab.TABS.EQUIPMENT.isOpen()) {
            if (InterfaceUtil.clickChildInterface(EQUIPMENT_INTERFACE_MASTER_ID, EQUIPMENT_INTERFACE_VIEW_ID)) {
                Timing.waitCondition(() -> Interfaces.get(EQUIPMENT_VIEW_INTERFACE_ID) != null, General.random(3000, 4000));

                Interfaces.closeAll();
            }
        } else {
            if (GameTab.TABS.EQUIPMENT.open()) {
                Timing.waitCondition(GameTab.TABS.EQUIPMENT::isOpen, General.random(4000, 5000));
            }
        }
    }

    public static void equipItems(String... itemNames) {
        RSItem[] items = Inventory.find(itemNames);

        for (RSItem i : items) {

            if (i.click("Wield")) {

                Timing.waitCondition(() -> Equipment.isEquipped(i.getID()), General.random(2500, 3500));
            }
        }
    }
}
