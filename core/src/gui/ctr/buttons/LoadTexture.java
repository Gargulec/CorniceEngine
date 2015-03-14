package gui.ctr.buttons;

import com.badlogic.gdx.graphics.Texture;

import gui.ctr.screens.ScreenEditor;

public class LoadTexture extends CreatorButton{

	//Constructor
	public LoadTexture(float x, float y)
	{
		super(x, y, "Load");
		
		tex = getBgImg();
	}
	
	//Clicked
	Texture tex;
	public void clicked(int button) 
	{
		super.clicked(button);
		
		ScreenEditor.fileChooser.toggle();
		
		if(ScreenEditor.fileChooser.isVisible())
			setBgImg(clicked);
		else
			setBgImg(tex);
		
	}

	
}
