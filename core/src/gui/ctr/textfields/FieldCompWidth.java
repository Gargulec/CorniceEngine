package gui.ctr.textfields;

import static gui.ctr.screens.ScreenEditor.selectedComponent;

public class FieldCompWidth extends CreatorTextField{

	//Constructor
	public FieldCompWidth(float x, float y) 
	{
		super(x, y, "Size");
		
		setNumberOnly(true);
		setWidth(50);
		setText("");
	}
	
	//Text typed
	public void textTyped() 
	{
		if(selectedComponent != null)
		{
			selectedComponent.setWidth((int)getFloat());
		}
	}

}
