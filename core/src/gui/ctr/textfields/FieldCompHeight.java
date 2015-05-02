package gui.ctr.textfields;

import static gui.ctr.screens.ScreenEditor.selectedComponent;

public class FieldCompHeight extends CreatorTextField{

	//Constructor
	public FieldCompHeight(float x, float y) 
	{
		super(x, y, "");
		
		setNumberOnly(true);
		setWidth(50);
		setText("");
	}
	
	//Text typed
	public void textTyped() 
	{
		if(selectedComponent != null)
		{
			selectedComponent.setHeight((int)getFloat());
		}
	}

}
