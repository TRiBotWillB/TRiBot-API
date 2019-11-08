package scripts.utils;

import org.tribot.api2007.Interfaces;

public class LoginUtil {

    public static boolean isOnWelcomePage() {
        return Interfaces.get(50) != null;
    }
}
