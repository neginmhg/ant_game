package com.mycompany.a3;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public abstract class FixedObject extends GameObject implements ISelectable {

	// default constructor
	public FixedObject() {
		super(); // access the fields of parent class(GameObject) in child class using super
		// keyword.

	}

	public FixedObject(int size, float x, float y, int color) {
		super(size, x, y, color);
	}

	
	

	
	// toString method for FixedObject class
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = "size= " + super.getSize() + " ";
		return parentDesc + myDesc;
	}
	
	//selctable interface
	
	  public abstract void setSelected(boolean b);
	  public abstract boolean isSelected() ;
	  public abstract void draw(Graphics g, Point pCmpRelPrnt);
	  public abstract boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt);

}
