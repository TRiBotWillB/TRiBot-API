package scripts.api.creation;

import org.tribot.api2007.Game;
import scripts.api.messagelistening.MessageListener;

/**
 * Created by willb on 18/09/2017.
 */
public class Creation {

    public static boolean hasItemSelected(String itemName) {
        return Game.getItemSelectionState() == 1 && Game.getSelectedItemName().equals(itemName);
    }
}
