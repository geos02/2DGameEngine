package game.settings;

public class GameSettings {

    private boolean debugMode;
    private float musicVolume;
    private float soundVolume;
   

    public GameSettings() {
        this.debugMode = false;
        musicVolume = 0;
        soundVolume = 0;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public void toggleDebugMode() {
        debugMode = !debugMode;
    }

	public float getMusicVolume() {
		return musicVolume;
	}

	public void setMusicVolume(float musicVolume) {
		this.musicVolume = musicVolume;
	}

	public float getSoundVolume() {
		return soundVolume;
	}

	public void setSoundVolume(float soundVolume) {
		this.soundVolume = soundVolume;
	}
    
    
}
