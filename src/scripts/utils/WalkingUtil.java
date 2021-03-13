package scripts.utils;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.interfaces.Positionable;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSTile;
import org.tribot.api2007.util.DPathNavigator;

public class WalkingUtil {

    public static void screenWalkLocal(Positionable positionable) {
        DPathNavigator dPathNavigator = new DPathNavigator();

        RSTile path[] = dPathNavigator.findPath(positionable);

        for (RSTile t : path) {
            if (Player.getPosition().distanceTo(t) > 5 || path[path.length - 1].equals(t)) {

                if (Camera.getCameraAngle() < 45)
                    Camera.setCameraAngle(General.random(70, 90));

                if (!t.isOnScreen() || !t.isClickable())
                    Camera.turnToTile(t);

                if (Walking.clickTileMS(t, "Walk here")) {
                    Timing.waitCondition(() -> Player.getPosition().distanceTo(t) < 3,
                            General.random(6000, 8000));
                }
            }
        }
    }
}
