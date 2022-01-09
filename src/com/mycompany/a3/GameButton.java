package com.mycompany.a3;


import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.plaf.Border;

public class GameButton extends Button {

	public GameButton(Command c) {

		this.getAllStyles().setBgTransparency(255);
		this.getAllStyles().setBorder(Border.createBevelRaised());
		this.getAllStyles().setBorder(Border.createBevelLowered());
		this.getAllStyles().setBorder(Border.createDoubleBorder(2, ColorUtil.BLACK));

		this.getAllStyles().setMargin(TOP, 1);
		this.getAllStyles().setMargin(BOTTOM, 1);

		this.getAllStyles().setPadding(TOP, 5);
		this.getAllStyles().setPadding(BOTTOM, 5);
		this.getAllStyles().setPadding(LEFT, 3);
		this.getAllStyles().setPadding(RIGHT, 3);

		this.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		this.getUnselectedStyle().setFgColor(ColorUtil.WHITE);

		this.getPressedStyle().setBgTransparency(255 / 2);
		this.getPressedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		this.getPressedStyle().setFgColor(ColorUtil.BLUE);	
		
		this.getDisabledStyle().setBgTransparency(255);
		this.getDisabledStyle().setBgColor(ColorUtil.WHITE);
		this.getDisabledStyle().setFgColor(ColorUtil.BLUE);


		this.setFocusable(false);

		this.setCommand(c);
		
		

	}
}
