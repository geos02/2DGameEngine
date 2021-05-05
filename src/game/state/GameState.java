package game.state;

import java.awt.*;
import java.util.List;

import controller.NPCController;
import controller.PlayerController;
import core.Position;
import core.Size;
import entity.NPC;
import entity.Player;
import entity.action.Cough;
import entity.effect.Sick;
import game.Game;
import game.ui.UIGameTime;
import game.ui.UISicknessStatistics;
import input.Input;
import map.GameMap;
import ui.*;

public class GameState extends State {

	public GameState(Size windowSize, Input input) {
		super(windowSize, input);
		gameMap = new GameMap(new Size(20, 20), spriteLibrary);

		initializeCharacters();
		initializeNPC(100);
		makeNumberOfNPCsSick(10);
		initializeUI(windowSize);
	}

	private void initializeUI(Size windowSize) {
		/*UIContainer container = new VerticalContainer(windowSize);
		container.setPadding(new Spacing(5));
		container.setMargin(new Spacing(20));
		container.setBackgroundColor(new Color(0,0,0,0));
		container.addUIComponent(new UIText("Hello World!"));
		uiContainers.add(container);*/
		uiContainers.add(new UIGameTime(windowSize));
		uiContainers.add(new UISicknessStatistics(windowSize));
	}

	private void initializeCharacters() {

		Player player = new Player(new PlayerController(input), spriteLibrary);
		gameObjects.add(player);
		camera.focusOn(player);
		//gameObjects.addAll(List.of(player,npc));
	}

	private void makeNumberOfNPCsSick(int numberOfSick) {
		getGameObjectsOfClass(NPC.class).stream()
				.limit(numberOfSick)
				.forEach(npc -> npc.addEffect(new Sick()));
	}

	private void initializeNPC(int numberOfNPC) {
		for(int i=0; i < numberOfNPC; i++){
			NPC npc = new NPC(new NPCController(), spriteLibrary);
			npc.setPosition(gameMap.getRandomPosition());
			gameObjects.add(npc);
		}

	}
}
