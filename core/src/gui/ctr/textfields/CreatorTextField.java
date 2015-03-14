package gui.ctr.textfields;

import gui.ctr.Main;

import com.badlogic.gdx.math.Vector2;

import lib.gui.CorniceLabeledField;
import lib.gui.StudiumComponent;
import lib.gui.StudiumTextField;

public class CreatorTextField extends CorniceLabeledField{

	//Constructor
	public CreatorTextField(float x, float y, String labelText)
	{
		super(x, y, 140, 26);
		setFont(Main.font);
		setTextOffset(new Vector2(5, 24));
		setLabelOffset(new Vector2(0, 14));
		setLabelText(labelText);
	}
	
}
