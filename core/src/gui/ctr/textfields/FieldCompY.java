package gui.ctr.textfields;

import static gui.ctr.screens.ScreenEditor.*;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import lib.gui.StudiumComponent;

public class FieldCompY extends CreatorTextField{

	//Constructor
	public FieldCompY(float x, float y) 
	{
		super(x, y, "Position Y");
		setText("");
		
		setNumberOnly(true);
	}

	//Text typed
	public void textTyped() 
	{
		if(selectedComponent != null)
		{
			float x = selectedComponent.getPosition().x; 
			float y = getFloat();
		
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
				setText(""+selectedComponent.getPosition().y);
	}
	
}
