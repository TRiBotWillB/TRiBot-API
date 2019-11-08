package scripts.api.mouse;

import org.tribot.api2007.types.RSItem;

import java.awt.*;

/**
 * Created by willb on 18/08/2018.
 */
public class Mouse07 {

    public static RSItem getClosestItemToMouse(RSItem[] items) {
        RSItem closestItem = null;
        double closestDistance = Double.MAX_VALUE;

        for (RSItem i : items) {
            Rectangle itemRect = i.getArea();

            if (itemRect == null)
                continue;

            double newDistance = org.tribot.api.input.Mouse.getDestination().distance(itemRect.getLocation());

            if (newDistance < closestDistance) {
                closestItem = i;
                closestDistance = newDistance;
            }
        }
        return closestItem;
    }
}
