package gui.ctr.textfields;

import static gui.ctr.screens.ScreenEditor.selectedComponent;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import lib.gui.StudiumComponent;

public class FieldCompName extends CreatorTextField{

	//Constructor
	public FieldCompName(float x, float y) 
	{
		super(x, y, "Name");
		setText("");
	}
	
	//Text typed
	public void textTyped() 
	{
		selectedComponent.setName(getText());
	}

}
