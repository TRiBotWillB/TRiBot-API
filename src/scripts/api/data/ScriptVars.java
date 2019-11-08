package scripts.api.data;

import scripts.api.frameworks.task.TaskScript;
import scripts.api.utility.trackers.SkillsTracker;

public abstract class ScriptVars {

    private static ScriptVars scriptVars;

    private TaskScript script;
    private SkillsTracker skillsTracker;

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
}
