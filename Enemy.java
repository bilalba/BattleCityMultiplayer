import javafx.scene.image.*;
 import java.util.Random;

public class Enemy extends Tank {
	public Enemy(Location l, Image im){
		super(l, im);
	}
	public Enemy(Location l){
		super(l);
    play = 2;
      missile = new Missile(new Location(-40, -40), 2);
    	image = new Image("enemy_left.png"); 
    	iv = new ImageView(image);
    	iv.setX(500);
    	iv.setY(500);
    	iv.setFitHeight(30);
    	iv.setFitWidth(30);
	}

  public void impact(int f){
    dele = 1;
    iv.setImage(new Image("1.png"));
  }
	public void doAction() {
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
          iv.setY(-40);
          iv.setX(-40);
          location.setLocation(-40,-40);
          missile.iv.setY(-40);
          missile.iv.setX(-40);
          missile.location.setLocation(-40,-40);
          killed = true;
        }
    }
    if (killed) {
      return;
    }
              if (direction != prevdir) { // PLAYER CHANGE DIRECTION
              iv.setRotate((direction) * 90);
              prevdir = direction;
            }
            
              boolean x = movePossible();
              while (!x) {
                Random rand = new Random();
                int dv = rand.nextInt(4) -1;
                while (dv == direction) {
                  rand = new Random();
                  dv = rand.nextInt(4) -1;
                }
                direction = dv;
                System.out.println(dv);
                x = movePossible();
              }
              makeMove();
              if (!missile.ml) {
              	missile.ml = true;
              	missile.direction = direction;
                missile.location.setLocation(location.getRow()+ 16,location.getCol()+16);
                missile.iv.setRotate((missile.direction) * 90);
                missile.makeMove();
                missile.makeMove();
                missile.makeMove();
                missile.makeMove();
                missile.makeMove();
              } else {
              	missile.doAction();
              }
			
	}


}