package com.mycompany.a3;


import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandAccelerate extends Command{
		private GameWorld gw;
		
		
		
		
		/**
		 * Creates a button command to accelerate the ant
		 * @param gw - Reference to game world to invoke appropriate method 
		 */
		public CommandAccelerate(GameWorld gw)
		{
			super("Accelerate");
			this.gw = gw;
		}
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			gw.accelerate();
			gw.notifyObservers(gw.getCollection());
			System.out.println("Ant accelerated!");
		}
		
}
