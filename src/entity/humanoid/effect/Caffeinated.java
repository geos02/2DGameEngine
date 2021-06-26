package entity.humanoid.effect;

import entity.humanoid.*;
import game.GameLoop;
import state.State;

public class Caffeinated extends Effect {

    private double speedMultiplier;

    public Caffeinated() {
        super(GameLoop.UPDATES_PER_SECOND * 5);  // 5 seconds
        speedMultiplier = 2.5;
    }

    @Override
    public void update(State state, Humanoid entity) {
        super.update(state, entity);

        entity.multiplySpeed(speedMultiplier);
    }
}
