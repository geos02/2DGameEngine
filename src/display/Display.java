package display;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import input.Input;
import state.State;

public class Display extends JFrame {

	private final Canvas canvas;
	private final Renderer renderer;
	private DebugRenderer debugRenderer;
	
	public Display(int width, int height, Input input) {
		setTitle("My Awesome 2D game");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		addKeyListener(input);
		
		renderer = new Renderer();
		debugRenderer = new DebugRenderer();
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setFocusable(false);
		canvas.addMouseListener(input);
		canvas.addMouseMotionListener(input);
		
		
		add(canvas);
		pack();
		canvas.createBufferStrategy(2);
		setLocationRelativeTo(null);
		
		setVisible(true);
	}
	
	public void render(State state, boolean debugMode) {
		BufferStrategy bs = canvas.getBufferStrategy();
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		renderer.render(state, g);
		if(debugMode)
			debugRenderer.render(state,g);
		
		g.dispose();
		bs.show();
	}
}
