import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class Deluge extends Application {
	final int MAX_X = 500;
	final int MAX_Y = 500;
	final Canvas board = new Canvas(MAX_X, MAX_Y);
	final GraphicsContext gc = board.getGraphicsContext2D();
	ArrayList<Object>redrawArray = new ArrayList<Object>();
	
	public static void main(String[] args) {
		launch(args);
	}
	@Override public void start(Stage primaryStage) {

		Player p1 = new Player(400,400);
		p1.setColor(Color.WHITE);
		redrawArray.add(p1);
		gc.setStroke((Paint) p1.getColor());
		//I can't seem to get the play color correct.
		gc.setFill(Paint.valueOf(p1.getColor().toString()));
		gc.strokeOval(p1.getX(), p1.getY(), 10, 10);
		gc.strokeLine(p1.getX(), p1.getY(), p1.getAim()[0], p1.getAim()[1]);
		gc.getCanvas().setFocusTraversable(true);
		gc.getCanvas().addEventFilter(MouseEvent.MOUSE_MOVED, m -> {	
			p1.setAim(Math.round(m.getX()),	Math.round(m.getY()));
			gc.clearRect(0, 0, board.getHeight(), board.getWidth());
			gc.setStroke((Paint) p1.getColor());
			gc.strokeOval(p1.getX()-5, p1.getY()-5, 10, 10);
			gc.strokeLine(p1.getX(), p1.getY(), p1.getAim()[0], p1.getAim()[1]);
		});
		gc.getCanvas().addEventFilter(KeyEvent.ANY, k -> {
			gc.getCanvas().requestFocus();
			if (k.getCode()==KeyCode.W) {
				if (p1.getY() - p1.MOVESPEED > 0) {
					p1.moveUp();
				}
			}
			if (k.getCode()==KeyCode.S) {
				if (p1.getY() + p1.MOVESPEED < MAX_Y - 10) {
					p1.moveDown();
				}
			}
			if (k.getCode()==KeyCode.D) {
				if (p1.getX() + p1.MOVESPEED < MAX_X - 10) {
					p1.moveRight();
				}
			}
			if (k.getCode()==KeyCode.A) {
				if (p1.getX() - p1.MOVESPEED > 0) {
					p1.moveLeft();
				}
			}
			if (k.getCode()==KeyCode.SPACE) {
				//shoot that muthafucka
			}
			gc.clearRect(0, 0, board.getHeight(), board.getWidth());
			gc.setStroke((Paint) p1.getColor());
			gc.strokeOval(p1.getX()-5, p1.getY()-5, 10, 10);
			gc.strokeLine(p1.getX(), p1.getY(), p1.getAim()[0], p1.getAim()[1]);
		});
		
		Stage myStage = new Stage();
		myStage.setTitle("Liberty Deluge");
		BorderPane pane = new BorderPane();
		pane.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 25), CornerRadii.EMPTY, Insets.EMPTY)));
		pane.setCenter(board);
		myStage.setScene(new Scene(pane));
		myStage.showAndWait();
		
	}//End of start method
	
	
}
