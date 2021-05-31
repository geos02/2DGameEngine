package ui;

import core.Position;
import core.Size;

public class HorizontalContainer extends UIContainer {

    public HorizontalContainer(Size windowSize) {
        super(windowSize);
    }

    @Override
    protected Size calculateContentSize() {
        int combinedChildWidth = 0;
        int tallestChildHeight = 0;

        for(UIComponent uiComponent : children){
            combinedChildWidth += uiComponent.getSize().getWidth() + uiComponent.getMargin().getHorizontal();

            if(uiComponent.getSize().getHeight() > tallestChildHeight){
                tallestChildHeight = uiComponent.getSize().getHeight();
            }
        }

        return new Size(combinedChildWidth, tallestChildHeight);
    }

    @Override
    protected void calculateContentPosition() {
        int currentX = padding.getLeft();

        for(UIComponent child : children){
            currentX += child.getMargin().getLeft();
            child.setRelativePosition(new Position(currentX,padding.getTop()));
            child.setAbsolutePosition(new Position(currentX + absolutePosition.intX(),padding.getTop() + absolutePosition.intY()));
            currentX += child.getSize().getWidth();
            currentX += child.getMargin().getRight();
        }
    }
}
