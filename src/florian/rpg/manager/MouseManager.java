package florian.rpg.manager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {

	private boolean leftPressed, rightPressed;
	private boolean leftClicked, rightClicked;
	private boolean leftJustClicked, rightJustClicked;
	private int mouseX, mouseY;
	
	public MouseManager() { }
	
	public boolean leftPressed(){
		return leftPressed;
	}
	
	public boolean rightPressed(){
		return rightPressed;
	}
	
	public boolean leftClicked() {
		return leftJustClicked;
	}
	
	public boolean rightClicked() {
		return rightJustClicked;
	}
	
	public int getMouseX(){
		return mouseX;
	}
	
	public int getMouseY(){
		return mouseY;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			leftPressed = true;
		else if(e.getButton() == MouseEvent.BUTTON3)
			rightPressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			leftPressed = false;
		else if(e.getButton() == MouseEvent.BUTTON3)
			rightPressed = false;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) { 
		if(e.getButton() == MouseEvent.BUTTON1)
			leftClicked = true;
		else if(e.getButton() == MouseEvent.BUTTON3)
			rightClicked = true;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) { 
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) {	}
	
	public void tick() {
		if(leftClicked) {
			if(leftJustClicked) {
				leftJustClicked = false;
				leftClicked = false;
			}else
				leftJustClicked = true;
		}
		
		if(rightClicked) {
			if(rightJustClicked) {
				rightJustClicked = false;
				rightClicked = false;
			}else
				rightJustClicked = true;
		}
	}
	
}
