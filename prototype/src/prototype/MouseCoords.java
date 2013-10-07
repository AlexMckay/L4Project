package prototype;

import java.awt.event.MouseEvent;

public class MouseCoords extends java.applet.Applet implements java.awt.event.MouseListener  {
	
	public MouseCoords() {
		
		addMouseListener(this) ; 
		setBackground(java.awt.Color.red) ; 
		
	} 

	@Override
	public void mouseClicked(MouseEvent ev) {
		int x, y;
		
		x = ev.getX();
		y = ev.getY();
		
		System.out.printf("x = %d y = %d \n", x, y);
		
	}

	@Override
	public void mouseEntered(MouseEvent ev) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent ev) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent ev) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent ev) {
		// TODO Auto-generated method stub
		
	}

}
