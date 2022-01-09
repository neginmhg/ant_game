package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.Game;

public class CommandPause extends Command
{
	Game g;
	
	/**
	 * Used to pause and unpause the game.
	 * @param g - Reference to the game form where buttons are located
	 */
	public CommandPause(Game g)
	{
		super("Pause");
		this.g = g;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		g.pauseOrPlay();
	}
}