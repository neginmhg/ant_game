package com.mycompany.a3;


import com.codename1.ui.layouts.BorderLayout;


import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.geom.Dimension;

//game class is a controller
public class Game extends Form implements Runnable{
	private GameWorld gw; // model,it holds the programs' data
	private MapView mv;
	private ScoreView sv;
	private UITimer timer;
	private BGSound bgSound;
	private int ticking=20;
	private boolean play=true;
	


	//	commands
	CommandAccelerate accelerateCmd;
	CommandLeft leftCmd;
	CommandBrake brakeCmd ;
	CommandRight rightCmd ;
	CommandPosition positionCmd ;
	
	//GameButtons	
	private GameButton btnAccelerate;
	private GameButton btnLeft;
	private GameButton btnBrake;
	private GameButton btnRight;
	private GameButton	pauseGame;
	private GameButton	btnPosition;
	private CheckBox soundCb ;
	
	// constructor for game
	public Game() {

		this.gw = new GameWorld();
		mv = new MapView(gw);
		sv = new ScoreView();

		
		
		gw.init(); // set the initial state of the Game
		// registering map view and score view as observers
		gw.addObserver(mv);
		gw.addObserver(sv);

	
		this.setLayout(new BorderLayout()); // border layout for the entire screen

		
		this.add(BorderLayout.NORTH, sv); // adding score view to the screen
		this.add(BorderLayout.CENTER, mv); // adding map view to the screen

		
		
		setUp(); // creating the game on the screen and adding 3 containers to the screen
		
		
	
//		gw.init(); // set the initial state of the Game
		this.show();
		
		// code here to query MapView’s width and height and set them as world’s
		// width and height


		revalidate(); 
		bgSound = new BGSound("background.wav");
		bgSound.play();
		
		timer = new UITimer(this);
		timer.schedule(ticking, true, this);
		
		
		
		
		
	
	}

	@Override
	public void run() {
		
		
		Dimension d = new Dimension ();
		d.setHeight((int) mv.getMapHeight());
		d.setWidth((int) mv.getMapWidth());		
		gw.clockTick((double)ticking,d);				
		gw.notifyObservers();
		
	}
	public void pauseOrPlay(){
		play=!play;
		
		
		
		btnAccelerate.setEnabled(!btnAccelerate.isEnabled());
		btnLeft.setEnabled(!btnLeft.isEnabled());
		btnBrake.setEnabled(!btnBrake.isEnabled());
		btnRight.setEnabled(!btnRight.isEnabled());
		soundCb.setEnabled(!soundCb.isEnabled());
	
		
		// adding sound button to the toolbar
				
		
		if(play) {
//			play the game
			timer.schedule(ticking, true, this);
			pauseGame.setText("Pause");
			if(gw.getFlag() ==false) {
			bgSound.play();
			}
			this.addKeyListener('a', accelerateCmd);
			this.addKeyListener('l', leftCmd);
			this.addKeyListener('b', brakeCmd);
			this.addKeyListener('r', rightCmd);
			gw.DeSelectObjects();
			btnPosition.setEnabled(false);
		}
		else {
			
			//paused
			timer.cancel();
			pauseGame.setText("Play");
			bgSound.pause();
			//remove action listeners
			btnPosition.setEnabled(true);
			this.removeKeyListener('a', accelerateCmd);
			this.removeKeyListener('l', leftCmd);
			this.removeKeyListener('b', brakeCmd);
			this.removeKeyListener('r', rightCmd);
		}
		
		
	}
	
	/*
	 * code here to create Command objects for each command, add commands to side
	 * menu and title bar area, bind commands to keys, create control containers for
	 * the buttons, add buttons to the control containers, add commands to the
	 * buttons, and add control containers
	 */
	private void setUp() {

		
		
		gw.createSounds();
		/*
		 * 
		 * 
		 * 
		 * WEST
		 * 
		 * 
		 */
		/* West Container creation */
		Container westContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		westContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		Label empty = new Label("  ");
		westContainer.add(empty);
		/* West Container creation */

		/* Add accelerate button */
		 accelerateCmd = new CommandAccelerate(gw);
		 btnAccelerate = new GameButton(accelerateCmd);
		westContainer.add(btnAccelerate);
		addKeyListener('a', accelerateCmd);
		/* Add accelerate button */

		/* Add left button */
		 leftCmd = new CommandLeft(gw);
		 btnLeft = new GameButton(leftCmd);
		westContainer.add(btnLeft);
		addKeyListener('l', leftCmd);
		/* Add left button */

		// add the west container to the screen at the left side
		this.addComponent(BorderLayout.WEST, westContainer);

		/*
		 * 
		 * 
		 * 
		 * EAST
		 * 
		 * 
		 */

		/* EAST Container creation */
		Container eastContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		eastContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		Label emptyy = new Label("  ");
		eastContainer.add(emptyy);
		/* EAST Container creation */

		/* Add brake button */
		 brakeCmd = new CommandBrake(gw);
		 btnBrake = new GameButton(brakeCmd);
		eastContainer.add(btnBrake);
		addKeyListener('b', brakeCmd);
		/* Add brake button */

		/* Add right button */
		 rightCmd = new CommandRight(gw);
		 btnRight = new GameButton(rightCmd);
		eastContainer.add(btnRight);
		addKeyListener('r', rightCmd);
		/* Add right button */

		// add the east container to the screen at the right side
		this.addComponent(BorderLayout.EAST, eastContainer);

		/*
		 * 
		 * 
		 * 
		 * bottom
		 * 
		 * 
		 */

		/* south Container creation */
		Container bottomContainer = new Container(new FlowLayout(CENTER));
		bottomContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		/* south Container creation */
		
		
		/* position button */
		 positionCmd = new CommandPosition(mv,this,gw);
		btnPosition = new GameButton(positionCmd);
		bottomContainer.add(btnPosition);
		btnPosition.setEnabled(false);
		/* position button */
		
		
		/* Pause button */
		CommandPause pauseCmd = new CommandPause(this);
		pauseGame = new GameButton(pauseCmd);
		bottomContainer.add(pauseGame);
		/* Pause button */

		

		// add the south container to the screen at the south side
		this.addComponent(BorderLayout.SOUTH, bottomContainer);

		/*
		 * 
		 * 
		 * 
		 * SIDE MENU using Toolbar
		 * 
		 * 
		 */
		Toolbar menu = new Toolbar();
		this.setToolbar(menu);
		menu.setTitle("ThePath Game");

		// adding accelerate button to the toolbar
		menu.addCommandToSideMenu(accelerateCmd);

		// adding sound button to the toolbar
		soundCb = new CheckBox("Sound");
		soundCb.getAllStyles().setBgTransparency(255);
		soundCb.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		soundCb.setSelected(true);
		CommandSound soundCmd = new CommandSound(gw,this, soundCb);
		soundCb.setCommand(soundCmd);
		menu.addComponentToSideMenu(soundCb);

		// adding sbout button to the toolbar
		CommandAbout aboutCmd = new CommandAbout(gw);
		menu.addCommandToSideMenu(aboutCmd);

		// adding exit button to the toolbar
		CommandExit exitCmd = new CommandExit(gw);
		menu.addCommandToSideMenu(exitCmd);

		// adding help button to the top right side
		CommandHelp helpCmd = new CommandHelp(gw);
		menu.addCommandToRightBar(helpCmd);

	}

	/*
	 * Accessors for gameworld
	 */
	public GameWorld getGw() {
		return gw;
	}

	public void setGw(GameWorld gw) {
		this.gw = gw;
	}
	
	
	public BGSound getBgSound() {
		return bgSound;
	}


	public void setBgSound(boolean on)
	{
		if (on) 	bgSound.play();
		
		else		bgSound.pause();
	
	}
	
	
public boolean isPlay() {
		return play;
	}

	public void setPlay(boolean play) {
		this.play = play;
	}


	

}
