package scripts.api.frameworks.task;

public interface Task {

    int priority();

    boolean validate();

    boolean alwaysExecute();

    void execute();

    String getStatus();

}