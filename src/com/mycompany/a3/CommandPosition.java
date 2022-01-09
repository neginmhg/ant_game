package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandPosition extends Command {

	MapView mv;
	Game game;
	GameWorld gw;

	public CommandPosition(MapView mv, Game game, GameWorld gw) {

		super("Position");
		this.mv = mv;
		this.game = game;
		this.gw = gw;
	}

	public void actionPerformed(ActionEvent e) {

		if (game.isPlay() == false) { // if game pause

			IIterator theElements = gw.getCollection().getIterator();

			while (theElements.hasNext()) {
				GameObject selectedObj = (GameObject) theElements.getNext();
				if (selectedObj instanceof Flag) {
					Flag f = (Flag) selectedObj;

					if (f.isSelected()) {

						mv.setRedraw(true);
					}
				}
				if (selectedObj instanceof FoodStation) {
					FoodStation fs = (FoodStation) selectedObj;

					if (fs.isSelected()) {

						mv.setRedraw(true);
					}
				}

			}
			System.out.println("position button pressed");
		}

	}

}
