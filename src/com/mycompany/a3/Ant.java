package com.mycompany.a3;




import java.util.Vector;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Ant extends MoveableObject implements ISteerable {

	private static Ant ant;
	private int maxSpeed;
	private int foodLevel;
	private int foodConsumptionRate;
	private int healthLevel;
	private int lastFlagReached;

	
	
	private Ant() {
		// heading, speed, size, x, y, color
		//
		super(0,getRandomNumber(40, 49) , 80, (float) 0.0, (float) 0.0, ColorUtil.rgb(255, 0, 0));
		this.lastFlagReached = 1;
		this.maxSpeed = 50;
		this.foodLevel = 1000;
		this.foodConsumptionRate = 1;
		this.healthLevel = 10;
		
	}

	public static Ant getAnt() {

		if (ant == null) {

			ant = new Ant();
			System.out.println("new ant created");
		}

		return ant;
	}

	public void clear() {
		
		
		ant = null;
		System.out.println("ant is removed");
		
	}

	// ant's move function
	public void move(double height, double width, double time) {
		if (foodLevel == 0 || healthLevel == 0 || super.getSpeed() == 0) {
			return;
		}

		else {
			double theta =(double) Math.toRadians(90 - super.getHeading()); // Math.toRadians() to convert degrees to
			

			double distaneMoved = (super.getSpeed() * time) ;
			distaneMoved=(double)distaneMoved/ 1000;

			double deltaX=Math.cos(theta) * distaneMoved ;
			double deltaY=Math.sin(theta) * distaneMoved ;
			
			double newX = deltaX + super.getLocation().getX();
			double newY = deltaY + super.getLocation().getY();
			
			
			super.setLocation((float) newX, (float) newY);
			
			if((newX < 0.0) || (newX+super.getSize() >= width)) {
				   super.setHeading(super.getHeading() + 180);
				}
				if((newY < 0.0) || (newY+super.getSize() >= height)){
				   super.setHeading(super.getHeading() + 180);
				}


			
		}
	}
	
	//true-right
		//false-left
		public void changeHeading(char c) {

			if (c == 'r') { // if the input is r we should add 5 degrees so the heading moves to the right
				super.setHeading((super.getHeading()) + 15 );	
				
				System.out.println("ant's heading changed to right by 5 degrees");
			} 
			else if(c == 'l') { //// if the input is l we should subtract 5 degrees so the heading moves to the
				//// right
				super.setHeading((super.getHeading()) - 15);
				System.out.println("ant's heading changed to left by 5 degrees");
			}
			
		}

	public void increaseSpeed() {
		// if the ant’s health value is between 10 and zero, it should be limited in
		// speed to a corresponding percentage of their speed range
		double perentage =(double)  healthLevel / 10;
		int speedRange = (int) (this.getMaxSpeed() * perentage);
		if(super.getSpeed() == speedRange) {
			return;
		}
		else if (super.getSpeed() > speedRange) { // if the speed is more than the speed level ,we shoudln't increase the
			// speed
			super.setSpeed(speedRange);
		} else { // if the speed is less than the speed limit ,we should increase the speed by
			// one
			super.setSpeed(super.getSpeed() + 1);
			System.out.println("Ant's speed increased");
		}
	}

	public void decreaseSpeed() {
		if (super.getSpeed() > 0) { // checking if the speed has positive value or not
			// if the ant’s health value is between 10 and zero, it should be limited in
			// speed to a corresponding percentage of their speed range
			double perentage =(double)  healthLevel / 10;
			int speedRange = (int) (this.getMaxSpeed() * perentage);
			 if (super.getSpeed() > speedRange) { // if the speed is more than speed limit we shoudl decrease the
				// speed // to speed limit.
				super.setSpeed(speedRange);
			} else { // if the speed is less than the speed limit ,we decrease the speed by one
				super.setSpeed(super.getSpeed() - 1);
				System.out.println("Ant's speed decreased");
			}
		}
	}
	
	
	
	

	public void decreaseFoodLevel() {
		// ant’s food level is reduced by the amount indicated by its
		// foodConsumptionRate.
		this.foodLevel = this.foodLevel - this.foodConsumptionRate;

	}

	// getters and setters
	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public int getFoodLevel() {
		return foodLevel;
	}

	public void setFoodLevel(int foodLevel) {
		this.foodLevel = foodLevel;
	}

	public int getFoodConsumptionRate() {
		return foodConsumptionRate;
	}

	public void setFoodConsumptionRate(int foodConsumptionRate) {
		this.foodConsumptionRate = foodConsumptionRate;
	}

	public int getHealthLevel() {
		return healthLevel;
	}

	public void setHealthLevel(int healthLevel) {
		this.healthLevel = healthLevel;
	}

	public int getLastFlagReached() {
		return lastFlagReached;
	}

	public void setLastFlagReached(int lastFlagReached) {
		this.lastFlagReached = lastFlagReached;
	}
	
	

	// toString method of the Ant class
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = "\n       " + "maxSpeed=" + this.maxSpeed + " " + "foodConcumpltionRate="
				+ this.foodConsumptionRate

				+ " " + "lastflagreached " + this.lastFlagReached + " " + "foodlevel " + this.foodLevel + " "
				+ "healthlevel " + this.healthLevel;
		return parentDesc + myDesc;
	}
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {

		g.setColor(this.getColor());

		int xLoc = pCmpRelPrnt.getX() + (int) this.getLocation().getX() - (this.getSize() / 2);
		int yLoc = pCmpRelPrnt.getY() + (int) this.getLocation().getY() - (this.getSize() / 2);

		
		g.drawArc(xLoc, yLoc, this.getSize(), this.getSize(), 0, 360);
		g.fillArc(xLoc, yLoc, this.getSize(), this.getSize(), 0, 360);

	}

	@Override
	public boolean collidesWith(GameObject other) {
		// TODO Auto-generated method stub
		boolean result = false;
		int thisCenterX = (int) (this.getLocation().getX()) ;
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
			result = true ; 	//collided
			
			
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
            
            
            if(other instanceof Spider) {
            	Spider s= (Spider)other;
            	gw.spiderCollision(s);
            }
            
            else if(other instanceof Flag) {
            	Flag f= (Flag)other;
            	gw.flagCollision(f.getSequenceNumber());
            }
            else if(other instanceof FoodStation) {
            	FoodStation fs= (FoodStation)other;
            	gw.foodStationCollision(fs);
            }
            
            
            
        }
	}
	

}
