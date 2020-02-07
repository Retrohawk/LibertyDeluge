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
	final Canvas board = new Canvas(500,500);
	final GraphicsContext gc = board.getGraphicsContext2D();
	public static void main(String[] args) {
		launch(args);
	}
	@Override public void start(Stage primaryStage) {
		
		gc.setFill(Color.BLUE);
		Player p1 = new Player(100,100);
		gc.strokeOval(p1.getX(), p1.getY(), 10, 10);
		gc.getCanvas().setFocusTraversable(true);
		gc.getCanvas().addEventFilter(KeyEvent.ANY, k -> {
			gc.getCanvas().requestFocus();
			if (k.getCode()==KeyCode.UP) {
				p1.moveUp();
			}
			if (k.getCode()==KeyCode.DOWN) {
				p1.moveDown();
			}
			if (k.getCode()==KeyCode.RIGHT) {
				p1.moveRight();
			}
			if (k.getCode()==KeyCode.LEFT) {
				p1.moveLeft();
			}
			if (k.getCode()==KeyCode.SPACE) {
				//shoot that muthafucka
			}
			gc.clearRect(p1.getX(), p1.getY(), 12, 12);
			gc.strokeOval(p1.getX(), p1.getY(), 10, 10);
		});
		
		Stage myStage = new Stage();
		myStage.setTitle("Liberty Deluge");
		BorderPane pane = new BorderPane();
		pane.setCenter(board);
		myStage.setScene(new Scene(pane));
		myStage.showAndWait();
		
	}//End of start method
	
}
