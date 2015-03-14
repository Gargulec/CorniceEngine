package gui.ctr.textfields;

import static gui.ctr.screens.ScreenEditor.*;
import gui.ctr.screens.ScreenEditor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import lib.gui.StudiumComponent;

public class FieldCompX extends CreatorTextField{

	//Constructor
	public FieldCompX(float x, float y) 
	{
		super(x, y, "Position X");
		setText("");
		
		setNumberOnly(true);
	}

	//Text typed
	public void textTyped() 
	{
		if(selectedComponent != null)
		{
			float x = getFloat();
			float y = selectedComponent.getPosition().y;
		
			selectedComponent.setPosition(new Vector2(x, y));
		}
		
		super.textTyped();
	}
	
	//Draw
	public void draw(SpriteBatch batch, ShapeRenderer renderer) 
	{
		super.draw(batch, renderer);
	
		if(!isFocused())
			if(selectedComponent != null)
				setText(""+selectedComponent.getPosition().x);
	}
}
