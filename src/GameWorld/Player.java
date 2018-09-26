package GameWorld;

import java.util.List;

public class Player {
	private int currentMap;
	private Position position;
	private List<Item> inventory;
	private Direction direction;
	private static Game game;
	
	public Player(int startMap, Position position) {
		this.currentMap = startMap;
		this.position = position;
		this.direction = Direction.NORTH;
	}
	public enum Direction{
		NORTH,
		EAST,
		SOUTH,
		WEST;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public int currentMapInteger() {
		return currentMap;
	}
	
	public Position getPosition(){
		return position;
	}
	
	public List<Item> getInventory(){
		return inventory;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public boolean pickup() {
		if (this.position.containsItem()){
			this.inventory.add(position.getItem());
			return true;
		}
		else return false;
	}
	/**
	 * 
	 * @param dir the direction to check if the move is valid in
	 * @return true if movement is allowed
	 */
	public boolean moveValid(Direction dir) {
		return game.isAccessible(this.requestPosition(dir));
	}
	
	/**
	 * 
	 * @param dir direction in which we want to move the player
	 * @return true if movement was successful
	 */
	public boolean movePlayer(Direction dir) {
		if (moveValid(dir)) {
			this.position = this.requestPosition(dir);
			return true;
		}
		else return false;
	}
	/**
	 * Returns the position in the requested direction of the player
	 * 
	 * @param dir - direction you need the new position relative to the player's current position to be in
	 */
	public Position requestPosition(Direction dir) {
		if (dir == null) return null;
		else if (dir == Direction.NORTH) {
			return new Position(this.position.getx(), this.position.gety() + 1);
		}
		else if (dir == Direction.EAST) {
			return new Position(this.position.getx() + 1, this.position.gety());
		}
		else if (dir == Direction.SOUTH) {
			return new Position(this.position.getx(), this.position.gety() - 1);
		}
		//must be west
		else {
			return new Position(this.position.getx() - 1, this.position.gety());
		}
	}
}
