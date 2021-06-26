package map;

import java.awt.Image;

import gfx.SpriteLibrary;

public class Tile {

	private Image sprite;
	
	public Tile(SpriteLibrary spriteLibrary) {
		this.sprite = spriteLibrary.getImage("woodfloor");
	}
	
	public Image getSprite() {
		return sprite;
	}
}
