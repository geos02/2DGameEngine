package ui;

import core.Position;
import core.Size;
import game.state.State;
import gfx.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class UIContainer extends UIComponent {

    protected Color backgroundColor;

    protected Alignment alignment;
    protected Size windowSize;

    protected List<UIComponent> children;


    public UIContainer(Size windowSize) {
      super();
      this.windowSize = windowSize;
      alignment = new Alignment(Alignment.Position.START, Alignment.Position.START);
      margin = new Spacing(5);
      padding = new Spacing(5);
      backgroundColor = new Color(0,0,0,0);
      children = new ArrayList<>();
      calculateSize();
      calculatePosition();
    }

    protected abstract Size calculateContentSize();
    protected abstract void calculateContentPosition();

    private void calculateSize(){
        Size contentSize = calculateContentSize();
        size = new Size(
                padding.getHorizontal() + contentSize.getWidth(),
                padding.getVertical() + contentSize.getHeight()
        );
    }

    private void calculatePosition(){
        //position = new Position(margin.getLeft(), margin.getTop());
        int x = margin.getLeft();
        if(alignment.getHorizontal().equals(Alignment.Position.CENTER)){
            x = windowSize.getWidth() / 2 - size.getWidth() / 2;
        }
        if(alignment.getHorizontal().equals(Alignment.Position.END)){
            x = windowSize.getWidth() - size.getWidth() - margin.getRight();
        }

        int y = margin.getTop();
        if(alignment.getVertical().equals(Alignment.Position.CENTER)){
            y = windowSize.getHeight() / 2 - size.getHeight() / 2;
        }
        if(alignment.getVertical().equals(Alignment.Position.END)){
            y = windowSize.getHeight() - size.getHeight() - margin.getBottom();
        }

        position = new Position(x,y);

        calculateContentPosition();
    }

    @Override
    public Image getSprite() {
        BufferedImage image = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_BIT_MASKED);
        Graphics2D graphics2 = image.createGraphics();
        graphics2.setColor(backgroundColor);
        graphics2.fillRect(0,0, size.getWidth(), size.getHeight() );

        // Draw Children
        for(UIComponent uiComponent : children){
            graphics2.drawImage(
                    uiComponent.getSprite(),
                    uiComponent.getPosition().intX(),
                    uiComponent.getPosition().intY(),
                    null
            );
        }

        graphics2.dispose();
        return image;
    }

    @Override
    public void update(State state) {
        children.forEach(uiComponent -> uiComponent.update(state));
        calculateSize();
        calculatePosition();
    }

    public void addUIComponent(UIComponent uiComponent){
        children.add(uiComponent);
    }

    public void setBackgroundColor(Color color) {
        backgroundColor = color;
    }
}
