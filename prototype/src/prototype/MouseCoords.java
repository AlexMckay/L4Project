package prototype;

import java.awt.*;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.net.URL;

public class MouseCoords extends java.applet.Applet implements java.awt.event.MouseListener  {
	
	Image cperks;
	URL base;
	MediaTracker tr;	
	
	public void paint(Graphics g) {
	   tr = new MediaTracker(this);
	   cperks = getImage(getCodeBase(), "colin.jpg");
	   tr.addImage(cperks,0);
	   g.drawImage(cperks, 0, 0, this);
	} 
	
	
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
