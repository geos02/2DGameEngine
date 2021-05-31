package display;

import java.awt.*;
import java.util.Optional;

import core.Position;
import core.Size;
import entity.GameObject;
import game.Game;
import map.GameMap;
import state.State;

public class Camera {

	private static final int SAFETY_SPACE = 2 * Game.SPRITE_SIZE;

	private Position position;
	private Size windowSize;

	private Rectangle viewBounds;
	
	private Optional<GameObject> objectWithFocus;
	
	public Camera(Size size) {
		
		this.position = new Position(0,0);
		this.windowSize = size;
		calculateViewBounds();
	}

	private void calculateViewBounds() {
		viewBounds = new Rectangle(position.intX(),
				position.intY(),
				windowSize.getWidth() + SAFETY_SPACE,
				windowSize.getHeight() + SAFETY_SPACE);
	}

	public void focusOn(GameObject gameObject) {
		this.objectWithFocus = Optional.of(gameObject);
	}
	
	public void update(State state) {
		if(objectWithFocus.isPresent()) {
			Position objectPosition = objectWithFocus.get().getPosition();
			
			this.position.setX(objectPosition.getX() - windowSize.getWidth() / 2);
			this.position.setY(objectPosition.getY() - windowSize.getHeight() / 2);
			
			clampCamera(state);
			calculateViewBounds();
		}
	}
	
	private void clampCamera(State state) {
		if(position.getX() < 0)
			position.setX(0);
		
		if(position.getY() < 0)
			position.setY(0);
		
		if(position.getX() + windowSize.getWidth()  > state.getGameMap().getWidth())
			position.setX(state.getGameMap().getWidth() - windowSize.getWidth());
		
		if(position.getY() + windowSize.getHeight() > state.getGameMap().getHeight())
			position.setY(state.getGameMap().getHeight() - windowSize.getHeight());
	}
	
	public Position getPosition() {
		return position;
	}

    public boolean isInView(GameObject gameObject) {
		return viewBounds.intersects(
				gameObject.getPosition().getX(),
				gameObject.getPosition().getY(),
				gameObject.getSize().getWidth(),
				gameObject.getSize().getHeight()
		);
    }

	public Size getSize() {
		return windowSize;
	}
}
