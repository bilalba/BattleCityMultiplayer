import javafx.scene.image.*;

public class BrickBlock extends BasicBlock {
	public BrickBlock(Location l, Image im){
		super(l,im,BasicBlock.BlockType.BRICK);
	}

	public void impact(int f){
		System.out.println('s');
		location.setLocation(-40,-40);
		iv.setX(-40);
     	iv.setY(-40);
	}
}