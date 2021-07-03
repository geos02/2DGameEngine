package entity.humanoid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import controller.EntityController;
import core.Position;
import core.Size;
import entity.GameObject;
import entity.MovingEntity;
import entity.humanoid.action.Action;
import entity.humanoid.effect.Effect;
import gfx.SpriteLibrary;
import state.State;

public class Humanoid extends MovingEntity {
	
	protected List<Effect> effects;
    protected Optional<Action> action;

	public Humanoid(EntityController entityController, SpriteLibrary spriteLibrary) {
		super(entityController, spriteLibrary);
		
		this.effects = new ArrayList<>();
		this.action = Optional.empty();
		
		this.collisionBoxSize = new Size(16,28);
	    this.renderOffset = new Position(size.getWidth() / 2, size.getHeight() - 12);
	    this.collisionBoxOffset = new Position(collisionBoxSize.getWidth() / 2, collisionBoxSize.getHeight());
		
	}
	
	@Override
    public void update(State state) {
		super.update(state);
        handleAction(state);
        effects.forEach(effect -> effect.update(state,this));

        cleanUp(); // clean effects
    }
	
	protected void handleMotion() {
        if(action.isPresent())
            motion.stop(true,true);
    }
	
	private void handleAction(State state){
        if(action.isPresent()){
            action.get().update(state,this);
        }
    }
	
	@Override
	protected String decideAnimation() {
       if(action.isPresent())
            return action.get().getAnimationName();
       else if(motion.isMoving())
            return "walk";
       
       return "stand";
	}
	
	 private void cleanUp() {
        List.copyOf(effects).stream()
                .filter(Effect::shouldDelete)
                .forEach(effects::remove);

        if(action.isPresent() && action.get().isDone()){
            action = Optional.empty();
        }
    }
	 
	 public void perform(Action action) {
		// Evita que se ejecute una accion si esta no es interrumplible
		if(this.action.isPresent() && !this.action.get().isInterruptable())
			return;
		
        this.action = Optional.of(action);
    }

    public void addEffect(Effect effect) {
        effects.add(effect);
    }

    public boolean isAffectedBy(Class<?> clazz) {
        return effects.stream()
                .anyMatch(effect -> clazz.isInstance(effect));
    }

    public void clearEffect() {
        effects.clear();
    }

	@Override
	protected void handleCollisions(GameObject gameObject) {
		
	}

	public List<Effect> getEffects() {
		
		return effects;
	}

}
