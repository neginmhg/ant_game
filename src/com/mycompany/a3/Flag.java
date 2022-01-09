package com.mycompany.a3;

import java.util.Vector;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

//all flags should have the same size
public class Flag extends FixedObject {

	private int sequenceNumber;
	private boolean isSelected = false;

	public Flag() {
		super(); // access the fields of parent class(GameObject) in child class using super
					// keyword.
		this.sequenceNumber = 1;
	}

	public Flag(int size, float x, float y, int sequenceNumber) {
		super(size, x, y, ColorUtil.rgb(100, 149, 237));
		this.sequenceNumber = sequenceNumber;
	}

	// setter and getters
	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	// Flags are not allowed to change color once they are created.
	public void setColor(int r, int g, int b) {
	}

	// toString method for Flag class
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = "seqNumber= " + this.sequenceNumber;
		return parentDesc + myDesc;
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {

		if (isSelected()) {
			g.setColor(ColorUtil.BLUE);
		} else {
			g.setColor(this.getColor());

		}

		int xLoc = pCmpRelPrnt.getX() + (int) this.getLocation().getX() - (this.getSize() / 2); // upperleft corner
		int yLoc = pCmpRelPrnt.getY() + (int) this.getLocation().getY() - (this.getSize() / 2);

		int[] x = { xLoc, (xLoc + (this.getSize()) / 2), (xLoc + this.getSize()) };

		int[] y = { (yLoc), yLoc + this.getSize(), (yLoc) };

		if (isSelected())
			g.drawPolygon(x, y, 3);
		else {
			g.setColor(this.getColor());
			g.fillPolygon(x, y, 3);

		}

		g.setColor(ColorUtil.BLACK);
		g.drawString(Integer.toString(sequenceNumber), xLoc + (this.getSize() / 3 + this.getSize() / 9),
				yLoc + this.getSize() / 4);

	}

	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {

		int px = pPtrRelPrnt.getX(); // pointer location relative to
		int py = pPtrRelPrnt.getY(); // parent’s origin

		int xLoc = (int) (pCmpRelPrnt.getX() + this.getLocation().getX() - this.getSize() / 2);
		int yLoc = (int) (pCmpRelPrnt.getY() + this.getLocation().getY() - this.getSize() / 2);

		if ((px >= xLoc) && (px <= xLoc + this.getSize()) && (py >= yLoc) && (py <= yLoc + this.getSize()))
			return true;
		else
			return false;
	}

	public boolean collidesWith(GameObject other) {
		// TODO Auto-generated method stub
		boolean result = false;
		int thisCenterX = (int) (this.getLocation().getX());
		int thisCenterY = (int) (this.getLocation().getY());

		int otherCenterX = (int) (((GameObject) other).getLocation().getX());
		int otherCenterY = (int) (((GameObject) other).getLocation().getY());
		// find dist between centers (use square, to avoid taking roots)
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterY - otherCenterY;
		int distBetweenCentersSqr = (dx * dx + dy * dy);
		// find square of sum of radii
		int thisRadius = this.getSize() / 2;
		int otherRadius = ((GameObject) other).getSize() / 2;
		int radiiSqr = (thisRadius * thisRadius + 2 * thisRadius * otherRadius + otherRadius * otherRadius);

		if (distBetweenCentersSqr <= radiiSqr) {
			result = true;
		}

		else { // not collided
			Vector<GameObject> temp = this.getCollisionArray();
			if (temp.isEmpty() == false) {
				if (temp.contains(other)) {
					temp.remove(other);
				}
			}
		}

		return result;

	}

	@Override
	public void handleCollision(GameObject other, GameWorld gw) {
		// TODO Auto-generated method stu
		Vector<GameObject> temp = this.getCollisionArray();
		if (temp.contains(other)) {
			return;
		} else {

			// add other to this
			Vector<GameObject> otherVector = other.getCollisionArray();
			otherVector.add(other);

			// add this to other
			temp.add(other);

			if (other instanceof Ant) {
				gw.flagCollision(this.getSequenceNumber());
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
