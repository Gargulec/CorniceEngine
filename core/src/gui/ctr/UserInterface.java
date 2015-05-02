package gui.ctr;

import gui.ctr.app.export.Export;
import gui.ctr.app.screen.CreatorScreen;
import gui.ctr.screens.ScreenEditor;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class UserInterface {

	//Is game playing
	public static boolean isRunning = false;
	
	//Window size
	public static int width = 600, height = 400;
	//List of screens
	public static ArrayList<CreatorScreen> screens = new ArrayList<CreatorScreen>();
	
	//Point 0,0 on UI
	public static Vector2 getZeroPoint()
	{
		return new Vector2((ScreenEditor.wWidth/2 - UserInterface.width/2), (ScreenEditor.wHeight/2 - UserInterface.height/2));
	}
	
	//Running game
	public static void run()
	{
		if(!isRunning)
		{
			isRunning = true;
			
			if(!Export.createApp())
				isRunning = false;
		}
	}
}
