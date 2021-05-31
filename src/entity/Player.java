package entity;

import controller.EntityController;
import core.Position;
import gfx.SpriteLibrary;


public class Player extends MovingEntity {
	
	public Player(EntityController entityController, SpriteLibrary spriteLibrary) {
		super(entityController, spriteLibrary);
		setPosition(new Position(50, 50));
		//effects.add(new Caffeinated());
	}

	@Override
	protected void handleCollisions(GameObject gameObject) {
		if(gameObject instanceof NPC){
			NPC other = (NPC) gameObject;
			other.clearEffect();
		}
	}

}
