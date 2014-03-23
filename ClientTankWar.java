import javafx.scene.input.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.animation.*;
import java.util.EventListener;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.util.*;
import java.net.*;
import java.io.*;
import java.rmi.*;
import java.rmi.registry.*;
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;
import java.util.ArrayList;

public class ClientTankWar extends Application {
	Timeline timeline;
ArrayList<ImageView> bricks = new ArrayList<ImageView>();
ArrayList<ImageView> players = new ArrayList<ImageView>();
ArrayList<ImageView> players_miss = new ArrayList<ImageView>();
ArrayList<ImageView> enem = new ArrayList<ImageView>();
ArrayList<ImageView> enem_miss = new ArrayList<ImageView>();
ImageView powerup = new ImageView();

	public void start(Stage stage) throws Exception{
		System.out.println("New1");
    	Registry registry = LocateRegistry.getRegistry();
		Transfer transfer = (Transfer) registry.lookup("transfer");
		transfer.initiate();
    final Group root = new Group();
    final Scene scene = new Scene(root, 520, 520);
	scene.setFill(Color.BLACK);
	stage.setTitle("Battle City!");
	stage.setScene(scene);
	stage.show();


	ArrayList<ArrayList<Info>> all = transfer.getit();
	

	for (Info dv : all.get(0)) { // INITIALIZE.
		System.out.println(dv.type);
		if (dv.type == 15){
			bricks.add(new ImageView("bricks.png"));
			bricks.get(bricks.size()-1).setX(dv.x);
			bricks.get(bricks.size()-1).setY(dv.y);
		}else if (dv.type == 17){
			bricks.add(new ImageView("steel.png"));
			bricks.get(bricks.size()-1).setX(dv.x);
			bricks.get(bricks.size()-1).setY(dv.y);
		}else if (dv.type == 16){
			bricks.add(new ImageView("eagless.png"));
			bricks.get(bricks.size()-1).setX(dv.x);
			bricks.get(bricks.size()-1).setY(dv.y);
		} else {
			bricks.add(new ImageView("eagless.png"));
			bricks.get(bricks.size()-1).setX(dv.x);
			bricks.get(bricks.size()-1).setY(dv.y);
		}
	}

	for (ImageView br : bricks)
		root.getChildren().add(br);

	// for (ImageView x :statics) { 
	// 	root.getChildren().add(x);
	// }
	final Duration oneFrameAmt = Duration.millis(1500/60);
	final KeyFrame oneFrame = new KeyFrame(oneFrameAmt, // MAIN HANDLE.
	new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event) {
			System.out.println('s');
			ArrayList<ArrayList<Info>> a1 = new ArrayList<ArrayList<Info>>();
			try {
				a1 = transfer.getit();
			} catch(Exception e) {}
			ArrayList<Info> h= a1.get(0);
			for (int i = 0; i < h.size(); i++){
				bricks.get(i).setX(h.get(i).x);
				bricks.get(i).setY(h.get(i).y);
			}

		}
	});

	timeline = new Timeline(oneFrame);
       timeline.setCycleCount(Timeline.INDEFINITE);
       timeline.play();
}
	public static void main(String[] args) throws Exception{
       	

		Application.launch(args);
	}
}