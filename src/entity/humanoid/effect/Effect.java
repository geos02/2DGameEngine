package entity.humanoid.effect;

import entity.humanoid.*;
import state.State;

public abstract class Effect {

    private int lifeSpanInUpdates;

    public Effect(int lifeSpanInUpdates) {
        this.lifeSpanInUpdates = lifeSpanInUpdates;
    }

    public void update(State state, Humanoid entity){
        lifeSpanInUpdates--;
    }

    public boolean shouldDelete(){
        return lifeSpanInUpdates <= 0;
    }
}
