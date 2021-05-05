package gfx;

import java.awt.Image;
import java.awt.image.BufferedImage;

import core.Direction;
import game.Game;

public class AnimationManager {

	private final SpriteSet spriteSet;
	private BufferedImage currentAnimationSheet;
	private String currentAnimationName;
	private final int updatePerFrame;
	private int currentFrameTime;
	private int frameIndex;
	private int directionIndex;
	
	public AnimationManager(SpriteSet spriteSet) {
		this.spriteSet = spriteSet;
		this.currentAnimationName = "";
		this.updatePerFrame = 20;
		this.currentFrameTime = 0;
		this.frameIndex = 0;
		this.directionIndex = 0;
		playAnimation("stand");
	}
	
	public Image getSprite() {
		return currentAnimationSheet.getSubimage(
				frameIndex * Game.SPRITE_SIZE,
				directionIndex * Game.SPRITE_SIZE,
				Game.SPRITE_SIZE,
				Game.SPRITE_SIZE
		);
	}
	
	public void update(Direction direction) {
		
		currentFrameTime++;
		directionIndex = direction.getAnimationRow();

		if(currentFrameTime >= updatePerFrame) {
			currentFrameTime = 0;
			frameIndex++;
			
			if(frameIndex >= currentAnimationSheet.getWidth() / Game.SPRITE_SIZE) {
				frameIndex = 0;
			}
		}
	}
	
	public void playAnimation(String name) {
		if(!name.equals(currentAnimationName)){
			this.currentAnimationSheet = (BufferedImage) this.spriteSet.get(name);
			currentAnimationName = name;
			frameIndex = 0;
		}
	}
}
