package entity.humanoid.effect;

import entity.humanoid.*;
import entity.humanoid.action.Cough;
import game.GameLoop;
import state.State;

public class Sick extends Effect {

    private static final double COUGH_RATE = 1d / GameLoop.UPDATES_PER_SECOND / 10;

    public Sick() {
        super(Integer.MAX_VALUE);
    }

    @Override
    public void update(State state, Humanoid entity) {
        super.update(state, entity);

        if(Math.random() < COUGH_RATE){
            entity.perform(new Cough());
        }
    }
}
