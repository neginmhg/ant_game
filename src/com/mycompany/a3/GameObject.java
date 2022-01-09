package com.mycompany.a3;


import java.util.Random;
import java.util.Vector;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public abstract class GameObject implements IDrawable,ICollider{

	private int size;
	private Point location; // using point class from model
	private int color;
	private Vector<GameObject> collisionArray;
	
	
	
	public GameObject() {
		this.size = 1;
		this.location = new Point(); // point uses float values range between 0.0 and 1000.0
		this.color = ColorUtil.rgb(0, 0, 255);			//a default color for all the game object if the default constructor is called
		this.collisionArray=new Vector<GameObject>();
	}

	public GameObject(int size, float x, float y, int color) {
		this.size = size;
		this.location = new Point(x, y);
		this.color = color;
		this.collisionArray=new Vector<GameObject>();
	}

	
	//getters and setters
	public Vector<GameObject> getCollisionArray() {
		return collisionArray;
	}

	public void setCollisionArray(Vector<GameObject> collisionArray) {
		this.collisionArray = collisionArray;
	}

	// getters and setters
	public int getSize() {
		return this.size;
	}

	

	public Point getLocation() {
		return this.location;
	}

	public void setLocation(float x, float y) {
		this.location.setX(x);
		this.location.setY(y);
		
	}
	

	public int getColor() {
		return this.color;
	}

	public void setColor(int r, int g, int b) {
		this.color = ColorUtil.rgb(r, g, b);

	}

	// function to create random integers
	public static int getRandomNumber(int lowerBound, int upperBound) {
		Random rand = new Random();
		return rand.nextInt(upperBound - lowerBound + 1) + lowerBound;
	}

	// function to create random float between 0.0 to upperbound
	public static float getRandomFloatNumber(float upperBound) {
		Random randFloat = new Random();
		float result = randFloat.nextFloat() * upperBound;
		return Math.round(result);

	}
	

	// to string method for GaeObject class
	public String toString() {
		String parent = "loc=" + location.getX() + "," + location.getY() + " " + "color: " + "["
				+ ColorUtil.red(this.color) + "," + ColorUtil.green(this.color) + "," + ColorUtil.blue(this.color)
				+ "]";
		return parent;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
