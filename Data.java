import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.util.ArrayList;
 import java.util.Random;

public class Data {
  public Powerup powerup;
  ArrayList<ArrayList<Info>> all = new ArrayList<ArrayList<Info>>();
	public BasicBlock[][] map;
	public ArrayList<Enemy> enem;
	public Player player;
  public Player player1;
  public Eagle eagle;
	private static Data aData;
  public int timefreeze = 0;
  public int timefreeze(int f) {
    timefreeze = timefreeze+f;
    return timefreeze;
  }
  public void makePowerup() {
    if (!powerup.on) {
      Random rand = new Random();
      int dv = rand.nextInt(3) + 1;
      Random rand1 = new Random();
      int rt = rand1.nextInt(4) + 1;
      Location fr;
      if (rt == 1) {
       fr = new Location(12*40, 12*40);
      } else if (rt == 2) {
        fr = new Location(5*40, 12*40);
      } else if (rt == 3){
        fr = new Location(5*40, 0);  
      } else {
        fr = new Location (240, 240);
      }
      
      powerup.newPowerup(fr, dv);
      powerup.avail = true;
    }
  }
	private Data() {
    powerup = new Powerup(new Location(-40, -40), 1);
		map = new BasicBlock[13][13];
		int rowVal = 0;
        for (int row = 0; row < 13; row++) { // Defining the array map EMPTY.
          for (int col = 0; col < 13; col++) {
            map[row][col] = new SpaceBlock(new Location(-40, -40), new Image("player_left.png"));
          }
        }

   /* 
          HARDCODING THE WALLS.
          */
        
        for (int row = 1; row < 5; row ++){
          for (int col = 1; col < 5; col = col + 2) {
            map[row][col] = new BrickBlock(new Location(row*40, col*40), new Image("bricks.png"));
          }
        }

        for (int row = 1; row < 5; row ++){
          for (int col = 9; col < 13; col = col + 2) {
            map[row][col] = new BrickBlock(new Location(row*40, col*40), new Image("bricks.png"));
          }
        }

        for (int row = 9; row < 12; row ++){
          for (int col = 1; col < 5; col = col + 2) {
            map[row][col] = new BrickBlock(new Location(row*40, col*40), new Image("bricks.png"));
          }
        }

        for (int row = 9; row < 12; row ++){
          for (int col = 9; col < 13; col = col + 2) {
            map[row][col] = new BrickBlock(new Location(row*40, col*40), new Image("bricks.png"));
          }
        }

        for (int row = 1; row < 4; row ++){
          for (int col = 5; col < 9; col = col + 2) {
            map[row][col] = new BrickBlock(new Location(row*40, col*40), new Image("bricks.png"));
          }
        }

        for (int col = 2; col < 11; col++) {
            int row = 6;
            if (col < 4 || col > 8)
            map[row][col] = new BrickBlock(new Location(row*40, col*40), new Image("bricks.png"));
        }
        map[5][5] = new BrickBlock(new Location(5*40, 5*40), new Image("bricks.png"));
        map[5][7] = new BrickBlock(new Location(5*40, 7*40), new Image("bricks.png"));
        map[11][6] = new BrickBlock(new Location(11*40, 6*40), new Image("bricks.png"));
        map[11][5] = new BrickBlock(new Location(11*40, 5*40), new Image("bricks.png"));
        map[11][7] = new BrickBlock(new Location(11*40, 7*40), new Image("bricks.png"));
        map[9][7] = new BrickBlock(new Location(9*40, 7*40), new Image("bricks.png"));
        map[9][5] = new BrickBlock(new Location(9*40, 5*40), new Image("bricks.png"));
        map[8][7] = new BrickBlock(new Location(8*40, 7*40), new Image("bricks.png"));
        map[8][5] = new BrickBlock(new Location(8*40, 5*40), new Image("bricks.png"));
        map[12][5] = new BrickBlock(new Location(12*40, 5*40), new Image("bricks.png"));
        map[12][7] = new BrickBlock(new Location(12*40, 7*40), new Image("bricks.png"));
        eagle = new Eagle(new Location(12*40, 6*40));
        map[12][6] = eagle;
        map[3][6] = new SteelBlock(new Location(3*40, 6*40));
        map[6][12] = new SteelBlock(new Location(6*40, 12*40));
        map[6][0] = new SteelBlock(new Location(6*40, 0*40));



          /* 
          -------------------- END.
          */
        for (int row = 0; row < 13; row++) { // PRINT MAP.
      		for (int col = 0; col < 13; col++) {
      			map[row][col].iv = new ImageView(map[row][col].image);
      			map[row][col].iv.setX(map[row][col].location.getCol());
      			map[row][col].iv.setY(map[row][col].location.getRow());
      			map[row][col].iv.setFitHeight(40);
      			map[row][col].iv.setFitWidth(40);
      		}
      	}
        Location loc = new Location(0,0);
        Location loc1 = new Location(50,0);
        player = new Player(loc);
        player1 new Player(loc1);

        enem = new ArrayList<Enemy>();


        all.add(new ArrayList<Info>()); // bricks.
        all.add(new ArrayList<Info>()); // player
        all.add(new ArrayList<Info>()); // player.missile
        all.add(new ArrayList<Info>()); // enemy
        all.add(new ArrayList<Info>()); // enemy.missile
        all.add(new ArrayList<Info>()); // powerup

        Location loce = new Location(0,80);
        Enemy xy = new Enemy(loce);
        enem.add(xy);
	}
	public static Data getData() {
		if (aData == null) {
			aData = new Data(); 
		} 
		return aData;
	}
}