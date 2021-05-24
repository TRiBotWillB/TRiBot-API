package scripts.webtest;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.tribot.script.Script;
import org.tribot.script.interfaces.Ending;
import scripts.api.gui.GUI;

import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;

public class test2 extends Script implements Ending {

    GUI gui =null;

    @Override
    public void run() {

        URL url = null;

        try {
            url = new URL("http://localhost:8088/farmer-gui.fxml");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        gui = new GUI(url);

        gui.show();

        while (true) {

            sleep(50);
        }
    }

    @Override
    public void onEnd() {

        gui.close();
    }
}
