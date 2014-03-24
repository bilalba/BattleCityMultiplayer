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
 import javafx.scene.paint.Paint;
import javafx.scene.control.*;
import javafx.animation.PauseTransition;
public class TankWar extends Application implements Transfer {
    Timeline timeline;
    Transfer transfer;
    ArrayList<ImageView> bricks = new ArrayList<ImageView>();
    ArrayList<ImageView> players = new ArrayList<ImageView>();
    ArrayList<ImageView> players_miss = new ArrayList<ImageView>();
    ArrayList<ImageView> enem = new ArrayList<ImageView>();
    ArrayList<ImageView> enem_miss = new ArrayList<ImageView>();
    int count = 0;
    int started = 0;

     public int initiate() {
    started = 1;
    timeline.play();
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
 
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
      new SoundThread();
        TankWar fd = this;
        final Group root = new Group();
    final Scene scene = new Scene(root, 520, 520);
     scene.setFill(Color.BLACK);
     stage.setTitle("Battle City!");
      stage.setScene(scene);
        stage.show();



       
        TextField userTextField = new TextField();
        // hbox1.getChildren().add(userTextField);



        Button button2 = new Button("GO SOLO!");
        button2.relocate(200, 50);
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                // label.setText("GO SOLO!");
           
    final Group root = new Group();
    final Scene scene = new Scene(root, 520, 520);
     scene.setFill(Color.BLACK);
    stage.setTitle("Battle City!");
    stage.setScene(scene);
        stage.show();

        // TEXTT
        Text t = new Text(10, 10, "");
        t.setFill(Color.WHITE);
        t.setFont(new Font(10));
        t.setText("SCORE");
        
        BasicBlock[][] maps = Data.getData().map;
        
        for (int row = 0; row < 13; row++) { // ADD TO ROOT.
          for (int col = 0; col < 13; col++) {
            root.getChildren().add(maps[row][col].iv);
          }
        }

        // DEFINING THE PLAYERS
        
        Player player = Data.getData().player;
        root.getChildren().add(player.iv);
        root.getChildren().add(player.missile.iv);
        stage.addEventHandler(KeyEvent.KEY_PRESSED, player);
        root.getChildren().add(Data.getData().powerup.iv);
        // ------------- END.

        // DEFINING ENEMIES ---------------
        ArrayList<Enemy> enem = Data.getData().enem;
        for (int tv = 0; tv < enem.size(); tv++){
          root.getChildren().add(enem.get(tv).iv); // Transferable.
          root.getChildren().add(enem.get(tv).missile.iv); // Transferable.
        }

        root.getChildren().add(t);
        final Duration oneFrameAmt = Duration.millis(1500/60);
        final KeyFrame oneFrame = new KeyFrame(oneFrameAmt, // MAIN HANDLE.
        new EventHandler<ActionEvent>() {

          public void handle(ActionEvent event) {
            // player1.iv.setX(player1.iv.getX());


            t.setText("SCORE:" + Data.getData().score);

            if (player.helmet) {
              if (player.h_count >= 700) { // CHANGE HELMET.
                player.iv.setImage(new Image("player_left.png"));
                player.h_count = 0;
                Data.getData().powerup.on = false;
                player.helmet = false;
              }
              player.h_count++;
            }

            if (count()%300 == 0 && Data.getData().powerup.on == false) {
              System.out.println("NOW EXECUTE");
              Location loce = new Location(80,240);
              Enemy xy = new Enemy(loce);
              ArrayList<Enemy> enem1 = Data.getData().enem;
              enem1.add(xy);
              root.getChildren().add(enem1.get(enem1.size()-1).iv);
              Data.getData().all.get(3).add(enem1.get(enem1.size()-1).info); // Transferable.
              root.getChildren().add(enem1.get(enem1.size()-1).missile.iv);
              Data.getData().all.get(4).add(enem1.get(enem1.size()-1).missile.info); // Transferable.
            }
            player.doAction();
            
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
            if (Data.getData().speedy > 0){
              Data.getData().speedy++;
                 if (Data.getData().speedy > 1000)
                Data.getData().speedy = 0;
            }
          if (Data.getData().player.killed || Data.getData().eagle.killed) { // STOPP.
            root.getChildren().add(new ImageView(new Image("gameover.png")));
            System.out.println("GAMEOVER");
              timeline.stop();
           }
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
        });
        Button button3 = new Button("Start a new game!");
        button3.relocate(180,100);
        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                  // label.setText("GO SOLO!");
              try {
                              Transfer transfer=(Transfer)UnicastRemoteObject.exportObject(fd,0);

    Registry registry=LocateRegistry.getRegistry();
    registry.rebind("rmi://localhost/transfer",transfer);
    }
                            catch (Exception e1) {}
    System.out.println("Done");

    

    final Group root = new Group();
    final Scene scene = new Scene(root, 520, 520);
     scene.setFill(Color.BLACK);
    stage.setTitle("Battle City!");
    stage.setScene(scene);
        stage.show();

        // TEXTT
        Text t = new Text(10, 10, "");
        t.setFill(Color.WHITE);
        t.setFont(new Font(10));
        t.setText("SCORE");
        
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

        root.getChildren().add(Data.getData().player1.iv);
        root.getChildren().add(t);
        Text t1 = new Text(100, 100, "WAITING...");
            t1.setFill(Color.WHITE);
             t1.setFont(new Font(30));
             t1.setText("WAITING FOR THE OTHER PLAYER..");
             root.getChildren().add(t1);
        final Duration oneFrameAmt = Duration.millis(1500/60);
        final KeyFrame oneFrame = new KeyFrame(oneFrameAmt, // MAIN HANDLE.
        new EventHandler<ActionEvent>() {

          public void handle(ActionEvent event) {
            if (started != 1){
              t1.relocate(50,50);
              timeline.pause();
            } else {
              t1.relocate(-50,-50);
            }
            // t1.relocate(-100,-100);
            // player1.iv.setX(player1.iv.getX());


            t.setText("SCORE:" + Data.getData().score);

            if (player.helmet) {
              if (player.h_count >= 700) { // CHANGE HELMET.
                player.iv.setImage(new Image("player_left.png"));
                player.h_count = 0;
                Data.getData().powerup.on = false;
                player.helmet = false;
              }
              player.h_count++;
            }

            if (count()%300 == 0 && Data.getData().powerup.on == false) {
              System.out.println("NOW EXECUTE");
              Location loce = new Location(80,240);
              Enemy xy = new Enemy(loce);
              ArrayList<Enemy> enem1 = Data.getData().enem;
              enem1.add(xy);
              root.getChildren().add(enem1.get(enem1.size()-1).iv);
              Data.getData().all.get(3).add(enem1.get(enem1.size()-1).info); // Transferable.
              root.getChildren().add(enem1.get(enem1.size()-1).missile.iv);
              Data.getData().all.get(4).add(enem1.get(enem1.size()-1).missile.info); // Transferable.
            }
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
            if (Data.getData().speedy > 0){
              Data.getData().speedy++;
                 if (Data.getData().speedy > 1000)
                Data.getData().speedy = 0;
            }
          if (Data.getData().player.killed || Data.getData().eagle.killed) { // STOPP.
            root.getChildren().add(new ImageView(new Image("gameover.png")));
            System.out.println("GAMEOVER");
              timeline.stop();
           }
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

                 // label.setText("Start a game!");
            }
        });
        TextField ip1 = new TextField();
        ip1.relocate(150, 180);
        ip1.setPromptText("Enter ip/localhost");
        Button button4 = new Button("Join a already running game!");
                button4.relocate(150,200);
        button4.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) { 
              try {
                              Registry registry = LocateRegistry.getRegistry();
                              transfer = (Transfer) registry.lookup("rmi://" + ip1.getText() + "/transfer");
                              transfer.initiate();
                               } catch (Exception e1) {}
                               System.out.println("New1");
                final Group root = new Group();
                final Scene scene = new Scene(root, 520, 520);
                scene.setFill(Color.BLACK);
                stage.setTitle("Battle City!");
                stage.setScene(scene);
                stage.show();
                ArrayList<ArrayList<Info>> all = new ArrayList<ArrayList<Info>>();
                try{
                                all = transfer.getit();
                                } catch (Exception e1) {}

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

                for (Info dv : all.get(4)) {
                    enem_miss.add(new ImageView("abc.png"));
                    ImageView fr = enem_miss.get(enem_miss.size()-1);
                    fr.setX(dv.x);
                    fr.setY(dv.y);
                    fr.setFitHeight(5);
                    fr.setFitWidth(5);
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

                for (Info dv : all.get(2)) {
                    players_miss.add(new ImageView("abc.png"));
                    ImageView fr = players_miss.get(players_miss.size()-1);
                    fr.setX(dv.x);
                    fr.setY(dv.y);
                    fr.setFitHeight(5);
                    fr.setFitWidth(5);
                    root.getChildren().add(fr);
                }

                for (ImageView br : enem)
                    root.getChildren().add(br);

                for (ImageView br : enem_miss)
                    root.getChildren().add(br);
                // for (ImageView x :statics) { 
                //  root.getChildren().add(x);
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

                        ArrayList<Info> e4 = a1.get(4); // ENEMIES MISSILE
                        if (e4.size() > enem_miss.size()) { // ADD ADDITIONAL ENEMIES CODE.
                            for (int i = enem_miss.size(); i < e4.size(); i++) {
                                enem_miss.add(new ImageView("enemy_left.png"));
                                ImageView fr = enem_miss.get(i);
                                fr.setX(e4.get(i).x);
                                fr.setY(e4.get(i).y);
                                fr.setFitHeight(5);
                                fr.setFitWidth(5);
                                root.getChildren().add(fr);
                            }
                        }
                        for (int i = 0; i < e4.size(); i++){ // Here
                            ImageView fr = enem_miss.get(i);
                            Info er = e4.get(i);
                            fr.setX(er.x); // here
                            fr.setY(er.y); // here
                        }

                        ArrayList<Info> p = a1.get(1);

                        for (int i = 0; i < 2; i++){ // Here
                            ImageView fr = players.get(i);
                            Info er = p.get(i);
                            fr.setX(er.x); // here
                            fr.setY(er.y); // here
                            fr.setRotate((er.dir) * 90);
                        }   

                        ArrayList<Info> p2 = a1.get(2);

                        for (int i = 0; i < 2; i++){ // Here
                            ImageView fr = players_miss.get(i);
                            Info er = p2.get(i);
                            fr.setX(er.x); // here
                            fr.setY(er.y); // here
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
                    });

        root.getChildren().add(button2);
        root.getChildren().add(button3);
        root.getChildren().add(button4);
        root.getChildren().add(ip1);


  

        // vbox.getChildren().add(button1);
     


    }
}