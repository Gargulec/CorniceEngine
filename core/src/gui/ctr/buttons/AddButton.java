package gui.ctr.buttons;

import lib.gui.StudiumComponent;
import gui.ctr.UserInterface;
import gui.ctr.app.component.CreatorComponent;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class AddButton extends CreatorButton{

	//Constructor
	public AddButton(float x, float y)
	{
		super(x, y, "Button");
	}

	//Clicked
	Texture tex;
	public void clicked(int button) 
	{
		super.clicked(button);
		
		UserInterface.screens.get(0).add(new CreatorComponent(new Vector2(100, 100), 120, 30, null, new StudiumComponent(0, 0, 0, 0)));		
	}
	
}
