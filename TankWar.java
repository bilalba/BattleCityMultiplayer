import javafx.scene.input.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.animation.*;
import javafx.util.*;
import java.util.EventListener;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
 import java.util.Random;
import java.net.*;
import java.io.*;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
 import java.util.ArrayList;

public class TankWar extends Application implements Transfer {
  
  // This is the class to execute the game
  Timeline timeline;
  int count = 0;
  // Player1 player1; 
  public int initiate() {
    return 0;
  }
  public ArrayList<ArrayList<Info>> getit() throws RemoteException {
    return Data.getData().all;
  }

  public void move2(int bcd) throws RemoteException {
    System.out.println("MOVE IT");
    Player1 player1 = Data.getData().player1;
    player1.movex(bcd);
  }
  public int count() {
    return count ++;
  }

  public void start(Stage stage) throws Exception{
    Transfer transfer=(Transfer)UnicastRemoteObject.exportObject(this,0);
    Registry registry=LocateRegistry.getRegistry();
    registry.rebind("transfer",transfer);
    System.out.println("Done");
    final Group root = new Group();
    final Scene scene = new Scene(root, 520, 520);
     scene.setFill(Color.BLACK);
    stage.setTitle("Battle City!");
    stage.setScene(scene);
        stage.show();

        // TEXTT
        Text t = new Text();
        t.setFont(new Font(20));
        t.setText("First row\nSecond row");




        new SoundThread();
        
        
       

        BasicBlock[][] maps = Data.getData().map;
        ArrayList<Info> siv = Data.getData().all.get(0);
        for (int row = 0; row < 13; row++) { // ADD TO ROOT.
          for (int col = 0; col < 13; col++) {
            root.getChildren().add(maps[row][col].iv);
            siv.add(maps[row][col].info); // Transferable.
          }
        }

        // DEFINING THE PLAYERS
        
        Player player = Data.getData().player;
        Player1 player1 = Data.getData().player1;
        root.getChildren().add(player.iv);
        root.getChildren().add(Data.getData().player1.iv);
        Data.getData().all.get(1).add(player.info);
        Data.getData().all.get(1).add(player1.info); // Transferable.
        root.getChildren().add(player.missile.iv);
        root.getChildren().add(player1.missile.iv);
        Data.getData().all.get(2).add(player.missile.info);
        Data.getData().all.get(2).add(player1.missile.info); // Transferable.
        stage.addEventHandler(KeyEvent.KEY_PRESSED, player);
        root.getChildren().add(Data.getData().powerup.iv);
        Data.getData().all.get(5).add(Data.getData().powerup.info); // Transferable.
        // ------------- END.

        // DEFINING ENEMIES ---------------
        ArrayList<Enemy> enem = Data.getData().enem;
        for (int tv = 0; tv < enem.size(); tv++){
          root.getChildren().add(enem.get(tv).iv);
          Data.getData().all.get(3).add(enem.get(tv).info); // Transferable.
          root.getChildren().add(enem.get(tv).missile.iv);
          Data.getData().all.get(4).add(enem.get(tv).missile.info);; // Transferable.
        }
        final Duration oneFrameAmt = Duration.millis(1500/60);
        final KeyFrame oneFrame = new KeyFrame(oneFrameAmt, // MAIN HANDLE.
        new EventHandler<ActionEvent>() {

          public void handle(ActionEvent event) {

            if (player.helmet) {
              if (player.h_count >= 700) { // CHANGE HELMET.
                player.iv.setImage(new Image("player_left.png"));
                player.h_count = 0;
                Data.getData().powerup.on = false;
                player.helmet = false;
              }
              player.h_count++;
            }

            // if (count()%300 == 0 && Data.getData().powerup.on == false) {
            //   System.out.println("NOW EXECUTE");
            //   Location loce = new Location(80,240);
            //   Enemy xy = new Enemy(loce);
            //   ArrayList<Enemy> enem1 = Data.getData().enem;
            //   enem1.add(xy);
            //   root.getChildren().add(enem1.get(enem1.size()-1).iv);
            //   Data.getData().all.get(3).add(enem1.get(enem1.size()-1).info); // Transferable.
            //   root.getChildren().add(enem1.get(enem1.size()-1).missile.iv);
            //   Data.getData().all.get(4).add(enem1.get(enem1.size()-1).missile.info); // Transferable.
            // }
            player.doAction();
            player1.doAction();
            
            if (Data.getData().timefreeze(0) != 0) {
              if (Data.getData().timefreeze(0) >= 700) { // CHANGE TIMEFREEZE VALUE HERE.
                Data.getData().powerup.on = false;
                Data.getData().timefreeze= 0;
                System.out.println("THIS SHOULD BE ZERO" + Data.getData().timefreeze(0));
              } else {
                System.out.println(Data.getData().timefreeze(1));
              }
            } else {

              for (int tv = 0; tv < enem.size(); tv++) {
                enem.get(tv).doAction();
              }
            }
          // if (Data.getData().player.killed || Data.getData().eagle.killed) { // STOPP.
          //   root.getChildren().add(new ImageView(new Image("gameover.png")));
          //   System.out.println("GAMEOVER");
          //     timeline.stop();
          //  }
           if (count() > 200000) { // STOPP yo!!
            root.getChildren().add(new ImageView(new Image("winner.png")));
            System.out.println("GAMEOVER");
              timeline.stop();
           }
           if (new Random().nextInt() > 0 && count()%350 == 0 && Data.getData().powerup.avail == false) { // half probability 
            System.out.println("executing probability");
                Data.getData().makePowerup();
           }
          }
        });

      timeline = new Timeline(oneFrame);
       timeline.setCycleCount(Timeline.INDEFINITE);
       timeline.play();



  }


  public static void main(String[] args) {
    
    Application.launch(args); //to launch the startMethod

  }

}
