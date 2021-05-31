package display;

import java.awt.*;

import core.Position;
import game.Game;
import map.GameMap;
import state.State;


public class Renderer {
	
	public void render(State state, Graphics g) {
		renderMap(state, g);
		renderGameObjects(state, g);
		renderUI(state, g);

	}

	private void renderUI(State state, Graphics g) {
		state.getUiContainers().forEach(uiContainer -> g.drawImage(
				uiContainer.getSprite(),
				uiContainer.getRelativePosition().intX(),
				uiContainer.getRelativePosition().intY(),
				null
		));
	}

	private void renderGameObjects(State state, Graphics g){
		Camera camera = state.getCamera();
		state.getGameObjects().stream()
				.filter(gameObject -> camera.isInView(gameObject))
				.forEach(gameObject -> g.drawImage(
				gameObject.getSprite(),
				gameObject.getRenderPosition(camera).intX(),
				gameObject.getRenderPosition(camera).intY(),
				null
		));
	}

	private void renderMap(State state, Graphics g) {
		GameMap map = state.getGameMap();
		Camera camera = state.getCamera();

		Position start = map.viewableStartingGridPosition(camera);
		Position end = map.viewableEndingGridPosition(camera);

		for(int x=start.intX(); x < end.intX(); x++) {
			for(int y=start.intY(); y < end.intY(); y++) {
				g.drawImage(
							map.getTiles()[x][y].getSprite(),
						    x * Game.SPRITE_SIZE - camera.getPosition().intX(),
						    y * Game.SPRITE_SIZE - camera.getPosition().intY(),
						    null
				);
			}
		}
		
	}
}