package scripts;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.ChooseOption;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.types.RSInterfaceChild;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSItemDefinition;
import org.tribot.script.Script;
import org.tribot.script.interfaces.Painting;

import java.awt.*;

public class Test extends Script implements Painting {


    @Override
    public void run() {
        General.println("Running");

        deposit("Raw karambwan");

        while (true) {

            sleep(100);
        }
    }

    public void deposit(String itemName) {
        RSItem[] items = getItems();

        if (items.length > 0) {
            for (RSItem i : items) {
                RSItemDefinition def = i.getDefinition();

                if (def != null && def.getName().equals(itemName)) {
                    Mouse.clickBox(i.getArea(), 3);

                    if (ChooseOption.isOpen()) {
                        if (ChooseOption.select("Pack All " + itemName)) {
                            // Put a wait here
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onPaint(Graphics g) {

        RSItem[] items = getItems();

        for (int i = 0; i < items.length; i++) {
            g.drawRect((int) items[i].getArea().getX(), (int) items[i].getArea().getY(), (int) items[i].getArea().getWidth(), (int) items[i].getArea().getHeight());
        }
    }


    // Returns length 0 if nothing found, or interface not found
    public RSItem[] getItems() {
        RSInterfaceChild inter = Interfaces.get(323, 2);
        RSItem[] items = new RSItem[0];

        if (inter != null) {
            items = inter.getItems();

            int startX = (int) inter.getAbsolutePosition().getX();
            int startY = (int) inter.getAbsolutePosition().getY();

            for (int i = 0; i < items.length; i++) {
                items[i].setArea(new Rectangle((startX + ((i % 7) * 42)), startY + ((i / 7) * 36), 32, 32));

            }
        }

        return items;
    }
}
