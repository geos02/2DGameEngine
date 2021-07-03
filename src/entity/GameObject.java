package entity;
import java.awt.Image;

import core.CollisionBox;
import core.Position;
import core.Size;
import display.Camera;
import state.State;

public abstract class GameObject {

	protected Position position;
	protected Position renderOffset;
	protected Position collisionBoxOffset;
	protected Size size;
	protected int renderOrder;
	
	protected GameObject parent;

	public GameObject() {
		position = new Position(0, 0);
		renderOffset = new Position(0,0);
		collisionBoxOffset = new Position(0,0);
		size = new Size(64, 64);
		renderOrder = 5;
	}
	
	public abstract void update(State state);
	public abstract Image getSprite();
	public abstract CollisionBox getCollisionBox();

	public boolean collidesWith(GameObject other){
		return getCollisionBox().collidesWith(other.getCollisionBox());
	}
	
	public Position getPosition() {
		Position finalPosition = Position.copyOf(position);

		if(parent != null){
			finalPosition.add(parent.getPosition());
		}

		return finalPosition;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}
	
	public Size getSize() {
		return size;
	}

	public void setParent(GameObject parent) {
		this.position = new Position(0, 0);
		this.parent = parent;
	}
	
	public Position getRenderPosition(Camera camera) {
		return new Position(
				getPosition().getX() - camera.getPosition().getX() - renderOffset.getX(),
				getPosition().getY() - camera.getPosition().getY() - renderOffset.getY());
	}
	
	public void setRenderOrder(int renderOrder) {
		this.renderOrder = renderOrder;
	}
	
	public int getRenderOrder() {
		return renderOrder;
	}
	
	protected void clearParent() {
		parent = null;
	}

	protected Position getRenderOffset() {
		
		return renderOffset;
	}
}
