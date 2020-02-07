import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Deluge extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	@Override public void start(Stage primaryStage) {
		Canvas board = new Canvas(500,500);
		final GraphicsContext gc = board.getGraphicsContext2D();
		gc.setFill(Color.BLUEVIOLET);
		Player p1 = new Player(100,100);
		board.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent k) {
				if (k.getCode().equals(KeyCode.UP)){
					p1.moveUp();
				}
				if (k.getCode().equals(KeyCode.DOWN)) {
					p1.moveDown();
				}
				if (k.getCode().equals(KeyCode.LEFT)) {
					p1.moveLeft();
				}
				if (k.getCode().equals(KeyCode.RIGHT)) {
					p1.moveRight();
				}
				if (k.getCode().equals(KeyCode.SPACE)) {
					//Shoot - to be implemented
				}
			}
		}); //End Keyboard Event Handler
		Stage myStage = new Stage();
		myStage.setTitle("Liberty Deluge");
		BorderPane pane = new BorderPane();
		pane.setCenter(board);
		myStage.setScene(new Scene(pane));
		myStage.showAndWait();
		
	}//End of start method
}
