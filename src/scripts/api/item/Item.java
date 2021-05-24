package scripts.api.item;

import org.tribot.api.General;
import org.tribot.api2007.Equipment;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSItemDefinition;

/**
 * Created by willb on 08/08/2018.
 */
public class Item {

    private String name;

    private int id;
    private int stack;

    private boolean noted;
    private boolean charged;
    private boolean stackable;

    private Equipment.SLOTS equipmentSlot;

    public Item(RSItem item) {
        RSItemDefinition def = item.getDefinition();
        String name = def != null ? def.getName() : null;
        if (name != null) {
            this.name = name;
            this.id = item.getID();
            this.stack = item.getStack();
            this.noted = def.isNoted();
            this.charged = ItemHelper.isCharged(item);
            this.equipmentSlot = item.getEquipmentSlot();
            this.stackable = item.getStack() > 1;
            General.println(item.getStack() > 1);
        }
    }

    public Item(Item item) {
        this.name = item.name;
        this.id = item.id;
        this.stack = item.stack;
        this.noted = item.noted;
        this.charged = item.charged;
        this.stackable = item.stackable;
        this.equipmentSlot = item.equipmentSlot;
    }

    public Item(String name, int id, int stack, boolean noted, boolean stackable, boolean charged, Equipment.SLOTS slot) {
        this.name = name;
        this.id = id;
        this.stack = stack;
        this.noted = noted;
        this.charged = charged;
        this.equipmentSlot = slot;
    }

    public String getName() {
        return name;
    }

    public int getStack() {
        return stack;
    }

    public boolean isNoted() {
        return noted;
    }

    public int getId() {
        return id;
    }

    public boolean isCharged() {
        return charged;
    }

    public Equipment.SLOTS getEquipmentSlot() {
        return equipmentSlot;
    }

    public boolean isStackable() {
        return stackable;
    }

    public void setStack(int stack) {
        this.stack = stack;
    }
}
