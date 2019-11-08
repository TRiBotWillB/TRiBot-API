package scripts.api.frameworks.task;

import org.tribot.script.Script;
import org.tribot.script.interfaces.MessageListening07;
import scripts.api.data.ScriptVars;
import scripts.api.messagelistening.MessageListener;
import scripts.api.painting.PaintingInfo;
import scripts.api.painting.components.*;
import scripts.dax_api.api_lib.DaxWalker;
import scripts.dax_api.api_lib.models.DaxCredentials;
import scripts.dax_api.api_lib.models.DaxCredentialsProvider;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by willb on 30/03/2018.
 */
public abstract class TaskScript extends Script implements MessageListening07, PaintingInfo {

    private boolean isRunning = true;
    private String status = "";
    private JFrame gui;
    private TaskSet taskSet = new TaskSet();
    private ArrayList<MessageListener> messageListeners = new ArrayList<>();

    @Override
    public void run() {
        DaxWalker.setCredentials(new DaxCredentialsProvider() {
            @Override
            public DaxCredentials getDaxCredentials() {
                return new DaxCredentials("sub_Dj6baWx0kugwMe ", "63038a71-b591-4a92-b377-140285c8f5a5");
            }
        });

        if (gui != null) {
            EventQueue.invokeLater(() -> gui.setVisible(true));
        }

        onStart();

        while (isRunning) {
            for (Task task : taskSet) {
                if (task != null && task.validate()) {
                    status = task.getStatus();

                    task.execute();

                    if (!task.alwaysExecute())
                        break;
                } else {
                    status = "";
                }
            }

            sleep(100);
        }

        onStop();
    }

    public abstract void onStart();

    public abstract void onStop();

    public abstract ScriptVars getVars();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setGui(JFrame gui) {
        this.gui = gui;
    }

    public TaskSet getTaskSet() {
        return taskSet;
    }

    public void setTaskSet(TaskSet taskSet) {
        this.taskSet = taskSet;
    }

    public void addMessageListener(MessageListener listener) {
        this.messageListeners.add(listener);
    }

    @Override
    public void serverMessageReceived(String s) {
        for (MessageListener o : messageListeners) {
            o.serverMessageReceived(s);
        }
    }

    @Override
    public void clanMessageReceived(String s, String s1) {
        for (MessageListener o : messageListeners) {
            o.clanMessageReceived(s, s1);
        }
    }

    @Override
    public void duelRequestReceived(String s, String s1) {
        for (MessageListener o : messageListeners) {
            o.duelRequestReceived(s, s1);
        }
    }

    @Override
    public void personalMessageReceived(String s, String s1) {
        for (MessageListener o : messageListeners) {
            o.personalMessageReceived(s, s1);
        }
    }

    @Override
    public void playerMessageReceived(String s, String s1) {
        for (MessageListener o : messageListeners) {
            o.playerMessageReceived(s, s1);
        }
    }

    @Override
    public void tradeRequestReceived(String s) {
        for (MessageListener o : messageListeners) {
            o.tradeRequestReceived(s);
        }
    }

    @Override
    public PaintComponent[] getPaintComponents() {
        return new PaintComponent[]{
                new PaintListComponent.Builder().addString("Testing").addString("Testing2").build()
        };
    }
}
