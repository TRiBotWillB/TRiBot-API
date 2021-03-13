package scripts.api.utility;

import java.text.NumberFormat;
import java.util.Locale;

public class Strings {

    public static String format(int number) {
        return NumberFormat.getNumberInstance(Locale.US).format(number);
    }

    public static String format(double number) {
        return NumberFormat.getNumberInstance(Locale.US).format((int) number);
    }

    public static String toProperCase(String input) {
        StringBuilder sb = new StringBuilder();
        boolean titleCase = true;
        for (char c : input.toCharArray()) {
            if (c == '_' || Character.isWhitespace(c)) {
                sb.append(" ");
                titleCase = true;
            } else if (titleCase) {
                sb.append(Character.toTitleCase(c));
                titleCase = false;
            } else {
                sb.append(Character.toLowerCase(c));
            }
        }
        return sb.toString();
    }

}