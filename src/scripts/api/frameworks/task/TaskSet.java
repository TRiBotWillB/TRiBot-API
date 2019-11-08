package scripts.api.frameworks.task;

import org.tribot.api.General;

import java.util.*;

public class TaskSet extends TreeSet<Task> {

    public TaskSet() {
        super((o1, o2) -> Integer.compare(o2.priority(), o1.priority()) < 0 ? -1 : 1);
    }

    public TaskSet(Task... tasks) {
        super((o1, o2) -> Integer.compare(o2.priority(), o1.priority()) < 0 ? -1 : 1);
        addAll(tasks);
    }

    public TaskSet(Comparator<? super Task> comparator) {
        this();
    }

    public TaskSet(Collection<? extends Task> c) {
        this(c.toArray(new Task[c.size()]));
    }

    public TaskSet(SortedSet<Task> s) {
        this(s.toArray(new Task[s.size()]));
    }

    public boolean addAll(Task... tasks) {
        return super.addAll(Arrays.asList(tasks));
    }

}