import javafx.scene.image.*;

public class SteelBlock extends BasicBlock {
	public SteelBlock(Location l){
		super(l,BasicBlock.BlockType.STEEL);
		info = new Info(l,17, 2);		
		image = new Image("steel.png");
		iv = new ImageView(image);
    	iv.setX(500);
    	iv.setY(500);
    	iv.setFitHeight(40);
    	iv.setFitWidth(40);
	}
}