package com.mycompany.a3;


import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;


public class CommandSound extends Command 
{
	private GameWorld gw;
	private Game g;
	private CheckBox ch;
	/**
	 * Creates a side menu command to toggle the sound on and off.
	 * @param gw - Reference to game world to invoke appropriate method
	 * @param cb - Reference to the check box created.
	 */
	public CommandSound(GameWorld gw, Game g,CheckBox ch)
	{
		super("Sound");
		this.gw = gw;
		this.g=g;
		this.ch=ch;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		
		g.setBgSound(ch.isSelected());
		gw.changeFlag();
		gw.notifyObservers(gw.getCollection());
	}
}