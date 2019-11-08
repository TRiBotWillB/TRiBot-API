package scripts.api;

import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Skills;
import scripts.api.condition.Condition07;
import scripts.api.data.ScriptVars;
import scripts.api.utility.trackers.SkillsTracker;

/**
 * Created by willb on 31/03/2018.
 */
public class Timing07 {

    public static boolean waitCondition(Condition07 condition, long time) {
        return Timing.waitCondition(new Condition() {
            @Override
            public boolean active() {
                return condition.active();
            }
        }, time);
    }
}
