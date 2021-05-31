package display;

import core.CollisionBox;
import game.GameLoop;
import state.State;

import java.awt.*;

public class DebugRenderer {

    public void render(State state, Graphics g){
        Camera camera = state.getCamera();
        state.getGameObjects().stream()
                .filter(gameObject -> camera.isInView(gameObject))
                .map(gameObject -> gameObject.getCollisionBox())
                .forEach(collisionBox -> drawCollisionBox(collisionBox, camera, g));
    }

    private void drawCollisionBox(CollisionBox collisionBox, Camera camera, Graphics g) {
        g.setColor(Color.GREEN);
        g.drawRect(
                (int) collisionBox.getBounds().getX() - camera.getPosition().intX(),
                (int) collisionBox.getBounds().getY() - camera.getPosition().intY(),
                (int) collisionBox.getBounds().getWidth(),
                (int) collisionBox.getBounds().getHeight()
        );
    }
}
