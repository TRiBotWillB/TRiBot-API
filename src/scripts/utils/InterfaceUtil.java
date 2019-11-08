package scripts.utils;

import org.tribot.api.Timing;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.types.RSInterface;
import org.tribot.api2007.types.RSInterfaceChild;
import scripts.api.Timing07;

import java.util.Arrays;

/**
 * Created by willb on 18/12/2016.
 */
public class InterfaceUtil {

    public static boolean clickChildInterface(int masterID, int childID) {
        RSInterfaceChild inter = Interfaces.get(masterID, childID);

        return inter != null && !inter.isHidden() && inter.click();
    }

    public static boolean closeInterfaceExists() {
        RSInterface[] all = Interfaces.getAll();
        for (RSInterface i : all) {
            if (i != null && i.getActions() != null && Arrays.asList(i.getActions()).contains("Close")) {
                return true;
            }
        }
        return false;
    }

    public static boolean clickCloseInterface() {
        RSInterface[] all = Interfaces.getAll();
        for (RSInterface i : all) {
            if (i != null && i.getActions() != null && Arrays.asList(i.getActions()).contains("Close")) {
                System.out.println("Interface found: Clicking Close.");
                if (i.click("Close")) {
                    Timing07.waitCondition(() -> i.isHidden(), 2000);
                    return true;
                }
            }
        }
        return false;
    }
}
