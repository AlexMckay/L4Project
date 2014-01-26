package prototype;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

public class MouseCoords extends java.applet.Applet implements java.awt.event.MouseListener  {
	
	Image cperks;
	int clickCount=0;
	int height, width;
	URL base;
	MediaTracker tr;
	
	public void init(){
		setBackground(java.awt.Color.red);
		setSize(500,500);
	}
	
	public void paint(Graphics g) {
		cperks = getImage(getCodeBase(), "colin.jpg");
		height = cperks.getHeight(null);
		width = cperks.getWidth(null);
		tr = new MediaTracker(this);
		tr.addImage(cperks,0);
		g.drawImage(cperks, 0, 0, this);
	} 
	
	public MouseCoords() {
		
		addMouseListener(this); 
		
	} 

	@Override
	public void mouseClicked(MouseEvent ev) {
		int x, y;
		clickCount++;
		
		x = ev.getX();
		y = ev.getY();
		
		System.out.printf("%d. x = %d y = %d \n", clickCount, x, y);
		
		
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
