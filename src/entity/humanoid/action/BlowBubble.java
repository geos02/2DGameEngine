package entity.humanoid.action;

import controller.NPCController;
import entity.Bubble;
import entity.humanoid.Humanoid;
import game.Game;
import game.GameLoop;
import state.State;

public class BlowBubble extends Action {
	
	private int lifeSpanInUpdates;
	private Humanoid target;
	private Bubble bubble;
	
	public BlowBubble(Humanoid target) {
		this.lifeSpanInUpdates = GameLoop.UPDATES_PER_SECOND * 1;
		this.target = target;
	}

	@Override
	public void update(State state, Humanoid entity) {
		lifeSpanInUpdates--;
		
		if(bubble == null) {
			bubbleTarget(state);
		}
	}

	private void bubbleTarget(State state) {
		target.perform(new Leviate());
		bubble = new Bubble(new NPCController(), state.getSpriteLibrary());
		bubble.insert(target);
		state.spawn(bubble);
	}

	@Override
	public boolean isDone() {
		
		return lifeSpanInUpdates <= 0;
	}

	@Override
	public String getAnimationName() {
		
		return "blow";
	}

}
