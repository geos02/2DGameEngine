package input;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import core.Position;
import game.Game;

public class Input implements KeyListener, MouseListener, MouseMotionListener {

	private Position mousePosition;
	private boolean mouseClicked;
	private boolean mousePressed;
	
	private boolean[] currentlyPressed;
	private boolean[] pressed;
	private Game game;

	public Input(Game game) {
		pressed = new boolean[255];
		currentlyPressed = new boolean[255];
		this.game = game;
		this.mousePosition = new Position(0, 0);
	}

	public boolean isPressed(int keyCode){
		if(!pressed[keyCode] && currentlyPressed[keyCode]){
			pressed[keyCode] = true;
			return true;
		}

		return false;
	}
	
	public void clearMouseClicked() {
		mouseClicked = false;
	}
	
	public Position getMousePosition() {
		return mousePosition;
	}
	
	public boolean isMouseClicked() {
		return mouseClicked;
	}
	
	public boolean isMousePressed() {
		return mousePressed;
	}

	public boolean isCurrentlyPressed(int keyCode) {
		return currentlyPressed[keyCode];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		currentlyPressed[e.getKeyCode()] = true;

	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		currentlyPressed[e.getKeyCode()] = false;
		pressed[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) { }

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		mousePressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mousePressed = false;
		mouseClicked = true;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mousePosition = new Position(e.getPoint().getX(), e.getPoint().getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mousePosition = new Position(e.getPoint().getX(), e.getPoint().getY());
	}

}
