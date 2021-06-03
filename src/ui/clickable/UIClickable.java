package ui.clickable;


import java.awt.Rectangle;

import core.Position;
import state.State;
import ui.UIComponent;

public abstract class UIClickable extends UIComponent {

	protected boolean hasFocus;
	protected boolean isPressed;

	@Override
	public void update(State state) {
		
		Position mousePosition = state.getInput().getMousePosition();
		hasFocus = getBounds().contains(mousePosition.intX(), mousePosition.intY());
		isPressed = hasFocus && state.getInput().isMousePressed();
		
		if(hasFocus && state.getInput().isMouseClicked()) {
			onClick(state);
		}
	}

	protected abstract void onClick(State state);

	private Rectangle getBounds() {
		
		return new Rectangle(
				absolutePosition.intX(),
				absolutePosition.intY(),
				size.getWidth(),
				size.getHeight()
		);
	}
}
