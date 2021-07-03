package entity.humanoid.action;

import entity.humanoid.*;
import state.State;

public abstract class Action {

	protected boolean interruptable;
	
	public Action() {
		this.interruptable = true;
	}
	
    public abstract void update(State state, Humanoid entity);
    public abstract boolean isDone();
    public abstract String getAnimationName();
    public boolean isInterruptable() {
    	return interruptable;
    }
}
