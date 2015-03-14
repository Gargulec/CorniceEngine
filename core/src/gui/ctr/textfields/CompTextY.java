package gui.ctr.textfields;

import static gui.ctr.screens.ScreenEditor.selectedComponent;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import lib.gui.StudiumComponent;
import lib.gui.StudiumLabel;
import lib.gui.StudiumTextField;

public class CompTextY extends CreatorTextField{

	//Constructor
	public CompTextY(float x, float y) 
	{
		super(x, y, "");
		
		setNumberOnly(true);
		setWidth(40);
		setText("");
	}
	
	//Text typed
	public void textTyped() 
	{
		if(selectedComponent != null )
		{
			if(selectedComponent.getComponent() instanceof StudiumTextField)
			{
				StudiumTextField tf = ((StudiumTextField)selectedComponent.getComponent());
				tf.setTextOffset(new Vector2(tf.getTextOffset().x, getFloat()));
			}
		}
	}

}
