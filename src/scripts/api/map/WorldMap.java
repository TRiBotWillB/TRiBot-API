package scripts.api.map;

import org.tribot.api.General;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.types.RSInterface;
import scripts.api.Timing07;

/**
 * Created by willb on 18/08/2018.
 */
public class WorldMap {

    private static int MAP_MASTER = 595;
    private static int MAP_CLOSE = 34;

    public static boolean isOpen() {
        return Interfaces.isInterfaceSubstantiated(595);
    }

    public static boolean close() {
        RSInterface close = Interfaces.get(MAP_MASTER, MAP_CLOSE);

        return close != null && close.click() && Timing07.waitCondition(() -> !isOpen(), General.random(3000, 4000));
    }
}
