import javafx.scene.image.*;

public class BasicBlock {
	
	Location location;
	Image image;
	ImageView iv;
	int type;
	public class BlockType {
		static final int SPACE = 1;
		static final int TANK = 2;
		static final int BRICK = 3;
		static final int MISSILE = 4;
		static final int EAGLE = 5;
		static final int STEEL = 6;
		static final int POWERUP = 7;
	}

	BasicBlock(Location l, Image im){
		location = l;
		image = im;
	}

	BasicBlock(Location l, Image im, int i){
		location = l;
		image = im;
		type = i;
	}
	BasicBlock(Location l, int i){
		location = l;
		type = i;
	}

	/* Template method. Each frame before rendering will invoke this method for 
	 * all objects contained on the canvas. They are to update their location, image
	 * and perhaps even blockType (if blockType change is required).
	 * All subclasses should override this method unless they are not expected to have
	 * any change over their lifetime
	 */
	public void doAction(){
		
		
	} 
	
	/* If another object (eg missile) moves into the space of this object (during doAction), 
	 * the missile will invoke method impact of this object passing itself. Using pre-defined
	 * rules you are to determine the outcome
	 */
	public void impact(int f){

	}


	protected boolean movePossible(){
                //evaluate move
                return false;
        }

	protected void makeMove(){
	        //make the move and make necessary updates
	}

}
