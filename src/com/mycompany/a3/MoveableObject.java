package com.mycompany.a3;


public abstract class MoveableObject extends GameObject {

	// fields for moveable class
	private int heading;
	private int speed;

	// default constructor
	public MoveableObject() {
		this.heading = 0;
		this.speed = 0;
	}

	public MoveableObject(int heading, int speed, int size, float x, float y, int color) {
		super(size, x, y, color);
		this.heading = heading;
		this.speed = speed;

	}

	// move() causes the object to update its location based on its current heading
	// and speed.
	public abstract void move(double height,double width,double time);

	// getters and setters
	public int getHeading() {
		return heading;
	}

	public void setHeading(int heading) {
		this.heading = heading;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	// toString function of moveable class
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = "heading= " + this.heading + " " + "speed= " + this.speed + " " + "size= " + super.getSize()
				+ " ";
		return parentDesc + myDesc;
	}

}