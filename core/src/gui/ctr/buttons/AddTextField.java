package gui.ctr.buttons;

import lib.gui.CorniceLabeledField;
import lib.gui.StudiumTextField;
import gui.ctr.UserInterface;
import gui.ctr.app.component.CreatorComponent;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class AddTextField extends CreatorButton{

	//Constructor
	public AddTextField(float x, float y)
	{
		super(x, y, "Text Field");
	}

	//Clicked
	Texture tex;
	public void clicked(int button) 
	{
		super.clicked(button);
		
		CreatorComponent c = new CreatorComponent(new Vector2(100, 100), 120, 30, null, new CorniceLabeledField(0, 0, 0, 0));
		UserInterface.screens.get(0).add(c);		
	}
	
}
