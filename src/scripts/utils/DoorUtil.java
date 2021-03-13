package scripts.utils;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Game;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.ext.Doors;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;

import java.util.function.BooleanSupplier;

public class DoorUtil {

    public static void clickDoor(RSTile doorTile, BooleanSupplier waitCondition) {
        RSObject door = Doors.getDoorAt(doorTile);

        General.println(door == null);

        if (door != null) {
            if (Player.getPosition().distanceTo(door) > 10)
                Walking.walkTo(door);

            if (!door.isOnScreen())
                Camera.turnToTile(door);

            if (door.click()) {
                Timing.waitCondition(waitCondition,
                        General.random(4000, 5500));
            }
        }
    }

}
