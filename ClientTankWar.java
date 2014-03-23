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


	public void start(Stage stage) throws Exception{
		System.out.println("Welcome to Bilal's chatroom.");
    	Registry registry = LocateRegistry.getRegistry();
		Transfer transfer = (Transfer) registry.lookup("transfer");
		transfer.initiate();
    final Group root = new Group();
    final Scene scene = new Scene(root, 520, 520);
	scene.setFill(Color.BLACK);
	stage.setTitle("Battle City!");
	stage.setScene(scene);
	stage.show();
	ArrayList<Info> statics = transfer.getStatics();
	// for (ImageView x :statics) { 
	// 	root.getChildren().add(x);
	// }
	final Duration oneFrameAmt = Duration.millis(1500/60);
	final KeyFrame oneFrame = new KeyFrame(oneFrameAmt, // MAIN HANDLE.
	new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event) {

		}
	});
}
	public static void main(String[] args) throws Exception{
       	

		Application.launch(args);
	}
}