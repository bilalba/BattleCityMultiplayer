import javafx.scene.image.*;
import java.util.ArrayList;

public class Powerup extends BasicBlock {
	int p_type;
	boolean on = false;
	boolean avail = false;
	public Powerup(Location l,int t) {
		super(l, BasicBlock.BlockType.POWERUP);
		p_type = t;
		if (p_type == 1){
			image = new Image("steel.png"); // change this! GRENDADE
		} else if (p_type == 2){
			image = new Image("steel.png"); // change this!
		} else if (p_type == 3){
			image = new Image("steel.png"); // change this!
		}
		iv = new ImageView(image);
		iv.setX(l.getCol());
    	iv.setY(l.getRow());
    	iv.setFitHeight(40);
    	iv.setFitWidth(40);
	}
	public void newPowerup(Location l,int t) {
		p_type = t;
		location.setLocation(l);
		if (p_type == 1){
			image = new Image("grenade.png"); // change this! GRENDADE
		} else if (p_type == 2){
			image = new Image("helmet1.png"); // change this!
		} else if (p_type == 3){
			image = new Image("timer.png"); // change this!
		}
		iv.setImage(image);
		iv.setX(l.getCol());
    	iv.setY(l.getRow());
    	iv.setFitHeight(40);
    	iv.setFitWidth(40);
	}

	public void impact(int f) {
		if (p_type == 1){
			ArrayList<Enemy> enem = Data.getData().enem;
			for (int x = 0; x < enem.size(); x++) {
				enem.get(x).impact(0);
			}
		} else if (p_type == 2) {
			Data.getData().player.helmet = true;
			Data.getData().player.iv.setImage(new Image("helmet.png"));
		} else if (p_type == 3) {
			Data.getData().timefreeze(1);
		}
	}
}