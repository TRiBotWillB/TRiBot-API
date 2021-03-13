package scripts.utils;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.*;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.util.DPathNavigator;

import java.util.function.BooleanSupplier;

/**
 * Created by willb on 24/01/2017.
 */
public class ObjectUtil {

    public static void clickObject(String objectName, String action, Condition waitCondition) {
        RSObject[] o = Objects.find(10, objectName);

        if (o.length > 0 && o[0] != null) {
            if (Player.getPosition().distanceTo(o[0]) > 3) {
                Walking.walkTo(o[0]);
            }
            if (!o[0].isOnScreen() || !o[0].isClickable())
                Camera.turnToTile(o[0]);

            if (DynamicClicking.clickRSObject(o[0], action)) {
                if (waitCondition != null) {
                    Timing.waitCondition(waitCondition, General.random(4500, 5500));
                }
            }
        }
    }

    public static void clickObject(int[] objectIDs, String action, Condition waitCondition) {
        RSObject[] o = Objects.findNearest(10, objectIDs);

        if (o.length > 0 && o[0] != null) {
            if (Player.getPosition().distanceTo(o[0]) > 3 || !PathFinding.canReach(o[0], true)) {
                DPathNavigator dPathNavigator = new DPathNavigator();
                dPathNavigator.traverse(o[0]);
            }

            if (!o[0].isOnScreen() || !o[0].isClickable())
                Camera.turnToTile(o[0]);

            if (DynamicClicking.clickRSObject(o[0], action)) {
                if (waitCondition != null) {
                    Timing.waitCondition(waitCondition, General.random(4500, 5500));
                }
            }
        }
    }

    public static void clickObject(String objectName, String action, BooleanSupplier waitCondition) {
        RSObject[] o = Objects.find(10, objectName);

        if (o.length > 0 && o[0] != null) {
            if (Player.getPosition().distanceTo(o[0]) > 3) {
                Walking.walkTo(o[0]);
            }
            if (!o[0].isOnScreen() || !o[0].isClickable())
                Camera.turnToTile(o[0]);

            if (DynamicClicking.clickRSObject(o[0], action)) {
                if (waitCondition != null) {
                    Timing.waitCondition((BooleanSupplier) waitCondition, General.random(4500, 5500));
                }
            }
        }
    }

    public static void clickObject(int objectID, String action, BooleanSupplier waitCondition) {
        RSObject[] o = Objects.findNearest(10, objectID);

        if (o.length > 0 && o[0] != null) {
            if (Player.getPosition().distanceTo(o[0]) > 3 || !PathFinding.canReach(o[0], true)) {
                DPathNavigator dPathNavigator = new DPathNavigator();
                dPathNavigator.traverse(o[0]);
            }

            if (!o[0].isOnScreen() || !o[0].isClickable())
                Camera.turnToTile(o[0]);

            if (DynamicClicking.clickRSObject(o[0], action)) {
                if (waitCondition != null) {
                    Timing.waitCondition(waitCondition, General.random(7500, 9500));
                }
            }
        }
    }
}
