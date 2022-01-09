package com.mycompany.a3;


import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class CommandHelp extends Command {
/**
	 * Creates a button command to accelerate the ant
	 * @param gw - Reference to game world to invoke appropriate method 
	 */
	public CommandHelp(GameWorld gw)
	{
		super("Help");
		 
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String text="\n Press 'a' to accellerate \n \n Press 'b' to brake \n \n Press 'l' to turn left \n \n "
				+ "Press 'r' to turn right \n \n ";
	
		Dialog.show("Help", text, "OK",null);
		
	}
}
