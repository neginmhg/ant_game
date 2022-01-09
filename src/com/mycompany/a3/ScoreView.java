package com.mycompany.a3;




import java.util.Observable;

import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;

public class ScoreView extends Container implements Observer{
	//code to output the current game/ant state information. 
	//same thing as 'd' did

	private Label clock;
	private Label livesLeft;
	private Label lastFlagReached;
	private Label antFoodLevel;
	private Label antHealthLevel;
	private Label sound;
	//present a graphical display of the game/ant state values



	public ScoreView (){ 

		this.setLayout(new FlowLayout(CENTER)); 	//Arranges components left-to-right
		//from left to right
		createClock();
		createLivesLeft();
		createLastFlagReached();
		createFoodLevel();
		createHealthLevel();
		createSound();
	} 




	private void createClock()
	{
		Label clockText = new Label("Time:");	
		clock = new Label("0");	
		this.add(clockText);
		this.add(clock);
		//styles
		clock.getAllStyles().setPadding(RIGHT, 4);
		clock.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		clockText.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));	
	}


	private void createLivesLeft()
	{
		Label livesText = new Label("Lives Left:");
		livesLeft = new Label("3");
		this.add(livesText);
		this.add(livesLeft);
		//styles
		livesText.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		livesLeft.getAllStyles().setPadding(RIGHT, 4);
		livesLeft.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
	}

	private void createSound()
	{
		Label soundText = new Label("Sound:");
		sound = new Label("ON");
		this.add(soundText);
		this.add(sound);
		//styles
		soundText.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		sound.getAllStyles().setPadding(RIGHT, 4);
		sound.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
	}

	private void createHealthLevel()
	{
		Label healthLevelText = new Label("Health Level:");
		antHealthLevel = new Label("10");
		this.add(healthLevelText);
		this.add(antHealthLevel);
		//styles
		healthLevelText.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		antHealthLevel.getAllStyles().setPadding(RIGHT, 4);
		antHealthLevel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
	}
	private void createFoodLevel()
	{
		Label foodLevelText = new Label("Food Level:");
		antFoodLevel = new Label("5");
		this.add(foodLevelText);
		this.add(antFoodLevel);
		//styles
		foodLevelText.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		antFoodLevel.getAllStyles().setPadding(RIGHT, 4);
		antFoodLevel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
	}


	private void createLastFlagReached()
	{
		Label lastFlagReachedText = new Label("Last Flag Reached:");
		lastFlagReached = new Label("1");
		this.add(lastFlagReachedText);
		this.add(lastFlagReached);
		//styles
		lastFlagReachedText.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		lastFlagReached.getAllStyles().setPadding(RIGHT, 4);
		lastFlagReached.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));	
	}

	@Override
	public void update (Observable gameWorld, Object gc) {
		// code here to output a view based on the data in the Observable

		GameWorld gw=(GameWorld) gameWorld;
		Ant ant=Ant.getAnt();
		clock.setText(Integer.toString(gw.getClock()));
		livesLeft.setText(Integer.toString(gw.getLivesLeft()));
		lastFlagReached.setText(Integer.toString(ant.getLastFlagReached()));
		antFoodLevel.setText( Integer.toString(ant.getFoodLevel()));
		antHealthLevel.setText( Integer.toString(ant.getHealthLevel()));
		
		if(gw.getFlag()) {
			sound.setText("OFF");
			System.out.println("Sound is : OFF ");
		}
		else {
			sound.setText("ON");
			System.out.println("Sound is : ON ");

		}

		this.repaint();
		

	} 































}

