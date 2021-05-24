package scripts.api.data;

import org.tribot.api.General;
import scripts.api.frameworks.task.TaskScript;
import scripts.api.utility.trackers.SkillsTracker;

import java.util.HashMap;

public abstract class ScriptVars {

    private static ScriptVars scriptVars;

    private TaskScript script;
    private SkillsTracker skillsTracker;

    private HashMap<String, Integer> bankCache = null;

    private float sleepTimeModifier = 1;

    protected ScriptVars(TaskScript script) {
        this.script = script;
    }

    public static ScriptVars get() {
        return scriptVars;
    }

    public static void set(ScriptVars vars) {
        scriptVars = vars;
    }

    public static void destroy() {
        set(null);
    }

    public TaskScript getScript() {
        return script;
    }

    public void setScript(TaskScript script) {
        this.script = script;
    }

    public SkillsTracker getSkillsTracker() {
        return skillsTracker;
    }

    public void setSkillsTracker(SkillsTracker skillsTracker) {
        this.skillsTracker = skillsTracker;
    }

    public float getSleepTimeModifier() {
        return sleepTimeModifier;
    }

    public void setSleepTimeModifier(float sleepTimeModifier) {
        this.sleepTimeModifier = sleepTimeModifier;
    }


    public HashMap<String, Integer> getBankCache() {
        return bankCache;
    }

    public void setBankCache(HashMap<String, Integer> bankCache) {
        this.bankCache = bankCache;
    }

    public void forceEndScript(String reason) {
        this.script.setRunning(false);
        General.println("[Debug] Stopped script: " + reason);
    }
}
