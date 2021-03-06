package state.menu;

import core.Size;
import game.settings.GameSettings;
import input.Input;
import map.GameMap;
import state.State;
import state.menu.ui.UIMainMenu;
import ui.UIContainer;

public class MenuState extends State {

	public MenuState(Size windowSize, Input input, GameSettings settings) {
		super(windowSize, input,settings);
		gameMap = new GameMap(new Size(20, 20), spriteLibrary);
		
		uiContainers.add(new UIMainMenu(windowSize));
		
		// Play Music
		audioPlayer.playMusic("isobubbler.wav");
	}

	public void enterMenu(UIContainer container) {
		uiContainers.clear();
		uiContainers.add(container);
	}
}
