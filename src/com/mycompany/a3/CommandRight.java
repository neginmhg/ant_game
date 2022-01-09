package com.mycompany.a3;


import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;


public class CommandRight extends Command{
	private GameWorld gw;
	
	
	
	
	/**
	 * Creates a button command to accelerate the ant
	 * @param gw - Reference to game world to invoke appropriate method 
	 */
	public CommandRight(GameWorld gw)
	{
		super("Right");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{

		gw.changeAntHeading('r');	//go right
		System.out.println("Ant heading changed to right");
		gw.notifyObservers(gw.getCollection());
	}
	
}
