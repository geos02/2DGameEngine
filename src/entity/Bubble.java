package entity;

import controller.NPCController;
import core.Direction;
import core.Vector2D;
import gfx.AnimationManager;
import gfx.SpriteLibrary;
import gfx.SpriteSet;

public class Bubble extends MovingEntity {

	private boolean halted;
	
	public Bubble(NPCController npcController, SpriteLibrary spriteLibrary) {
		super(npcController, spriteLibrary);
		
		this.animationManager = new AnimationManager(new SpriteSet(spriteLibrary.getImage("bubble")), false);
		
	}

	@Override
	protected void handleMotion() {
		if(!halted) {
			motion.add(new Vector2D(0, -0.5));
		}
		
		halted = false;
		this.direction = Direction.S; // porque S es = 0.
	}

	@Override
	protected void handleCollisions(GameObject gameObject) {
	}

	@Override
	protected String decideAnimation() {
		
		return "default";
	}

	public void insert(GameObject gameObject) {
		this.position = gameObject.getPosition();
		this.renderOffset = gameObject.getRenderOffset();
		this.collisionBoxOffset = renderOffset;
		gameObject.setParent(this);
	}
	
	public void halt() {
		halted = true; 
	}
}
