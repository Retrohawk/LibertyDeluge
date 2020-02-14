import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Deluge extends Application {
	final int MAX_X = 800;
	final int MAX_Y = 800;
	
	final int ZOMBIE_RATE = 5000;
	final Canvas board = new Canvas(MAX_X, MAX_Y);
	final GraphicsContext gc = board.getGraphicsContext2D();
	ArrayList<Zombie> zombieBag = new ArrayList<Zombie>();
	
	public static void main(String[] args) {
		launch(args);
	}
	@Override public void start(Stage primaryStage) {
		
		Stage myStage = new Stage();
		myStage.setTitle("Liberty Swarm");
		BorderPane pane = new BorderPane();
		Player p1 = new Player("Gorge",400,400);
		p1.setColor(Color.WHITE);
		Timeline REFRESH_SCREEN = new Timeline();
		
		REFRESH_SCREEN.setCycleCount(50);
		EventHandler<ActionEvent> onFinished = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent a) {
				gc.clearRect(0, 0, board.getHeight(), board.getWidth());
				gc.setStroke((Paint) p1.getColor());
				gc.strokeOval(p1.getX()-5, p1.getY()-5, 10, 10);
				gc.setStroke((Paint) Color.LIGHTPINK);
				gc.strokeLine(p1.getX(), p1.getY(), p1.getAim()[0], p1.getAim()[1]);
				for (Zombie z : zombieBag) {
					z.setTarget(p1); //maybe zombie needs a getClosest method to find closest player
					z.stalk();
					gc.setStroke((Paint) z.getColor());
					gc.strokeOval(z.getX()-5, z.getY()-5, 10, 10);
					if ((Math.abs(z.getX()-p1.getX()) <= 2) && (Math.abs(z.getY()-p1.getY()) <= 2)) {
						System.out.println("You've been devoured!");
						REFRESH_SCREEN.stop();
						return;
					}
				}
				
				REFRESH_SCREEN.playFromStart();
			}
		};
		KeyFrame keyFrame = new KeyFrame(Duration.millis(60), onFinished);
		REFRESH_SCREEN.getKeyFrames().add(keyFrame);
		REFRESH_SCREEN.play();
	
		//Add a zombie to the board every five seconds
		Timeline ZOMBIE_CREATOR = new Timeline();
		ZOMBIE_CREATOR.setCycleCount(5000); //Not sure what this does honestly.
		EventHandler<ActionEvent> onFinishedZ = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent a) {
				Zombie z = new Zombie(4);
				z.setColor(Color.GREENYELLOW);
				zombieBag.add(z);
				ZOMBIE_CREATOR.playFromStart();
			}
		};
		KeyFrame keyFrameZ = new KeyFrame(Duration.millis(5000), onFinishedZ);
		ZOMBIE_CREATOR.getKeyFrames().add(keyFrameZ);
		ZOMBIE_CREATOR.play();
		
		
		
		
		
		
		gc.setStroke((Paint) p1.getColor());
		gc.setFill((Paint) p1.getColor());
		gc.fillOval(p1.getX(), p1.getY(), 10, 10);

		gc.strokeLine(p1.getX(), p1.getY(), p1.getAim()[0], p1.getAim()[1]);
		gc.getCanvas().setFocusTraversable(true);
		gc.getCanvas().addEventFilter(MouseEvent.MOUSE_MOVED, m -> {	
			p1.setAim(Math.round(m.getX()),	Math.round(m.getY()));
		});
		gc.getCanvas().addEventFilter(MouseEvent.MOUSE_PRESSED, m -> {
			if (m.getButton()==MouseButton.PRIMARY) {
				//shoot?
			}
		});
		gc.getCanvas().addEventFilter(KeyEvent.KEY_PRESSED, k -> {
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
		});
		
		
		pane.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 25), CornerRadii.EMPTY, Insets.EMPTY)));
		pane.setCenter(board);
		myStage.setScene(new Scene(pane));
		
		
		myStage.showAndWait();
		REFRESH_SCREEN.stop();
		ZOMBIE_CREATOR.stop();
	}//End of start method
	
	
}