package display;

import core.CollisionBox;
import entity.humanoid.Humanoid;
import entity.humanoid.effect.Sick;
import state.State;
import ui.UIText;

import java.awt.*;

public class DebugRenderer {

    public void render(State state, Graphics g){
        Camera camera = state.getCamera();
        state.getGameObjects().stream()
                .filter(gameObject -> camera.isInView(gameObject))
                .map(gameObject -> gameObject.getCollisionBox())
                .forEach(collisionBox -> drawCollisionBox(collisionBox, camera, g));
        
        drawSickHumanoids(state, g);
    }
    
    private void drawSickHumanoids(State state, Graphics g) {
    	
    	Camera camera = state.getCamera();
    	state.getGameObjectsOfClass(Humanoid.class).stream()
    			.filter(humanoid -> humanoid.isAffectedBy(Sick.class))
    			.forEach(humanoid -> {
    				
        			UIText effectsText = new UIText("Sick");
        			effectsText.update(state);
    				g.drawImage(
    					effectsText.getSprite(),
    					humanoid.getPosition().intX() - camera.getPosition().intX(),
    					humanoid.getPosition().intY() - camera.getPosition().intY(),
    					null
    				);
    					
    			});
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
