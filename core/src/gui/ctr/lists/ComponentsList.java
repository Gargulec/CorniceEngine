package gui.ctr.lists;

import gui.ctr.Main;
import gui.ctr.app.component.CreatorComponent;
import gui.ctr.screens.ScreenEditor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import lib.gui.CorniceList;

public class ComponentsList extends CreatorList{

	//Constructor
	public ComponentsList(float x, float y) 
	{
		super(x, y);
		setFont(Main.font);
		setOffset(new Vector2(5, -20));
		setGapSize(5);
		setElementTexture(new Texture(Gdx.files.internal("element.png")));
		setMaxElements(18);
		setWrap(true);
		setMaxColumns(2);
	}
	
	//Draw
	public void draw(SpriteBatch batch, ShapeRenderer renderer) 
	{
		setElements(ScreenEditor.editedScreen.getComponents());
		
		if(ScreenEditor.selectedComponent == null)
			setSelected(null);
		
		super.draw(batch, renderer);
	}
	//Drawing element
	public void drawElement(Object element, SpriteBatch batch, ShapeRenderer renderer, Vector2 position) 
	{
		//Checking if element is selected
		if((CreatorComponent)element == ScreenEditor.selectedComponent)
			setSelected(element);
		
		super.drawElement(element, batch, renderer, position);
	
		CreatorComponent c = (CreatorComponent)element;
		
		//Name
		getFont().draw(batch, c.getName(), position.x + 14, position.y);
	}
	
	//Element clicked
	public void elementClicked(Object element, int button)
	{
		ScreenEditor.selectedComponent = (CreatorComponent)element;
		ScreenEditor.componentSelected();
	}
}
