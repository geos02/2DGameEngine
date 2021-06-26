package audio;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import game.settings.GameSettings;

public class AudioPlayer {

	List<AudioClip> audioClips;
	
	public AudioPlayer() {
		
		audioClips = new ArrayList<>();
	}
	
	public void update(GameSettings gameSettings) {
		audioClips.forEach(audioClip -> audioClip.update(gameSettings));
		
		List.copyOf(audioClips).forEach(audioClip ->{
			if(audioClip.hasFinishedPlaying()) {
				audioClip.cleanUp();
				audioClips.remove(audioClip);
			}
		});
	}
	
	public void playMusic(String fileName) {
		
		final Clip clip = getClip(fileName);
		audioClips.add(new MusicClip(clip));
	}
	
	public void playSound(String fileName) {
		
		final Clip clip = getClip(fileName);
		audioClips.add(new SoundClip(clip));
	}
	
	private Clip getClip(String fileName) {
		// URL file:/E:/Java/Java%202D%20Game/MyGame/res/sounds/isobubbler.wav
		final URL soundFile = AudioPlayer.class.getResource("/sounds/" + fileName);
		//System.out.println(soundFile.toString());
		try(AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile)){
			final Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.setMicrosecondPosition(0);
			return clip;
		}catch(UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return null;
	}
}
