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
public ArrayList<ImageView> getenem() {
	return enem;
}
public void setenem(ArrayList<ImageView> a) {
	enem = a;
}
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


	for (Info dv : all.get(3)) {
		enem.add(new ImageView("enemy_left.png"));
		ImageView fr = enem.get(enem.size()-1);
		fr.setX(dv.x);
		fr.setY(dv.y);
		fr.setFitHeight(30);
		fr.setFitWidth(30);

	}

	for (Info dv : all.get(1)) {
		players.add(new ImageView("player_left.png"));
		ImageView fr = players.get(players.size()-1);
		fr.setX(dv.x);
		fr.setY(dv.y);
		fr.setFitHeight(30);
		fr.setFitWidth(30);
		root.getChildren().add(fr);
	}

	for (ImageView br : enem)
		root.getChildren().add(br);

	// for (ImageView x :statics) { 
	// 	root.getChildren().add(x);
	// }
	final Duration oneFrameAmt = Duration.millis(1500/60);
	final KeyFrame oneFrame = new KeyFrame(oneFrameAmt, // MAIN HANDLE.
	new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event) {
			// System.out.println('s');
			ArrayList<ArrayList<Info>> a1 = new ArrayList<ArrayList<Info>>();
			try {
				a1 = transfer.getit();
			} catch(Exception e) {}


			ArrayList<Info> h= a1.get(0); // UPDATING STATIC OBJECTS.
			for (int i = 0; i < h.size(); i++){ // Here
				bricks.get(i).setX(h.get(i).x); // here
				bricks.get(i).setY(h.get(i).y); // here
			} // here

			ArrayList<Info> e = a1.get(3); // ENEMIES.
			if (e.size() > enem.size()) { // ADD ADDITIONAL ENEMIES CODE.
				for (int i = enem.size(); i < e.size(); i++) {
					enem.add(new ImageView("enemy_left.png"));
					ImageView fr = enem.get(i);
					fr.setX(e.get(i).x);
					fr.setY(e.get(i).y);
					fr.setFitHeight(30);
    				fr.setFitWidth(30);
					root.getChildren().add(fr);
				}
			}
			for (int i = 0; i < e.size(); i++){ // Here
				ImageView fr = enem.get(i);
				Info er = e.get(i);
				fr.setX(er.x); // here
				fr.setY(er.y); // here
				fr.setRotate((er.dir) * 90);
			}

			ArrayList<Info> p = a1.get(1);

			for (int i = 0; i < 2; i++){ // Here
				ImageView fr = players.get(i);
				Info er = p.get(i);
				fr.setX(er.x); // here
				fr.setY(er.y); // here
				fr.setRotate((er.dir) * 90);
			}	



		}
	});

	timeline = new Timeline(oneFrame);
       timeline.setCycleCount(Timeline.INDEFINITE);
       timeline.play();

       EventHandler move = new EventHandler<KeyEvent>() {
           public void handle(KeyEvent event) {
           		int s = 0;
              if(event.getCode() == KeyCode.W)
              {
              	s = 1;
                // circ.setCenterY(circ.getCenterY()-5);
              } else if(event.getCode() == KeyCode.S)
              {
              	s = -1;
                // circ.setCenterY(circ.getCenterY()+5);
              } else if(event.getCode() == KeyCode.A)
              {
              	s = 0;
                // circ.setCenterX(circ.getCenterX()-5);
              } else if(event.getCode() == KeyCode.D)
              {
              	s = 2;
                // circ.setCenterX(circ.getCenterX()+5);
              } else if(event.getCode() == KeyCode.X)
              {
              	s = 10;
                // circ.setCenterX(circ.getCenterX()+5);
              } else {
                
                System.out.println("Invalid Key");
              }
              try {
	                transfer.move2(s);
	            } catch(Exception e) {}
           }
        };
        stage.getScene().setOnKeyPressed(move);
}
	public static void main(String[] args) throws Exception{
       	

		Application.launch(args);
	}
}