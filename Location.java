public class Location {
	private int row, col;

	public Location(int r, int c){
		row = r;
		col = c;
	}

	public boolean equals(Location loc){
		return (row == loc.getRow() && col == loc.getCol());
	}

	public void setLocation(int r,int c){
		row = r;
		col = c;
	}

	public void setLocation(Location l){
		row = l.getRow();
		col = l.getCol();
	}

	public int findDistance(int r,int c){
		return 1;
	}

	public int getRow(){
		return row;
	}

	public int getCol(){
		return col;
	}

	public boolean collision(Location l) {
		if ((row - l.getRow() < 30) && (row -l.getRow() > -30)) {
			if ((col - l.getCol() < 30) && (col -l.getCol() > -30))
				return true;
		}
		return false;
	}

	public boolean m_collision(Location l) {
		if ((row - l.getRow() < 50) && (row -l.getRow() > -50)) {
			if ((col - l.getCol() < 50) && (col -l.getCol() > -50))
				return true;
		}
		return false;
	}


}
