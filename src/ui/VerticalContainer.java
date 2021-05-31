package ui;

import core.Position;
import core.Size;

public class VerticalContainer extends UIContainer {

    public VerticalContainer(Size windowSize) {
        super(windowSize);
    }

    @Override
    protected Size calculateContentSize() {
        int biggestChildWidth = 0;
        int combinedChildHeight = 0;

        for(UIComponent child : children){
            combinedChildHeight += child.getSize().getHeight() + child.getMargin().getVertical();

            if(child.getSize().getWidth() > biggestChildWidth){
                biggestChildWidth = child.getSize().getWidth();
            }
        }

        return new Size(biggestChildWidth,combinedChildHeight);
    }

    @Override
    protected void calculateContentPosition() {
        int currentY = padding.getTop();
        for(UIComponent child : children){
            currentY += child.getMargin().getTop();
            child.setRelativePosition(new Position(padding.getLeft(), currentY));
            child.setAbsolutePosition(new Position(padding.getLeft() + absolutePosition.intX(), currentY + absolutePosition.intY()));
            currentY += child.getSize().getHeight();
            currentY += child.getMargin().getBottom();
        }
    }
}
