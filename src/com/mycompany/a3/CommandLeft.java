package com.mycompany.a3;


import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;


public class CommandLeft extends Command{
	private GameWorld gw;
	
	
	
	
	/**
	 * Creates a button command to accelerate the ant
	 * @param gw - Reference to game world to invoke appropriate method 
	 */
	public CommandLeft(GameWorld gw)
	{
		super("Left");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		gw.changeAntHeading('l');	//go left
		
		System.out.println("Ant heading changed to left");
		gw.notifyObservers(gw.getCollection());
	}
	
}






