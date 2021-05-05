package game.settings;

public class GameSettings {

    private boolean debugMode;

    public GameSettings() {
        this.debugMode = false;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public void toggleDebugMode() {
        debugMode = !debugMode;
    }
}
