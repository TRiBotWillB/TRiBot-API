package scripts.api.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;

import javax.swing.*;
import java.net.URL;

/**
 * @author Laniax
 */
public class GUI extends Application {

    private final URL fxml;
    private final URL[] stylesheets;

    private Stage stage;
    private Scene scene;
    private AbstractGUIController controller;
    private boolean decorated = true;
    private String title;

    private boolean isOpen = false;

    public GUI(URL fxml) {
        this(fxml, false);
    }

    public GUI(URL fxml, boolean decorated) {
        this(fxml, null, decorated);
    }

    public GUI(URL fxml, URL[] stylesheets) {
        this(fxml, stylesheets, true);
    }

    public GUI(URL fxml, URL[] stylesheets, boolean decorated) {

        this.fxml = fxml;
        this.stylesheets = stylesheets;
        this.decorated = decorated;

        // We have to start the JFX thread from the EDT otherwise tribot will end it.
        SwingUtilities.invokeLater(() -> {

            new JFXPanel(); // we have to init the toolkit

            Platform.runLater(() -> {
                try {
                    final Stage stage = new Stage();

                    if (!this.decorated) {
                        stage.initStyle(StageStyle.TRANSPARENT);
                    }

                    start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });

        waitForInit();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Scene getScene() {
        return this.scene;
    }

    public Stage getStage() {
        return this.stage;
    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     * <p>
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set. The primary stage will be embedded in
     *              the browser if the application was launched as an applet.
     *              Applications may create other stages, if needed, but they will not be
     *              primary stages and will not be embedded in the browser.
     */
    @Override
    public void start(Stage stage) throws Exception {
        stage.initStyle(StageStyle.TRANSPARENT);


        if (fxml == null) {
            General.println("fxml is null. aborting");
            return;
        }

        this.stage = stage;

        if (title != null) {
            stage.setTitle(title);
        }
//        stage.setAlwaysOnTop(true);

        Platform.setImplicitExit(false);

        FXMLLoader loader = new FXMLLoader(fxml);

        // By default FXMLLoader uses a different classloader, this caused issues with upcasting
        loader.setClassLoader(this.getClass().getClassLoader());

        Parent box = loader.load();

        controller = loader.getController();

        if (controller == null) {
            General.println("Please add a controller to your fxml!");
            return;
        }

        controller.setGUI(this);

        scene = new Scene(box);

        scene.setFill(Color.TRANSPARENT);

        if (this.stylesheets != null) {
            for (URL stylesheet : stylesheets) {
                scene.getStylesheets().add(stylesheet.toExternalForm());
            }
        }

        stage.setScene(scene);

    }

    public <T extends AbstractGUIController> T getController() {

        return (T) this.controller;

    }

    public void show() {

        if (stage == null)
            return;

        isOpen = true;

        Platform.runLater(() -> stage.show());
    }

    public void close() {

        if (stage == null)
            return;

        isOpen = false;

        Platform.runLater(() -> stage.close());
    }

    public boolean isOpen() {
        return isOpen;
    }

    private void waitForInit() {

        Timing.waitCondition(new Condition() {
            @Override
            public boolean active() {
                General.sleep(250);
                return stage != null;
            }
        }, 5000);
    }
}