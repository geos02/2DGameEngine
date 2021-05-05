package entity;


import controller.EntityController;
import core.*;
import entity.action.Action;
import entity.effect.Effect;
import game.state.State;
import gfx.AnimationManager;
import gfx.SpriteLibrary;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class MovingEntity extends GameObject {

    protected EntityController entityController;
    protected Motion motion;
    
    protected AnimationManager animationManager;
    protected Direction direction;

    protected List<Effect> effects;
    protected Optional<Action> action;

    private Size collisionBoxSize;

    public MovingEntity(EntityController entityController, SpriteLibrary spriteLibrary){
        super();
        this.entityController = entityController;
        this.motion = new Motion(2);
        this.direction = Direction.S;
        this.animationManager = new AnimationManager(spriteLibrary.getUnit("matt"));
        this.effects = new ArrayList<>();
        this.collisionBoxSize = new Size(16,28);
        this.action = Optional.empty();
    }

    @Override
    public void update(State state) {
        handleAction(state);
        handleMotion();

        animationManager.update(direction);
        effects.forEach(effect -> effect.update(state,this));

        handleCollisions(state);
        manageDirection();
        decideAnimation();

        position.apply(motion);

        cleanUp(); // clean effects
    }

    private void handleMotion() {
        if(action.isEmpty()){
            motion.update(entityController);
        }else {
            motion.stop(true,true);
        }
    }

    private void handleAction(State state){
        if(action.isPresent()){
            action.get().update(state,this);
        }
    }

    private void handleCollisions(State state){
        state.getCollidingGameObjects(this)
                .forEach(this::handleCollisions);
    }

    protected abstract void handleCollisions(GameObject gameObject);

    private void cleanUp() {
        List.copyOf(effects).stream()
                .filter(Effect::shouldDelete)
                .forEach(effects::remove);

        if(action.isPresent() && action.get().isDone()){
            action = Optional.empty();
        }
    }

    private void decideAnimation() {
        if(action.isPresent())
            animationManager.playAnimation(action.get().getAnimationName());
        else if(motion.isMoving())
            animationManager.playAnimation("walk");
        else
            animationManager.playAnimation("stand");
    }

    private void manageDirection() {
        if(motion.isMoving()){
            this.direction = Direction.fromMotion(motion);
        }
        
    }

    @Override
    public boolean collidesWith(GameObject other) {
        return getCollisionBox().collidesWith(other.getCollisionBox());
    }

    @Override
    public CollisionBox getCollisionBox() {
        Position positionWithMotion = Position.copyOf(position);
        positionWithMotion.apply(motion);
        return new CollisionBox(new Rectangle(
                positionWithMotion.intX(),
                positionWithMotion.intY(),
                collisionBoxSize.getWidth(),
                collisionBoxSize.getHeight()
        ));
    }

    @Override
    public Image getSprite() {
		return animationManager.getSprite();
    }

    public EntityController getController(){
        return entityController;
    }

    public void multiplySpeed(double multiplier){
        motion.multiply(multiplier);
    }

    public void perform(Action action) {
        this.action = Optional.of(action);
    }

    public void addEffect(Effect effect) {
        effects.add(effect);
    }

    public boolean isAffectedBy(Class<?> clazz) {
        return effects.stream()
                .anyMatch(effect -> clazz.isInstance(effect));
    }

    protected void clearEffect() {
        effects.clear();
    }

    public boolean willCollideX(GameObject other){
        CollisionBox otherBox = other.getCollisionBox();
        Position positionWithXApplied = Position.copyOf(position);
        positionWithXApplied.applyX(motion);

        return CollisionBox.of(positionWithXApplied, collisionBoxSize).collidesWith(otherBox);
    }

    public boolean willCollideY(GameObject other){
        CollisionBox otherBox = other.getCollisionBox();
        Position positionWithYApplied = Position.copyOf(position);
        positionWithYApplied.applyY(motion);

        return CollisionBox.of(positionWithYApplied, collisionBoxSize).collidesWith(otherBox);
    }
}
