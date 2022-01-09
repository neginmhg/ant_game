
package com.mycompany.a3;


import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class CommandAbout extends Command {
	/**
	 * Creates a button command to accelerate the ant
	 * 
	 * @param gw - Reference to game world to invoke appropriate method
	 */
	public CommandAbout(GameWorld gw) {
		super("About");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String info = "Negin Mahrang ,CSc 133 , Assignement 3";
		Dialog.show("About", info, "Ok", null);
	}

}
