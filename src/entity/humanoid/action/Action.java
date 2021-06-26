package entity.humanoid.action;

import entity.humanoid.*;
import state.State;

public abstract class Action {

    public abstract void update(State state, Humanoid entity);
    public abstract boolean isDone();
    public abstract String getAnimationName();
}
