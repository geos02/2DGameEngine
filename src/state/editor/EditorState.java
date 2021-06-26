package state.editor;

import core.Size;
import game.settings.GameSettings;
import input.Input;
import map.GameMap;
import state.State;
import state.editor.ui.UIButtonMenu;

public class EditorState extends State {

	public EditorState(Size windowSize, Input input, GameSettings settings) {
		super(windowSize, input, settings);
		
		gameMap = new GameMap(new Size(20, 20), spriteLibrary);
		
		uiContainers.add(new UIButtonMenu(windowSize));
		
	}

}
