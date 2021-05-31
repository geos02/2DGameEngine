package state.game;

import java.awt.*;
import java.util.List;


import controller.NPCController;
import controller.PlayerController;
import core.Position;
import core.Size;
import entity.NPC;
import entity.Player;
import entity.SelectionCircle;
import entity.action.Cough;
import entity.effect.Sick;
import game.Game;
import input.Input;
import map.GameMap;
import state.State;
import state.game.ui.UIGameTime;
import state.game.ui.UISicknessStatistics;
import ui.*;
import ui.clickable.UIButton;

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
		
		VerticalContainer verticalContainer = new VerticalContainer(windowSize);
		verticalContainer.setAlignment(new Alignment(Alignment.Position.CENTER,Alignment.Position.CENTER));
		verticalContainer.setBackgroundColor(Color.DARK_GRAY);
		verticalContainer.addUIComponent(new UIButton("Menu", () -> System.out.println("Button1 pressed")));
		verticalContainer.addUIComponent(new UIButton("Options", () -> System.out.println("Button2 pressed")));
		verticalContainer.addUIComponent(new UIButton("Exit", () -> System.exit(0)));
		uiContainers.add(verticalContainer);
		
	}

	private void initializeCharacters() {

		Player player = new Player(new PlayerController(input), spriteLibrary);
		gameObjects.add(player);

		SelectionCircle selectionCircle = new SelectionCircle();
		selectionCircle.setParent(player);
		gameObjects.add(selectionCircle);

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
