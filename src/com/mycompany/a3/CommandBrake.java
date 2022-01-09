package com.mycompany.a3;


import com.codename1.ui.Command;

import com.codename1.ui.events.ActionEvent;

public class CommandBrake extends Command{
		private GameWorld gw;
		
		
		
		
		/**
		 * Creates a button command to accelerate the ant
		 * @param gw - Reference to game world to invoke appropriate method 
		 */
		public CommandBrake(GameWorld gw)
		{
			super("Brake");
			this.gw = gw;
		}
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			gw.brake();
			gw.notifyObservers(gw.getCollection());
			System.out.println("brake is applied to the ant");
		}
		
}