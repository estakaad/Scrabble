package scrabblegame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Scrabble extends Application {

    Stage stage;
    @Override

    public void start(Stage primaryStage) throws Exception {
        Gui gui = new Gui();
        GuiGame guiGame = new GuiGame();
        //ConsoleGame game = new ConsoleGame();

        stage = primaryStage;
        gui.createScene();
        guiGame.run();


    }

}
