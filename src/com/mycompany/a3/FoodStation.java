package com.mycompany.a3;


import java.util.Vector;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class FoodStation extends FixedObject {

	private int capacity;
	private boolean isSelected=false;
	public FoodStation() {
		// size,x,y,color
		super(getRandomNumber(70, 100), getRandomFloatNumber((float) 1000.0), getRandomFloatNumber((float) 1000.0),
				ColorUtil.rgb(0, 128, 0));
		this.capacity = (int) ((super.getSize()) * (0.35));

	}

	// getter and setters
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	// toString method for FoodStation class
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = "capacity= " + getCapacity() + " ";
		return parentDesc + myDesc;
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		if (isSelected())
		{
			g.setColor(ColorUtil.BLUE);
		}
		else
		{
			g.setColor(this.getColor());
			
		}
		
		
		int xLoc = (int) (pCmpRelPrnt.getX()+ this.getLocation().getX()-this.getSize()/2);
		int yLoc = (int) (pCmpRelPrnt.getY()+ this.getLocation().getY()-this.getSize()/2);	
		if(isSelected())
			g.drawRect(xLoc, yLoc, this.getSize(), this.getSize());	
		else {
			g.fillRect(xLoc, yLoc, this.getSize(), this.getSize());
			g.drawRect(xLoc, yLoc, this.getSize(), this.getSize());	
		}
		g.setColor(ColorUtil.BLACK);
		g.drawString(Integer.toString(capacity), xLoc+this.getSize()/4, yLoc+this.getSize()/4);
		
	}

	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		int px = pPtrRelPrnt.getX(); // pointer location relative to
		int py = pPtrRelPrnt.getY(); // parent’s origin
		
		int xLoc = (int) (pCmpRelPrnt.getX()+ this.getLocation().getX());
		int yLoc = (int) (pCmpRelPrnt.getY()+ this.getLocation().getY());
		
		if ( (px >= xLoc) && (px <= xLoc+this.getSize()) && (py >= yLoc) && (py <= yLoc+this.getSize()) )
			return true; 
		else 
			return false;
		}
	
	
	
	public boolean collidesWith(GameObject other) {
		// TODO Auto-generated method stub
		boolean result = false;
		int thisCenterX = (int) (this.getLocation().getX() );
		int thisCenterY = (int) (this.getLocation().getY() );
		
		
		int otherCenterX = (int) (((GameObject)other).getLocation().getX() );
		int otherCenterY = (int) (((GameObject)other).getLocation().getY() );
		// find dist between centers (use square, to avoid taking roots)
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterY - otherCenterY;
		int distBetweenCentersSqr = (dx*dx + dy*dy);
		// find square of sum of radii
		int thisRadius = this.getSize()/2;
		int otherRadius = ((GameObject)other).getSize()/2;
		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius
		+ otherRadius*otherRadius); 
		
		if (distBetweenCentersSqr <= radiiSqr) { 
			result = true ; 
		}
		
		
		else {		//not collided
	          Vector<GameObject> temp = this.getCollisionArray();
	           if(temp.isEmpty() == false) {
	               if(temp.contains(other)) {
	                   temp.remove(other);
	                }
	           }
		  }
		
		
		return result ;
		
	}

	@Override
	public void handleCollision(GameObject other,GameWorld gw) {
		// TODO Auto-generated method stu
		Vector<GameObject> temp = this.getCollisionArray();
        if(temp.contains(other)) {
            return;
        }
        else {
        	
        	// add other to this
        	Vector <GameObject> otherVector=other.getCollisionArray();
        	otherVector.add(other);
        	
        	//add this to other
            temp.add(other);
      if(other instanceof Ant) {

    	gw.foodStationCollision(this);
    }
	}
	}
	
	
	  public void setSelected(boolean b) { 
		  isSelected = b;
		  }
	  public boolean isSelected() { 
		  return isSelected; 
		  }
	}


