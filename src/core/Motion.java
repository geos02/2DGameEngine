package core;

import controller.EntityController;

public class Motion {

    private Vector2D vector2D;
    private double speed;

    public Motion(double speed) {
        this.speed = speed;
        this.vector2D = new Vector2D(0,0);
    }

    public void update(EntityController entityController){

        int deltaX = 0;
        int deltaY = 0;

        if(entityController.isRequestingUp())
            deltaY--;
        if(entityController.isRequestingDown())
            deltaY++;
        if(entityController.isRequestingRight())
            deltaX++;
        if(entityController.isRequestingLeft())
            deltaX--;

        vector2D = new Vector2D(deltaX,deltaY);
        vector2D.normalize();
        vector2D.multiply(speed);
        //System.out.println(vector2D.length());
    }

    public Vector2D getVector2D() {
        return vector2D;
    }

    public boolean isMoving() {
        return vector2D.length() > 0;
    }

    public void multiply(double multiplier) {
        vector2D.multiply(multiplier);
    }

    public void stop(boolean stopX, boolean stopY) {
        vector2D = new Vector2D(
                stopX ? 0 : vector2D.getX(),
                stopY ? 0 : vector2D.getY());
    }

	public Vector2D getDirection() {
		Vector2D direction = Vector2D.copyOf(vector2D);
		direction.normalize();
		return direction;
	}

	public void add(Vector2D other) {
		this.vector2D.add(other);
	}
}
