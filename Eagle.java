import javafx.scene.image.*;

public class Eagle extends BasicBlock{
	boolean killed = false;
	public Eagle(Location l){
		super(l,BasicBlock.BlockType.EAGLE);
		info = new Info(l,16, 2);
		image = new Image("eagless.png");
		iv = new ImageView(image);
    	iv.setX(500);
    	iv.setY(500);
    	iv.setFitHeight(40);
    	iv.setFitWidth(40);
	}

	public void impact(int f) {
		killed = true;
	}
}