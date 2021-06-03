package audio;

import javax.sound.sampled.Clip;

import game.settings.GameSettings;

public class MusicClip extends AudioClip {

	public MusicClip(Clip clip) {
		super(clip);
		
	}

	@Override
	protected float getVolume(GameSettings gameSettings) {
		
		return gameSettings.getMusicVolume();
	}

}
