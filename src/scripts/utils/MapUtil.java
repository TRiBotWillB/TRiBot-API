package scripts.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by willb on 24/01/2017.
 */
public class MapUtil {

    public static boolean mapContainsMap(Map<String, Integer> map1, Map<String, Integer> map2) {
        if (map1.size() == 0 || map2.size() == 0)
            return false;

        int checkCount = 0;
        Iterator it = map2.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> next = (Map.Entry<String, Integer>) it.next();

            if (map1.containsKey(next.getKey()) && map1.get(next.getKey()) >= next.getValue())
                checkCount++;
        }

        return checkCount >= map2.size();
    }

    public static Map<String, Integer> combineMaps(Map<String, Integer> map1, Map<String, Integer> map2) {
        Map<String, Integer> newMap = new HashMap<>();

        Iterator it1 = map1.entrySet().iterator();
        while (it1.hasNext()) {
            Map.Entry<String, Integer> next = (Map.Entry<String, Integer>) it1.next();
            newMap.put(next.getKey(), next.getValue());
        }

        Iterator it2 = map2.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry<String, Integer> next = (Map.Entry<String, Integer>) it2.next();
            if (newMap.containsKey(next.getKey())) {
                newMap.put(next.getKey(), newMap.get(next.getKey()) + next.getValue());
            } else {
                newMap.put(next.getKey(), next.getValue());
            }
        }
        return newMap;
    }
}
