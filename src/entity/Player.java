package entity;

import java.util.Comparator;
import java.util.Optional;

import controller.EntityController;
import core.Position;
import entity.humanoid.Humanoid;
import entity.humanoid.action.BlowBubble;
import game.Game;
import gfx.SpriteLibrary;
import state.State;


public class Player extends Humanoid {
	
	private NPC target;
	private double targetRange;
	private SelectionCircle selectionCircle;
	
	public Player(EntityController entityController, SpriteLibrary spriteLibrary, SelectionCircle selectionCircle) {
		super(entityController, spriteLibrary);
		setPosition(new Position(50, 50));
		this.selectionCircle = selectionCircle;
		this.targetRange = Game.SPRITE_SIZE;
		//effects.add(new Caffeinated());
	}
	
	@Override
	public void update(State state) {
		
		super.update(state);
		handleTarget(state);
		
		handleInput(state);
	}

	private void handleTarget(State state) {
		
		Optional<NPC> closestNPC = findClosestNPC(state);
		
		if(closestNPC.isPresent()) {
			NPC npc = closestNPC.get();
			if(!npc.equals(target)) {
				selectionCircle.setParent(npc);
				target = npc;
			}
		} else {
			selectionCircle.clearParent();
			target = null;
		}
	}
	
	private void handleInput(State state) {
		if(entityController.isRequestingAction()) {
			if(target != null) {
				perform(new BlowBubble(target));
			}
		}
	}

	private Optional<NPC> findClosestNPC(State state) {
		
		return state.getGameObjectsOfClass(NPC.class).stream()
				.filter(npc -> getPosition().distanceTo(npc.getPosition()) < targetRange)
				.filter(npc -> isFacing(npc.getPosition()))
				.min(Comparator.comparingDouble(npc -> position.distanceTo(npc.getPosition())));
	}

	

	@Override
	protected void handleCollisions(GameObject gameObject) {
		
	}

}
