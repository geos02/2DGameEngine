package core;

public class Vector2D {

    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double length(){
        return Math.sqrt(x * x + y * y);
    }

    public void normalize(){
        double length = length();
        x = x == 0 ? 0 : x / length;
        y = y == 0 ? 0 : y / length;
    }

    public void multiply(double speed) {
        x *= speed;
        y *= speed;
    }
    
    public static Vector2D copyOf(Vector2D vector) {
    	return new Vector2D(vector.getX(),vector.getY());
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
