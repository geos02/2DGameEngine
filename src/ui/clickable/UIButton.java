package ui.clickable;

import java.awt.Color;
import java.awt.Image;

import core.Size;
import state.State;
import ui.UIContainer;
import ui.UIText;
import ui.VerticalContainer;

public class UIButton extends UIClickable {

	private UIContainer container;
	private UIText label;
	
	private Runnable clickEvent;
	
	public UIButton(String label, Runnable clickEvent) {
		this.label = new UIText(label);
		this.clickEvent = clickEvent;
		
		this.container = new VerticalContainer(size);
		this.container.addUIComponent(this.label);
		this.container.setFixedSize(new Size(150,40));
	}
	
	@Override
	public Image getSprite() {
		
		return container.getSprite();
	}

	@Override
	public void update(State state) {
		super.update(state);
		container.update(state);
		size = container.getSize();
		
		Color color = Color.GRAY;
		
		if(hasFocus) {
			color = Color.LIGHT_GRAY;
		}
		
		if(isPressed) {
			color = Color.DARK_GRAY;
		}
		
		container.setBackgroundColor(color);
	}

	@Override
	protected void onClick() {
		
		clickEvent.run();
	}

}
