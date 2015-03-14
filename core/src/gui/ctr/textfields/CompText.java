package gui.ctr.textfields;

import static gui.ctr.screens.ScreenEditor.selectedComponent;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import lib.gui.StudiumComponent;
import lib.gui.StudiumLabel;

public class CompText extends CreatorTextField{

	//Constructor
	public CompText(float x, float y) 
	{
		super(x, y, "Text");
	}
	
	//Text typed
	public void textTyped() 
	{
		if(selectedComponent.getComponent() instanceof StudiumLabel)
		{
			((StudiumLabel)selectedComponent.getComponent()).setText(getText());
		}
	}

}
