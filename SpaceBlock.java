import javafx.scene.image.*;

public class SpaceBlock extends BasicBlock {
	public SpaceBlock(Location l, Image i){
		super(l,i,BasicBlock.BlockType.SPACE);
		info = new Info(l,20, 2);		
	}
}