package scripts.utils;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.*;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.util.DPathNavigator;
import scripts.api.Timing07;

/**
 * Created by willb on 24/01/2017.
 */
public class NPCUtil {

    public static void talkTo(String npcName, String action) {
        RSNPC[] n = NPCs.find(npcName);

        if (n.length > 0 && n[0] != null) {
            RSNPC[] npcs = NPCs.findNearest(npcName);

            if (npcs.length > 0 && npcs[0] != null) {
                if (Player.getPosition().distanceTo(npcs[0]) > 5)
                    Walking.walkTo(npcs[0]);

                if (!npcs[0].isOnScreen() || !npcs[0].isClickable())
                    //aCamera.turnToTile(npcs[0]);
                    Camera.turnToTile(npcs[0]);

                if (DynamicClicking.clickRSNPC(npcs[0], action)) {
                    Timing07.waitCondition(() -> NPCChat.getMessage() != null || NPCChat.getOptions() != null, General.random(3500, 4500));
                }
            }
        }
    }
}
