package game;

import controller.GameController;
import core.Size;
import display.Display;

import game.settings.GameSettings;
import input.Input;
import state.State;
import state.menu.MenuState;

public class Game {

	public static final int SPRITE_SIZE = 64;
	
	private Display display;
	private Input input;
	
	private State state;
	private GameSettings settings;
	private GameController gameController;
	
	public Game(int width, int height) {
		input = new Input(this);
		display = new Display(width, height,input);
		state = new MenuState(new Size(width,height),input);
		gameController = new GameController(input);

		settings = new GameSettings();
	}

	
	public void update() {
		state.update(this);
		gameController.update(this);
	}
	
	public void render() {
		display.render(state, settings.isDebugMode());
	}

	public GameSettings getSettings() {
		return settings;
	}


	public void enterState(State nextState) {
		
		state = nextState;
	}
}
