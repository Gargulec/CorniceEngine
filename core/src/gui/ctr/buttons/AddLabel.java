package gui.ctr.buttons;

import gui.ctr.UserInterface;
import gui.ctr.app.component.CreatorComponent;
import lib.gui.StudiumLabel;

import com.badlogic.gdx.math.Vector2;

public class AddLabel extends CreatorButton{

	//Constructor
	public AddLabel(float x, float y)
	{
		super(x, y, "Label");
	}

	//Clicked
	public void clicked(int button) 
	{
		super.clicked(button);
	
		UserInterface.screens.get(0).add(new CreatorComponent(new Vector2(100, 100), 120, 30, null, new StudiumLabel(0, 0, "Test")));
	}
	
}
