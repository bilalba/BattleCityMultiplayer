import javafx.scene.image.*;
import java.util.ArrayList;
public class Missile extends BasicBlock {
	int direction = 2;
	boolean ml = false;
	int m_type = 0;
	public Missile(Location l, Image im){
		super(l,im,BasicBlock.BlockType.MISSILE);
	}
	public Missile(Location l, int typ) {
		super(l, BasicBlock.BlockType.MISSILE);
		m_type = typ;
		image = new Image("abc.png");
		iv = new ImageView(image);
		iv.setX(-40);
    	iv.setY(-40);
    	iv.setFitHeight(5);
    	iv.setFitWidth(4);
	}

	protected boolean movePossible() {
		int rt = 0;
	  int ct = 0;
	  if (direction == 1){
	    rt = location.getRow()-5; 
	    ct = location.getCol();
	  } else if (direction == -1){
	    rt = location.getRow()+5; 
	    ct = location.getCol();
	  } else if (direction == 0){
	    rt = location.getRow(); 
	    ct = location.getCol()-5;
	  } else if (direction == 2){
	    rt = location.getRow(); 
	    ct = location.getCol()+5;
	  }
	  
	  Location newLoc = new Location(rt, ct);
	  BasicBlock[][] map = Data.getData().map;
	  if (rt < 0 || ct < 0 || rt > 505 || ct > 505 ||map[rt/40][ct/40].location.getCol() != -40 || map[(rt+10)/40][ct/40].location.getCol() != -40 || map[rt/40][(ct + 10)/40].location.getCol() != -40 || map[(rt+10)/40][(ct+10)/40].location.getCol() != -40) {
	  	if (rt < 0 || ct < 0 || rt > 505 || ct > 505) {

	  	} else {
	  	if (map[rt/40][ct/40].location.getCol() != -40) {
	  		System.out.println("SSS");
	  		if (map[rt/40][ct/40].type == 3){
	  			  		BrickBlock fd = (BrickBlock) map[rt/40][ct/40];
	  			  		fd.impact(0);
	  			  	} else if (map[rt/40][ct/40].type == 5) {
	  			  		Eagle fd = (Eagle) map[rt/40][ct/40];
	  			  		fd.impact(0);
	  			  	}
	  	} else if (map[(rt+10)/40][ct/40].location.getCol() != -40) {
	  		System.out.println("SSS");if (map[rt/40][ct/40].type == 3){
	  			  		BrickBlock fd = (BrickBlock) map[(rt+10)/40][ct/40];
	  			  		fd.impact(0);
	  			  	} else if (map[(rt+10)/40][ct/40].type == 5){
	  			  		Eagle fd = (Eagle) map[(rt+10)/40][ct/40];
	  			  		fd.impact(0);
	  			  	}
	  	} else if (map[rt/40][(ct+10)/40].location.getCol() != -40) {
	  		System.out.println("SSS");
	 	 		if (map[rt/40][(ct+10)/40].type == 3){
	  			  		BrickBlock fd = (BrickBlock) map[rt/40][(ct+10)/40];
	  			  		fd.impact(0);
	  			  	} else if (map[rt/40][(ct+10)/40].type == 5){
	  			  		Eagle fd = (Eagle) map[rt/40][(ct+10)/40];
	  			  		fd.impact(0);
	  			  	}
	  		
	  	} else if (map[(rt+10)/40][(ct+10)/40].location.getCol() != -40) {
	  		System.out.println("SSS");
	  		if (map[(rt+10)/40][(ct+10)/40].type == 3){
	  			  		BrickBlock fd = (BrickBlock) map[(rt+10)/40][(ct+10)/40];
	  			  		fd.impact(0);
	  			  	} else if (map[(rt+10)/40][(ct+10)/40].type == 5){
	  			  		Eagle fd = (Eagle) map[(rt+10)/40][(ct+10)/40];
	  			  		fd.impact(0);
	  			  	}
	
	  	}
	  }
	    return false;
	  }
	  Player player = Data.getData().player;
	  if (iv.intersects(player.iv.getBoundsInLocal())){
	  	player.impact(2);
	  	 	return false;
	  	 }
	 ArrayList<Enemy> enem = Data.getData().enem;
	 for (int tv = 0; tv < enem.size(); tv++) {
	 	Enemy dv = enem.get(tv);
	 	if (iv.intersects(dv.iv.getBoundsInLocal())){
	 		if (m_type == 1) {
	 			dv.impact(1);
	 		}
	 		return false;
	 	}
	 }

		return true;
	}

	public void makeMove() {
		if (direction == 1)
			location.setLocation(location.getRow()-5,location.getCol());
		if (direction == -1)
			location.setLocation(location.getRow()+5,location.getCol());
		if (direction == 0)
			location.setLocation(location.getRow(),location.getCol()-5);
		if (direction == 2)
			location.setLocation(location.getRow(),location.getCol()+5);

		iv.setX(location.getCol());
        iv.setY(location.getRow());
	}
	public void doAction(){
		if (ml) {
			if (movePossible()) {
				makeMove();
			} else {
				System.out.println("Missilfe");
				ml = false;

				iv.setX(-40);
     		    iv.setY(-40);
			}
			
		}
	}
} 
