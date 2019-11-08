package scripts.utils;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.*;
import org.tribot.api2007.types.RSPlayer;
//import scripts.exodus.scripts.mule.ExodusOrder;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by willb on 24/12/2016.
 */
public class TradeUtil {

    /*
    public static boolean trade(ExodusOrder order, boolean sendOffer, boolean isMule) {
        String reqOpName = order.getMuleName();

        if (isMule)
            reqOpName = order.getAccountName();

        if (Trading.getWindowState() == null && sendOffer) {
            if (!sendTradeRequest(reqOpName))
                return false;
        }

        if (Trading.getWindowState() != null) {
            String opponentName = Trading.getOpponentName();

            if (opponentName == null || !opponentName.toLowerCase().equals(reqOpName.toLowerCase())) {
                Trading.close();
                return false;
            }

            if (!offerItems(order, isMule))
                return false;

            if (Trading.hasAccepted(true) || isMule) {
                if (Trading.getWindowState() == Trading.WINDOW_STATE.SECOND_WINDOW && Trading.accept()) {
                    return Timing.waitCondition(new Condition() {
                        @Override
                        public boolean active() {
                            return Trading.getWindowState() == null;
                        }
                    }, General.random(3500, 4500));
                } else {
                    Trading.accept();
                }
            }
        }
        return false;
    }

    public static boolean sendTradeRequest(String username) {
        if (Banking.isBankScreenOpen())
            Banking.close();

        RSPlayer[] players = Players.findNearest(username);

        if (players.length > 0 && players[0] != null) {
            if (Player.getPosition().distanceTo(players[0]) > 4)
                Walking.walkTo(players[0]);

            if (!players[0].isOnScreen() || !players[0].isClickable())
                Camera.turnToTile(players[0]);

            if (players[0].click("Trade with " + username)) {
                return Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        General.sleep(50, 100);
                        return Trading.getWindowState() != null;
                    }
                }, General.random(6500, 8500));
            }
        }
        return false;
    }

    public static boolean offerItems(ExodusOrder order, boolean isMule) {
        int tradeOrderCount = 0;

        Iterator it = order.getOrderedItems().entrySet().iterator();

        if (isMule)
            it = order.getGiveItems().entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<String, Integer> next = (Map.Entry<String, Integer>) it.next();

            if (Trading.getCount(true, next.getKey()) >= next.getValue())
                tradeOrderCount++;
        }

        int tradeGiveCount = 0;
        Iterator itGive = null;

        if (!isMule && order.getGiveItems() != null && order.getGiveItems().size() > 0)
            itGive = order.getGiveItems().entrySet().iterator();

        if (isMule)
            itGive = order.getOrderedItems().entrySet().iterator();

        if (itGive != null)
            while (itGive.hasNext()) {
                Map.Entry<String, Integer> next = (Map.Entry<String, Integer>) itGive.next();

                if (Trading.getCount(false, next.getKey()) < next.getValue() && Inventory.getCount(next.getKey()) > 0) {
                    if (!isMule) {
                        if (offer(Inventory.getCount(next.getKey()), next.getKey()))
                            tradeGiveCount++;
                    } else if (offer(next.getValue() - Trading.getCount(false, next.getKey()), next.getKey())) {
                        tradeGiveCount++;
                    }
                } else {
                    tradeGiveCount++;
                }
            }

        return (!isMule && tradeOrderCount == order.getOrderedItems().size() || isMule && tradeOrderCount == order.getGiveItems().size())
                && (!isMule && tradeGiveCount >= order.getGiveItems().size() || isMule && tradeGiveCount == order.getOrderedItems().size());
    }

    public static boolean offer(int count, String itemName) {
        if (Trading.getWindowState() == null || Trading.getWindowState() == Trading.WINDOW_STATE.SECOND_WINDOW)
            return false;

        int itemCount = Trading.getCount(false, itemName);
        if (Trading.offer(count, itemName)) {
            return Timing.waitCondition(new Condition() {
                @Override
                public boolean active() {
                    return Trading.getCount(false, itemName) > itemCount;
                }
            }, General.random(6000, 8000));
        }

        return false;
    }
    */
}
