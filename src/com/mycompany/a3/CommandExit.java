
package com.mycompany.a3;


import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class CommandExit extends Command {
/**
	 * Creates a button command to accelerate the ant
	 * @param gw - Reference to game world to invoke appropriate method 
	 */
	public CommandExit(GameWorld gw)
	{
		super("Exit");
		 
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
	
		if (Dialog.show("Exit", "Are you sure you want to exit?", "Yes", "No"))
		{
			System.exit(0);
		}
	}
}
