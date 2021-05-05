package game.ui;

import core.Size;
import game.state.State;
import ui.Alignment;
import ui.HorizontalContainer;
import ui.UIText;

public class UIGameTime extends HorizontalContainer {

    private UIText uiText;


    public UIGameTime(Size windowSize) {
        super(windowSize);
        alignment = new Alignment(Alignment.Position.CENTER, Alignment.Position.START);
        uiText = new UIText("00:00");
        addUIComponent(uiText);
    }

    @Override
    public void update(State state) {
        super.update(state);
        uiText.setText(state.getTime().getFormattedTime());
    }
}
