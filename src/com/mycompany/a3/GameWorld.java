package com.mycompany.a3;

import java.util.Observable;

import com.codename1.ui.geom.Dimension;

public class GameWorld extends Observable {

	private int clock;
	private int livesLeft;
	private GameObjectCollection collection;
	private boolean flag; // to know if the sound is ON or OFF
	private Sound flagSound;
	private Sound spiderSound;
	private Sound foodStationSound;

	// constructor
	public GameWorld() {
		this.livesLeft = 3;
		this.clock = 0;
		this.setChanged();
	}

	//////////////////////
	// functions
	public void init() {
		// flag intialized
		this.collection = new GameObjectCollection();
		Flag flagOne = new Flag(100, (float) 200.0, (float) 200.0, 1);
		Flag flagTwo = new Flag(100, (float) 200.0, (float) 800.0, 2);
		Flag flagThree = new Flag(100, (float) 700.0, (float) 800.0, 3);
		Flag flagFour = new Flag(100, (float) 900.0, (float) 400.0, 4);
		Flag flagFive = new Flag(100, (float) 800.0, (float) 300.0, 5);

		// FoodStation intialized
		FoodStation stationOne = new FoodStation();
		FoodStation stationTwo = new FoodStation();

		// spider intialized
		Spider spiderOne = new Spider();
		Spider spiderTwo = new Spider();

		// ant intialized
		float initialAntX = flagOne.getLocation().getX();
		float initialAntY = flagOne.getLocation().getY();

		// Initially, the ant should be positioned at the location of flag #1
		// (initially lastFlagReached is assigned to “1”)
		// ants heading,size and speed are assigned to zero in the ant's constructor

		Ant ant = Ant.getAnt();
		ant.setLocation(initialAntX, initialAntY);

		// adding all the objects to the arrayList
		collection.add(flagOne);
		collection.add(flagTwo);
		collection.add(flagThree);
		collection.add(flagFour);
		collection.add(flagFive);

		collection.add(ant);

		collection.add(spiderOne);
		collection.add(spiderTwo);

		collection.add(stationOne);
		collection.add(stationTwo);



		this.setChanged();

	}

	// getters and setters
	public int getClock() {
		return clock;
	}

	public void setClock(int clock) {
		this.clock = clock;
	}

	public int getLivesLeft() {
		return livesLeft;
	}

	public void setLivesLeft(int livesLeft) {
		this.livesLeft = livesLeft;
	}

	public GameObjectCollection getCollection() {
		return this.collection;
	}

	public void setCollection(GameObjectCollection collection) {
		this.collection = collection;
	}

	public boolean getFlag() {
		return flag;
	}

	public void changeFlag() {

		flag = !flag; // whatever the sound is change it to the opposite status

		this.setChanged();

	}


	// ant accelerate function
	public void accelerate() {
		Ant ant = Ant.getAnt();
		if(ant != null) {
		ant.increaseSpeed();
		
		}
		this.setChanged();
	}

	// ant brake function
	public void brake() {
		Ant ant = Ant.getAnt();
		boolean antDead = checkGameState(ant); // checking to see if the ant's health and foodlevel are not zero
		if(ant != null) {
		if (antDead == true) {
			ant.clear();
			return;
		}
		else {
		ant.decreaseSpeed();
		
		}
		}
		this.setChanged();
	}

	// ant collides with flag number that was entered.
	public void flagCollision(int flagNumber) {

		if (flagNumber < 5) {
			Ant ant = Ant.getAnt();
			if (flagNumber == (ant.getLastFlagReached() + 1)) {// if the flag is the next flag in the game
				ant.setLastFlagReached(flagNumber); // change LastFlagReached to the flag

			}

			System.out.println("Ant has collided with the flag number " + flagNumber);
		} else {
			
			System.out.println("Game over, you win!  Total time: " + clock);
						exit();
		}

		if(flag ==false) {
			flagSound.play();
			}
		this.setChanged();

	}

	// ant collides with foodstation function
	public void foodStationCollision(FoodStation fs) {
		int capacity = 0; // to find the capacity of the random selected foodstation

		
		capacity = fs.getCapacity();
		if (capacity != 0) { // if the foodstation is not empty
			fs.setCapacity(0); // reduce the capacity of the food station to zero
			fs.setColor(0, 255, 0); // fade the color to light green).

		}
		
		Ant ant = Ant.getAnt();
		ant.setFoodLevel(ant.getFoodLevel() + capacity); // adding the capacity of the foodstation to the ant's
		// foodlevel
		System.out.println("ant food level increased");
		if(flag==false) {
		foodStationSound.play();
		}
		this.setChanged();

	}

	// ant collides with spider function
	public void spiderCollision(Spider s) {

		
		Ant ant = Ant.getAnt();

		IIterator theElements = collection.getIterator();
		
		boolean antDead = checkGameState(ant); // check to see if the healthlevel or foodlevel of ant is not
		// zero
		if (antDead == true) {
			ant.clear();
			return;

		}

		ant.setHealthLevel(ant.getHealthLevel() - 1); // decrease healthlevel of ant by one
		
		ant.setColor(250, 128, 114); // fade the color to light red color
		ant.decreaseSpeed();

		// remove the collided spider from the collection

		theElements.remove(s);
		Spider newSpider = new Spider(); // creating a new spider
		collection.add(newSpider); // adding the new spider to the arraylist
		System.out.println("A new spider is created ");

		
		
		if(flag==false) {
		spiderSound.play();
		}
		

		this.setChanged();
	}

	// function for the game clock tick
	public void clockTick(double t,Dimension d) {
		
		
		
		Ant ant = Ant.getAnt();

		ant.decreaseFoodLevel(); // decrease food level of ant by foodConsumptionRate

		boolean antDead = checkGameState(ant); // if true is dead, if false is alive
		if (antDead == true) {
			clock++;
			ant.clear();
			return;
		}

		ant.move(d.getHeight(), d.getWidth(), t); // changing the location of ant

		IIterator theElements = collection.getIterator();

		while (theElements.hasNext()) {
			GameObject current = (GameObject) theElements.getNext();
			if (current instanceof Spider) {
				Spider spider = (Spider) current;
				spider.move(d.getHeight(), d.getWidth(), t); // changing the location & heading of the spider
			}
		}

		clock++;

		// check if moving caused any collisions
		IIterator iter = collection.getIterator();

		while (iter.hasNext()) {
			ICollider curObj = (ICollider) iter.getNext(); // get a collidable object // check if this object collides
			// with any OTHER object

			IIterator iter2 = collection.getIterator();
			while (iter2.hasNext()) {
				ICollider otherObj = (ICollider) iter2.getNext(); // get a collidable object
				// check for collision
				if (otherObj != curObj) {// make sure it's not the SAME object
					if (curObj.collidesWith((GameObject) otherObj)) {
						curObj.handleCollision((GameObject) otherObj, this);
					}
				}
			}
		}

		this.setChanged();

	}

	// function to change the ant heading whether to right or left
	public void changeAntHeading(char c) {
	
			Ant ant = Ant.getAnt();
			ant.changeHeading(c);

			this.setChanged();
		
	}

	// function to map game by using the toString functions of all game objects
	public void map() {
		System.out.println("Map of the current world : ");

		IIterator theElements = collection.getIterator();
		while (theElements.hasNext()) {

			GameObject obj = theElements.getNext();

			if (obj instanceof Ant) {
				System.out.println("Ant:  " + obj);
			} else if (obj instanceof Spider) {
				System.out.println("Spider:  " + obj);
			} else if (obj instanceof FoodStation) {
				System.out.println("FoodStation:  " + obj);
			} else {
				System.out.println("Flag:  " + obj);
			}

		}

	}

	// function to exit out of the game
	public void exit() {
		System.exit(0);
		System.out.println("User has confirmed the exit by saying yes.");
		System.out.println("Program terminated !!");
		this.setChanged();
	}

	// The program keeps track of the following “game state” values: current clock
	// time and lives remaining
	private boolean checkGameState(Ant ant) {
		ant = Ant.getAnt();
		
		if ((ant.getFoodLevel() <= 0) || (ant.getHealthLevel() <= 0)) {
			livesLeft--;
			if (livesLeft > 0) { // if livesleft is not zero,the game is not yet over
				ant.clear();
				collection.removeAll(); // remove all gameobject from the arraylist
				ant.clear();
				init(); // re-initialize the game
				System.out.println("You lost one life ! Game re-initiated");
				return false; // ant is alive
			} else { // if there are no lives left for the ant ,the game is over
				System.out.println("Game over, you failed!");

				exit();
				return true; // ant is dead
			}

		} else {
			return false; // ant is alive
		}

	}

	// Deselects game objects when the play button is clicked
	public void DeSelectObjects() {
		IIterator iterator = collection.getIterator();
		while (iterator.hasNext()) {
			Object object = iterator.getNext();
			if (object instanceof ISelectable) {
				ISelectable selectableObject = (ISelectable) object;
				if (selectableObject.isSelected())
					selectableObject.setSelected(false);
			}
		}
	}

	public void createSounds() {

		flagSound = new Sound("levelup.wav"); 	//for flag
		spiderSound = new Sound("pain.wav");		//spider	
		foodStationSound = new Sound("burger.wav");		//food station
		
	}

}
