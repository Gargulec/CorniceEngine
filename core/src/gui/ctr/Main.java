package gui.ctr;

import lib.gui.StudiumLabel;
import lib.gui.StudiumScreen;
import gui.ctr.app.screen.CreatorScreen;
import gui.ctr.screens.ScreenEditor;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Main extends Game
{
	//Font
	public static BitmapFont font;

	//Screens
	ScreenEditor screenEditor;
	
	public void create() 
	{
		font = StudiumLabel.createFontFromTTF(Gdx.files.internal("font.ttf"), 22);

		//Creating screens
		screenEditor = new ScreenEditor();
		
		StudiumScreen.change(this, screenEditor);
		
		UserInterface.screens.add(new CreatorScreen());
		ScreenEditor.editedScreen = UserInterface.screens.get(0);
	}
	

}
