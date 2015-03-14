package gui.ctr.buttons;

import com.badlogic.gdx.graphics.Texture;

import gui.ctr.screens.ScreenEditor;

public class AddComponent extends CreatorButton{

	//Constructor
	public AddComponent(float x, float y)
	{
		super(x, y, "Add...");
		
		tex = getBgImg();
	}
	
	//Clicked
	Texture tex;
	public void clicked(int button) 
	{
		super.clicked(button);
		
		ScreenEditor.addToolbar.toggle();
		
		if(ScreenEditor.addToolbar.isVisible())
			setBgImg(clicked);
		else
			setBgImg(tex);
		
	}

}
