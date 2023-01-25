package application;
//package livecoding2022;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

//import java.awt.Color;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.robot.Robot;

public class BugMain extends Application {

	private int someThing = 10;
	private ArrayList<Bug> bugs = new ArrayList<Bug>();
	private ArrayList<Plant> plants = new ArrayList<Plant>();
	private int width = 1000;
	private int height = 700;
	private boolean moved;
	private Pane root;

	public Pane getRoot() {
		return root;
	}

	public void setRoot(Pane root) {
		this.root = root;
	}

	public boolean isMoved() {
		return moved;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	public ArrayList<Plant> getPlants() {
		return plants;
	}

	public void setPlants(ArrayList<Plant> plants) {
		this.plants = plants;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public ArrayList<Bug> getBugs() {
		return bugs;
	}

	public int getSomeThing() {
		return someThing;
	}

	public void setSomeThing(int someThing) {
		this.someThing = someThing;
	}
	
	//random bug constructor
	public Bug randomBug() {
		Bug random = new Bug("Rando", Math.random() * width, Math.random() * height, (int) (Math.random() * 100), 3);

		return random;
	}

	//random plant constructor
	public Plant randomPlant() {
		Plant random = new Plant("rand", Math.random() * width, Math.random() * height, (int) (Math.random() * 50),
				(Color.color(Math.random(), Math.random(), Math.random())));

		return random;
	}

	@Override
	public void start(Stage primaryStage) {
		try {

			
			HBox hbox = new HBox(40);
			// 
			root = new Pane();

			plants.add(randomPlant());
			plants.add(randomPlant());
			plants.add(randomPlant());
			plants.add(randomPlant());
			plants.add(randomPlant());
			plants.add(randomPlant());
			plants.add(randomPlant());
			plants.add(randomPlant());

			for (Plant p : plants) {
				root.getChildren().add(p);
			}

			bugs.add(randomBug());
			bugs.add(randomBug());
			bugs.add(randomBug());
			bugs.add(randomBug());
			bugs.add(randomBug());
			bugs.add(randomBug());
			bugs.add(randomBug());
			bugs.add(randomBug());
			bugs.add(randomBug());
			bugs.add(randomBug());

//			bugs.splice(2,1);
			Image bug = new Image("scarabBug.png");

			//creates a button to add a plant
			Button plantButton = new Button("plant");
			plantButton.setTranslateX(10);
			plantButton.setTranslateY(10);
			plantButton.setStyle("-fx-background-color: green");
			Image plant = new Image("plant.jpg");
			ImageView plantView = new ImageView(plant);
			plantView.setFitWidth(20);
			plantView.setFitHeight(20);
			plantButton.setGraphic(plantView);
			plantButton.resize(20, 10);
			

			root.getChildren().add(plantButton);

		
			//creates a button to add a bug
			Button bugButton = new Button("bug");
			bugButton.setTranslateX(100);
			bugButton.setTranslateY(10);
			bugButton.setStyle("-fx-background-color: yellow");
			ImageView bugView = new ImageView(bug);
			bugView.setFitWidth(20);
			bugView.setFitHeight(20);
			bugButton.setGraphic(bugView);
			bugButton.resize(20, 10);
			
			//plant button functions
			plantButton.setOnAction(v -> {
				Plant p = randomPlant();
				plants.add(p);
				root.getChildren().add(p);

			});
			
			//bug button functions
			bugButton.setOnAction(v -> {
				Bug b = randomBug();
				b.setFill(new ImagePattern(bug));
				bugs.add(b);
				root.getChildren().add(b);
//					Scene scene = new Scene(root, width, height);					
			//	}

			});

			root.getChildren().add(bugButton);
//			hbox.getChildren().add(bugButton);

			
			Image skull = new Image("dead.png");

			//each bug is set with the correct image
			for (Bug b : bugs) {
//				b.setFill(null);
				b.setFill(new ImagePattern(bug));
				root.getChildren().add(b);

			}

			// Button b2 = new Button("Start");
			// b2.setPrefSize(100, 20);

			MyEventHandler v = new MyEventHandler(this);

			KeyFrame frame = new KeyFrame(Duration.millis(100), v);
			Timeline timeline = new Timeline();
			timeline.setCycleCount(javafx.animation.Animation.INDEFINITE);
			timeline.getKeyFrames().add(frame);
			timeline.play();

			StackPane rootPane = new StackPane();
			rootPane.getChildren().add(root);
			Scene scene = new Scene(rootPane, width, height);
			primaryStage.setScene(scene);

			primaryStage.getIcons().add(bug);
			primaryStage.setTitle("Buggggs");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
