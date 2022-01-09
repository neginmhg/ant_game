package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.geom.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;

public class MapView extends Container implements Observer {
	// code to output the map
	// same thing as 'm' did

	// the MapView output for this assignment is unchanged from A1: text output on
	// the
	// console showing all game objects which exist in the world

	private GameWorld theGameWorld;
	private boolean redraw;

	public MapView(GameWorld gw) {
		// this constructor also registers itself as an Observer
		this.getAllStyles().setBorder(Border.createLineBorder(7, ColorUtil.rgb(500, 0, 0)));
		theGameWorld = gw;
	}

	public void update(Observable gw, Object go) {
		// code here to output a view based on the data in the Observable
		theGameWorld = (GameWorld) gw;

		theGameWorld.map();
		repaint();
	}

	/**
	 * @return The width of map view
	 */
	public double getMapWidth() {
		return (double) this.getWidth();
	}

	public boolean isRedraw() {
		return redraw;
	}

	public void setRedraw(boolean redraw) {
		this.redraw = redraw;
	}

	/**
	 * @return The height of map view
	 */
	public double getMapHeight() {
		return (double) this.getHeight();
	}

	public void paint(Graphics g) {
		super.paint(g);
		Point pCmpRelPrnt = new Point(this.getX(), this.getY());
		IIterator theElements = theGameWorld.getCollection().getIterator();

		while (theElements.hasNext()) {
			if (theElements.getNext() instanceof IDrawable)
				((IDrawable) theElements.getCurrent()).draw(g, pCmpRelPrnt);

		}

	}

	@Override
	

	
	
	
	
	
	
	public void pointerPressed(int x, int y) {
		int px = x - getParent().getAbsoluteX();
		int py = y - getParent().getAbsoluteY();

		Point pPtrRelPrnt = new Point(px, py);
		Point pCmpRelPrnt = new Point(getX(), getY());

		

		IIterator theElements = theGameWorld.getCollection().getIterator();

		while (theElements.hasNext()) {
			GameObject selectedObj = (GameObject) theElements.getNext();
			
			
			
			//FLAG
			if (selectedObj instanceof Flag) {
				Flag f=(Flag) selectedObj;
				
				
				//click inside the shape
				if (f.contains(pPtrRelPrnt, pCmpRelPrnt)) {
					f.setSelected(true);
					
					

				}
				
				//click on the screen
				else {
					if (f.isSelected() && this.isRedraw() ) {
						float locationX = pPtrRelPrnt.getX() - pCmpRelPrnt.getX();
						float locationY = pPtrRelPrnt.getY() - pCmpRelPrnt.getY();
						f.setLocation(locationX, locationY);	
						this.redraw=false;

						
						
					
					}
					f.setSelected(false);
				}
				
			}
				
			//FOODSTATION	
				
			else {
				if (selectedObj instanceof FoodStation) {
				FoodStation fs=(FoodStation) selectedObj;
				
				if (fs.contains(pPtrRelPrnt, pCmpRelPrnt)) {
					fs.setSelected(true);
					

				}
				else {
					if (fs.isSelected() && this.isRedraw() ) {
						float locationX = pPtrRelPrnt.getX() - pCmpRelPrnt.getX();
						float locationY = pPtrRelPrnt.getY() - pCmpRelPrnt.getY();
						fs.setLocation(locationX, locationY);	
						this.redraw=false;
					
						
					}
					fs.setSelected(false);
				}
				
			}
			}
		}
		
			
		repaint();
			
	}
	
	
	
	
	
	
}
	
