import javafx.scene.image.*;
import javafx.event.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Player extends Tank implements EventHandler<KeyEvent> {
  int h_count = 0;
  boolean helmet = false;
  public Player(Location l,Image im){
		super(l,im);
	}
  public Player(Location l){
    super(l);
    play = 1;
    missile = new Missile(new Location(-40, -40), 1);
    image = new Image("player_left.png"); 
    iv = new ImageView(image);
    iv.setX(l.getRow());
    iv.setY(l.getCol());
    iv.setFitHeight(30);
    iv.setFitWidth(30);
  }
	
  public void impact(int f){
    if (helmet) {
      return;
    } 
    dele = 1;
    iv.setImage(new Image("1.png"));
  }
	/*
	 * You need to register the appropriate event handling at the root node level.
	 * eg, if Group is your root node, then addEventHandler(KeyEvent.KEY_TYPED, player)
	 */

  public void doAction() {
    iv.setX(location.getCol());
    iv.setY(location.getRow());
    dele = 0;
    if (dele > 0){
      if (dele < 3) {
        iv.setImage(new Image("2.png"));
        dele++;
      } else if (dele < 5) {
        iv.setImage(new Image("3.png"));
        dele++;
      } else if (dele < 7) {
        iv.setImage(new Image("4.png"));
        dele++;
      } else if (dele < 9) {
        iv.setImage(new Image("5.png"));
        dele++;
      } else {
            killed = true;
    System.out.println("GAME OVER");
        }
    }
    missile.doAction();
  }

	public void handle(KeyEvent event){
		// KeyEvent event = (KeyEvent) e;
		if(event.getCode() == KeyCode.UP)
              {
                direction = 1;
              	if (movePossible()) {
                  makeMove();
                }
                System.out.println("Up key pressed");
              } else if(event.getCode() == KeyCode.DOWN)
              {
                direction = -1;
              	if (movePossible()) {
                  makeMove();
                }
                System.out.println("Down key pressed");
              } else if(event.getCode() == KeyCode.LEFT)
              {
                direction = 0;
              	if (movePossible()) {
                  makeMove();
                }
                System.out.println("Left key pressed");
              } else if(event.getCode() == KeyCode.RIGHT)
              {
                direction = 2;
              	if (movePossible()) {
                  makeMove();
                }
                System.out.println("Right key pressed");
              } else if(event.getCode() == KeyCode.Z)
              {
              	
                System.out.println("Fire Key!");
              } else if(event.getCode() == KeyCode.X)
              {
                if (!missile.ml) {
                     missile.ml = true;
                     missile.direction = direction;
                    
                     missile.location.setLocation(location.getRow()+14,location.getCol()+14);
                     missile.iv.setRotate((missile.direction) * 90);
                     missile.makeMove();
                      missile.makeMove();
                      missile.makeMove();
                      missile.makeMove();
                      }
                System.out.println("X KEY");
              } else {
                System.out.println("Unused key :" + event.getCode());
              }

              if (direction != prevdir) { // PLAYER CHANGE DIRECTION
              iv.setRotate((direction) * 90);
              prevdir = direction;
            }

		//logic for player. Remember that the player is not allowed to make more
		//than one move per 'tick' or frame.
	}
}
