import javafx.scene.image.*;
import java.util.ArrayList;
public class Tank extends BasicBlock{
	int play = 0;
	boolean killed = false;
	Missile missile;
	int direction = 2;
	 int prevdir = 0;
	 int dele = 0;
	Tank(Location l,Image im){
		super(l,im,BasicBlock.BlockType.TANK);
	}
	Tank(Location l){
		super(l,BasicBlock.BlockType.TANK);
		info = new Info(l,play, direction);

	}

	public boolean movePossible(){
	  int rt = 0;
	  int ct = 0;
	  if (direction == 1){
	    rt = location.getRow()-2; 
	    ct = location.getCol();
	  } else if (direction == -1){
	    rt = location.getRow()+2; 
	    ct = location.getCol();
	  } else if (direction == 0){
	    rt = location.getRow(); 
	    ct = location.getCol()-2;
	  } else if (direction == 2){
	    rt = location.getRow(); 
	    ct = location.getCol()+2;
	  }
	  Location newLoc = new Location(rt, ct);
	  BasicBlock[][] map = Data.getData().map;
	  if (rt < 0 || ct < 0 || rt > 485 || ct > 485 ||map[rt/40][ct/40].location.getCol() != -40 || map[(rt+30)/40][ct/40].location.getCol() != -40 || map[rt/40][(ct + 30)/40].location.getCol() != -40 || map[(rt+30)/40][(ct+30)/40].location.getCol() != -40) {
	    return false;
	  }
	  Player player = Data.getData().player;
	  if (play != 1 &&  (newLoc.collision(player.location)))
	 	return false;
	 if (Data.getData().powerup.avail && play == 1) {
	 	if (iv.intersects(Data.getData().powerup.iv.getBoundsInLocal())) {
	 				System.out.println("sds");
	 		 		Data.getData().powerup.impact(0);
	 		 		Data.getData().powerup.avail = false;
	 		 		Data.getData().powerup.on = true;
	 		 		if (Data.getData().powerup.p_type == 1) {
		 		 		Data.getData().powerup.on = false;
		 		 	}
	 		 		Data.getData().powerup.newPowerup(new Location(-40,-40), 0);
	 		 	}
	 }
	 ArrayList<Enemy> enem = Data.getData().enem;
	 for (Enemy dv : enem) {
	 	if (!(location.equals(dv.location)) &&  (newLoc.collision(dv.location)))
	 	return false;
	 }
	 return true;
	}

	public void makeMove(){
		int rt = 0;
		int ct = 0;
      if (direction == 1){
      	System.out.println("abc1");
	    rt = location.getRow()-2; 
	    ct = location.getCol();
	  } else if (direction == -1){
	  	System.out.println("abc-1");
	    rt = location.getRow()+2; 
	    ct = location.getCol();
	  } else if (direction == 0){
	  	System.out.println("abc0");
	    rt = location.getRow(); 
	    ct = location.getCol()-2;
	  } else if (direction == 2){
	  	System.out.println("abc2");
	    rt = location.getRow(); 
	    ct = location.getCol()+2;
	  }
	  location.setLocation(rt, ct);
	  iv.setX(ct);
	  iv.setY(rt);
	  info.setinfo(ct,rt);
   }
}