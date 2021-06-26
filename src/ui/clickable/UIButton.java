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
	
	private ClickAction clickAction;
	
	public UIButton(String label, ClickAction clickAction) {
		this.label = new UIText(label);
		this.clickAction = clickAction;
		
		this.container = new VerticalContainer(size);
		this.container.addUIComponent(this.label);
		this.container.setFixedSize(new Size(200,40));
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
	protected void onClick(State state) {
		
		clickAction.execute(state);
	}

	@Override
	protected void onDrag(State state) {
		
	}

	@Override
	protected void onFocus(State state) {
		
		state.getAudioPlayer().playSound("button.wav");
	}

}
