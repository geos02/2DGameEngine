package entity;


import controller.EntityController;
import core.*;
import gfx.AnimationManager;
import gfx.SpriteLibrary;
import state.State;

import java.awt.*;

public abstract class MovingEntity extends GameObject {

    protected EntityController entityController;
    protected Motion motion;
    
    protected AnimationManager animationManager;
    protected Direction direction;
    protected Vector2D directionVector;

    

    protected Size collisionBoxSize;

    public MovingEntity(EntityController entityController, SpriteLibrary spriteLibrary){
        super();
        this.entityController = entityController;
        this.motion = new Motion(2);
        this.direction = Direction.S;
        this.directionVector = new Vector2D(0, 0);
        this.animationManager = new AnimationManager(spriteLibrary.getSpriteSet("matt"));
        this.collisionBoxSize = new Size(size.getWidth(), size.getHeight());
    }

    @Override
    public void update(State state) {
        motion.update(entityController);
        handleMotion();

        animationManager.update(direction);

        handleCollisions(state);
        manageDirection();
        animationManager.playAnimation(decideAnimation());

        position.apply(motion);
    }
    
    protected abstract void handleMotion();


    private void handleCollisions(State state){
        state.getCollidingGameObjects(this)
                .forEach(this::handleCollisions);
    }

    protected abstract void handleCollisions(GameObject gameObject);


    protected abstract String decideAnimation();
    
   
    private void manageDirection() {
        if(motion.isMoving()){
            this.direction = Direction.fromMotion(motion);
            this.directionVector = motion.getDirection();
        }
        
    }

    @Override
    public CollisionBox getCollisionBox() {
        Position positionWithMotion = Position.copyOf(getPosition());
        positionWithMotion.apply(motion);
        positionWithMotion.subtract(collisionBoxOffset);
        
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

   

    public boolean willCollideX(GameObject other){
        CollisionBox otherBox = other.getCollisionBox();
        Position positionWithXApplied = Position.copyOf(position);
        positionWithXApplied.applyX(motion);
        positionWithXApplied.subtract(collisionBoxOffset);

        return CollisionBox.of(positionWithXApplied, collisionBoxSize).collidesWith(otherBox);
    }

    public boolean willCollideY(GameObject other){
        CollisionBox otherBox = other.getCollisionBox();
        Position positionWithYApplied = Position.copyOf(position);
        positionWithYApplied.applyY(motion);
        positionWithYApplied.subtract(collisionBoxOffset);

        return CollisionBox.of(positionWithYApplied, collisionBoxSize).collidesWith(otherBox);
    }
    
    public boolean isFacing(Position other) {
		Vector2D direction = Vector2D.directionBetweenPositions(other, getPosition());
		double dotProduct = Vector2D.dotProduct(direction, directionVector); 
		return dotProduct > 0;
	}
}
