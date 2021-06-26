package entity.humanoid.action;

import entity.humanoid.Humanoid;
import state.State;

public class Leviate extends Action {

	@Override
	public void update(State state, Humanoid entity) {
	}

	@Override
	public boolean isDone() {
		
		return false;
	}

	@Override
	public String getAnimationName() {
		
		return "levitate";
	}

}
