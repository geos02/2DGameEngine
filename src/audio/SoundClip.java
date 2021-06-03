package audio;

import javax.sound.sampled.Clip;

import game.settings.GameSettings;

public class SoundClip extends AudioClip {

	public SoundClip(Clip clip) {
		super(clip);
		
	}

	@Override
	protected float getVolume(GameSettings gameSettings) {
		
		return gameSettings.getSoundVolume();
	}

}
