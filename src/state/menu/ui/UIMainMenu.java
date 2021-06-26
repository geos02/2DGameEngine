package state.menu.ui;


import java.awt.Color;

import core.Size;
import game.settings.GameSettings;
import state.editor.EditorState;
import state.game.GameState;
import state.menu.MenuState;
import ui.Alignment;
import ui.Spacing;
import ui.UIText;
import ui.VerticalContainer;
import ui.clickable.UIButton;

public class UIMainMenu extends VerticalContainer {

	public UIMainMenu(Size windowSize) {
		super(windowSize);
		alignment = new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER);
		addUIComponent(new UIText("ISOBUBBLER"));
		addUIComponent(new UIButton("Play", (state) -> state.setNextState(new GameState(windowSize, state.getInput(),state.getGameSettings()))));
		addUIComponent(new UIButton("Editor", (state) -> state.setNextState(new EditorState(windowSize, state.getInput(),state.getGameSettings()))));
		addUIComponent(new UIButton("Options", (state) -> ((MenuState) state).enterMenu(new UIOptionMenu(windowSize))));
		addUIComponent(new UIButton("Exit", (state) -> System.exit(0)));
		
		// Padding and Margin
		setBackgroundColor(Color.DARK_GRAY);
		setPadding(new Spacing(10));
		children.forEach(button -> button.setMargin(new Spacing(10,5)));
	}

}
 