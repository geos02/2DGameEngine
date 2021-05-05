package ui;

import core.Size;
import game.state.State;
import gfx.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIText extends UIComponent {

    private String text;
    private int fontSize ;
    private int fontStyle;
    private String fontFamily;
    private Color color;

    private boolean dropShadow;
    private int dropShadowOffset;
    private Color dropShadowColor;

    private Font font;

    public UIText(String text){
        this.text = text;
        this.fontSize = 24;
        this.fontStyle = Font.PLAIN;
        this.fontFamily = "joystix monospace";
        this.color = Color.WHITE;
    }

    @Override
    public Image getSprite() {
        BufferedImage image = (BufferedImage) ImageUtils.createCompatibleImage(size,ImageUtils.ALPHA_BIT_MASKED);
        Graphics2D graphics2D = image.createGraphics();
        graphics2D.setFont(font);

        graphics2D.setColor(color);
        graphics2D.drawString(text, padding.getLeft(), fontSize + padding.getTop());

        graphics2D.dispose();

        return image;
    }

    @Override
    public void update(State state) {
        createFont();
        calculateSize();
    }

    private void calculateSize() {
        FontMetrics metrics = new Canvas().getFontMetrics(font);
        size = new Size(
                metrics.stringWidth(text) + padding.getHorizontal(),
                metrics.getHeight() + padding.getVertical()
        );
    }

    private void createFont() {
        font = new Font(fontFamily, fontStyle, fontSize);
    }

    public void setText(String text) {
        this.text = text;
    }
}
