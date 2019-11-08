package scripts.utils;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.*;
import org.tribot.api2007.types.*;

import java.util.HashMap;

/**
 * Created by willb on 24/12/2016.
 */
public class BankUtil {

    private static final int BANKING_INTERFACE = 12;
    private static final int SELECTED_TEXTURE = 813;
    private static final int AMOUNT_INTERFACE = 5;
    private static final int BANK_MASTER_ID = 12, WITHDRAW_AS_ITEM_ID = 22, WITHDRAW_AS_NOTED_ID = 24;

    public static HashMap<String, Integer> getBankItems() {
        General.sleep(600, 900);
        HashMap<String, Integer> items = new HashMap<>();

        if (Banking.isBankScreenOpen()) {
            for (RSItem i : Banking.getAll()) {
                RSItemDefinition def = i.getDefinition();
                String name = def != null ? def.getName() : null;

                if (name != null) {
                    items.put(name, i.getStack());
                }
            }
        }
        return items;
    }

    /**
     * Checks if all the items in the bank are properly loaded into memory.
     *
     * @return
     */
    public static boolean isLoaded() {
        return getCurrentBankSpace() == Banking.getAll().length;
    }

    /**
     * Gets the used bank space by reading the ingame interface.
     *
     * @return
     */
    private static int getCurrentBankSpace() {
        RSInterface amount = Interfaces.get(BANKING_INTERFACE, AMOUNT_INTERFACE);
        if (amount != null) {
            String text = amount.getText();
            if (text != null) {
                try {
                    int parse = Integer.parseInt(text);
                    if (parse > 0)
                        return parse;
                } catch (NumberFormatException e) {
                    return -1;
                }
            }
        }
        return -1;
    }

    /**
     * Gets the count of the items in the bank.
     *
     * @param itemName String of item
     * @return The amount of items in the bank that match the ids.
     */
    public static int getCount(String itemName) {
        int count = 0;
        if (!isLoaded()) return count;
        RSItem[] items = Banking.find(itemName);
        for (RSItem item : items)
            count += item.getStack();
        return count;
    }

    public static boolean withdraw(int count, String itemName) {
        if (!Banking.isBankScreenOpen())
            return false;

        int itemCount = Inventory.getCount(itemName);
        if (Banking.withdraw(count, itemName)) {
            return Timing.waitCondition(new Condition() {
                @Override
                public boolean active() {
                    General.sleep(50, 100);
                    return Inventory.getCount(itemName) > itemCount;
                }
            }, General.random(2500, 3200));
        }
        return false;
    }

    public static boolean isNotedOn() {
        return Game.getSetting(115) == 1;
    }

    public static boolean setNoted(boolean noted) {
        if (Banking.isBankScreenOpen()) {
            if (noted && !isNotedOn()) {
                RSInterfaceChild notedInterfaceChild = Interfaces.get(BANK_MASTER_ID, WITHDRAW_AS_NOTED_ID);
                if (notedInterfaceChild != null && notedInterfaceChild.click("Note")) {
                    Timing.waitCondition(new Condition() {
                        @Override
                        public boolean active() {
                            General.sleep(10, 30);
                            return isNotedOn();
                        }
                    }, General.random(800, 1200));
                    return isNotedOn();
                }
            } else if (!noted && isNotedOn()) {
                RSInterfaceChild itemInterfaceChild = Interfaces.get(BANK_MASTER_ID, WITHDRAW_AS_ITEM_ID);
                if (itemInterfaceChild != null && itemInterfaceChild.click("ItemHelper")) {
                    Timing.waitCondition(new Condition() {
                        @Override
                        public boolean active() {
                            General.sleep(10, 30);
                            return !isNotedOn();
                        }
                    }, General.random(800, 1200));
                    return !isNotedOn();
                }
            }
        }
        return false;
    }

    public static void openBank() {
        GrandExchange.close(true);

        RSInterface collectBox = Interfaces.get(402, 2);

        if (collectBox != null) {
            RSInterface close = collectBox.getChild(11);

            if (close.click()) {
                Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        return Interfaces.get(402) == null;
                    }
                }, General.random(3000, 3500));
            }
        }

        if (NPCs.find("Grand Exchange Clerk").length > 0) {
            RSNPC[] bankers = NPCs.findNearest("Banker");

            if (bankers.length > 0 && bankers[0] != null) {
                if (Player.getPosition().distanceTo(bankers[0]) > 5)
                    Walking.walkTo(bankers[0]);

                if (!bankers[0].isClickable() || !bankers[0].isOnScreen())
                    Camera.turnToTile(bankers[0]);

                if (bankers[0].click("Bank Banker")) {
                    Timing.waitCondition(new Condition() {
                        @Override
                        public boolean active() {
                            General.sleep(50, 100);
                            return Banking.isBankScreenOpen();
                        }
                    }, General.random(4500, 6500));
                }
            }
        } else {
            Banking.openBank();
        }
    }
}
