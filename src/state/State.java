package state;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import audio.AudioPlayer;
import core.Position;
import core.Size;
import display.Camera;
import entity.GameObject;
import game.Game;
import game.Time;
import gfx.SpriteLibrary;
import input.Input;
import map.GameMap;
import ui.UIContainer;

public abstract class State {

	protected GameMap gameMap;
	protected SpriteLibrary spriteLibrary;
	protected Input input;
	protected Camera camera;
	protected Time time;
	protected AudioPlayer audioPlayer;
	
	protected List<GameObject> gameObjects;
	protected List<UIContainer> uiContainers;

	private State nextState;
	
	public State(Size windowSize, Input input) {
		this.audioPlayer = new AudioPlayer();
		this.time = new Time();
		this.input = input;

		gameObjects = new ArrayList<>();
		uiContainers = new ArrayList<>();
		spriteLibrary = new SpriteLibrary();

		camera = new Camera(windowSize);
		
		

	}
	
	public void update(Game game) {
		time.update();
		sortObjectsByPosition();
		gameObjects.forEach(gameObject -> gameObject.update(this));
		List.copyOf(uiContainers).forEach(uiContainer -> uiContainer.update(this));
		camera.update(this);
		handleMouseInput();
		
		if(nextState != null) {
			game.enterState(nextState);
		}
	}
	
	private void handleMouseInput() {
		
		if(input.isMouseClicked())
			System.out.println(String.format("MOUSE CLICKED AT POSITION x:%d y:%d", 
											 input.getMousePosition().intX(),
											 input.getMousePosition().intY()));
		
		input.clearMouseClicked();
	}
	
	private void sortObjectsByPosition() {
		
		gameObjects.sort(Comparator.comparing(GameObject::getRenderOrder)
						 .thenComparing( gameObject -> gameObject.getPosition().getY() ));
	}
	
	public List<GameObject> getGameObjects(){
		return gameObjects;
	}

	public List<UIContainer> getUiContainers(){return uiContainers;}
	
	public GameMap getGameMap() {
		return gameMap;
	}
	
	public Camera getCamera() {
		return camera;
	}

	public Time getTime() {
		return time;
	}

	public Position getRandomPosition() {
		return gameMap.getRandomPosition();
	}

    public List<GameObject> getCollidingGameObjects(GameObject gameObject) {
		return gameObjects.stream()
				.filter(other -> other.collidesWith(gameObject))
				.collect(Collectors.toList());
    }

    public <T extends GameObject> List<T> getGameObjectsOfClass(Class<T> clazz){
		return gameObjects.stream()
				.filter(gameObject -> clazz.isInstance(gameObject))
				.map(gameObject -> (T) gameObject)
				.collect(Collectors.toList());
	}
    
    public Input getInput() {
    	return input;
    }

	public void setNextState(State nextState) {
		this.nextState = nextState;
	}
    
    
}
