package com.mycompany.a3;




import java.util.Vector;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Spider extends MoveableObject  {

	// delfault constructors
	public Spider() {
		// heading, speed, size, x, y, color
		super(getRandomNumber(0, 359), getRandomNumber(100, 200), getRandomNumber(70, 120),
				getRandomFloatNumber((float) 1000.0), getRandomFloatNumber((float) 1000.0), ColorUtil.rgb(0, 0, 0));

	}

	// constructor to set location x and location y of the spider
	public Spider(float x, float y) {
		// heading, speed, size, x, y, color
		super(getRandomNumber(0, 359), getRandomNumber(100, 200), getRandomNumber(30, 50),
				getRandomFloatNumber((float) 1000.0), getRandomFloatNumber((float) 1000.0), ColorUtil.rgb(0, 0, 0));

	}

	// spider's move function
	public void move(double height,double width,double time) {
		
		
		double theta = Math.toRadians(90 - (super.getHeading())); 
		double distaneMoved = (super.getSpeed() * time )/ 1000;
		double deltaX=Math.cos(theta) * distaneMoved ;
		double deltaY=Math.sin(theta) * distaneMoved ;
		double newX = deltaX + super.getLocation().getX();
		double newY = deltaY + super.getLocation().getY();
		super.setLocation((float) newX, (float) newY);
		if((newX < 0) || (newX+super.getSize() >= width)) {
			   super.setHeading(super.getHeading() + 180);		   
			}
			if((newY < 0) || (newY+super.getSize() >= height)){
			   super.setHeading(super.getHeading() + 180);
			}
			randomSpiderHeading(); 	
			
			
		
		
	}

	// function to create random values to add or subtract from the heading of the
	// spider
	private void randomSpiderHeading() {
		int rand = getRandomNumber(-7, 8);
		super.setHeading((super.getHeading()) + rand);
		
	}

	// Spiders are not allowed to change color once they are created.
	public void setColor() {}
	
	public String toString() {
		String parentDesc = super.toString();
		return parentDesc;
		
	}

	
	public void draw(Graphics g, Point pCmpRelPrnt) {
		
		g.setColor(this.getColor());
		int xLoc = pCmpRelPrnt.getX()+(int)this.getLocation().getX()  - (this.getSize() / 2);
		int yLoc = pCmpRelPrnt.getY()+(int)this.getLocation().getY() - (this.getSize() / 2);		
		int[] x = { xLoc, (xLoc + (this.getSize())/2), (xLoc + this.getSize())};
		int[] y = { (yLoc ), yLoc+ this.getSize(), (yLoc ) };
		g.drawPolygon(x, y, 3);	
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
        	
        	gw.spiderCollision(this);
        }
        }


	}
}

