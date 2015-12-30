package scrabblegame;

import javafx.application.Application;
import javafx.stage.Stage;

public class Scrabble extends Application {

    Stage stage;
    @Override

    public void start(Stage primaryStage) throws Exception {
        Gui gui = new Gui();
        Game game = new Game();
        //ConsoleGame game = new ConsoleGame();

        stage = primaryStage;
        gui.createScene();
        game.run();


    }

}
