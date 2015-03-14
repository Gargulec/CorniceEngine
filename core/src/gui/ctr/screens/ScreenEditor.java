package gui.ctr.screens;

import javafx.stage.FileChooser;
import gui.ctr.Main;
import gui.ctr.UserInterface;
import gui.ctr.app.component.CreatorComponent;
import gui.ctr.app.screen.CreatorScreen;
import gui.ctr.buttons.AddButton;
import gui.ctr.buttons.AddComponent;
import gui.ctr.buttons.AddLabel;
import gui.ctr.buttons.AddTextField;
import gui.ctr.buttons.CreatorButton;
import gui.ctr.buttons.LoadTexture;
import gui.ctr.lists.ComponentsList;
import gui.ctr.lists.CreatorList;
import gui.ctr.textfields.CompText;
import gui.ctr.textfields.CompTextX;
import gui.ctr.textfields.CompTextY;
import gui.ctr.textfields.FieldCompName;
import gui.ctr.textfields.FieldCompX;
import gui.ctr.textfields.FieldCompY;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

import lib.gui.CorniceLabeledField;
import lib.gui.StudiumComponent;
import lib.gui.StudiumLabel;
import lib.gui.StudiumScreen;
import lib.gui.StudiumTextField;
import lib.gui.chooser.CorniceFileChooser;

public class ScreenEditor extends StudiumScreen{

	//Draw bounds?
	public static boolean drawBounds = true;
	
	//Edited screen
	public static CreatorScreen editedScreen;
	
	//Selected component
	public static CreatorComponent selectedComponent;
	public static Vector2 grabPoint = Vector2.Zero;
	
	//Workspace
	public static float wWidth, wHeight;
	/**TOOLBAR**/
	public static StudiumComponent toolbar;
		//Add
		public static StudiumComponent addToolbar;
		public static AddComponent addComponent;
		public static AddLabel addLabel;
		public static AddTextField addTextField;
		public static AddButton addButton;
	/**RIGHT PANEL**/
	public static StudiumComponent rightPanel;
		//Properities
		public static FieldCompName componentName;
		public static FieldCompX componentX;
		public static FieldCompY componentY;
		public static ComponentsList componentsList;
		public static LoadTexture loadTexture;
		public static CompText compText;
		public static CompTextX compTextX;
		public static CompTextY compTextY;
		
	public static CorniceFileChooser fileChooser;
		
	//Constructor
	public ScreenEditor()
	{
		//Toolbar
		toolbar = new StudiumComponent(0, Gdx.graphics.getHeight() - 50, 100, 50);
		//Button - Add Component
		addComponent = new AddComponent(10, 10);
		toolbar.add(addComponent);
		//Add Component Toolbar
		addToolbar = new StudiumComponent(0, Gdx.graphics.getHeight() - 100, 100, 50);
		addToolbar.setBgColor(new Color(.6f, .6f, .6f, 1));
		addToolbar.setVisible(false);
		//Button - Add Label
		addLabel = new AddLabel(10, 10);
		addToolbar.add(addLabel);
		//Button - Add Text Field
		addTextField = new AddTextField(140, 10);
		addToolbar.add(addTextField);
		//Button - Add Button
		addButton = new AddButton(270, 10);
		addToolbar.add(addButton);
		add(toolbar);
		//Right panel
		rightPanel = new StudiumComponent(Gdx.graphics.getWidth() - 200, 0, 200, Gdx.graphics.getHeight());
		componentName = new FieldCompName(10, 10);
		componentX = new FieldCompX(10, 110);
		componentY = new FieldCompY(10, 60);
		componentName.setActive(false);
		componentX.setActive(false);
		componentY.setActive(false);
		rightPanel.add(componentName);
		rightPanel.add(componentX);
		rightPanel.add(componentY);
		//Load Texture button
		loadTexture = new LoadTexture(10, 160);
		rightPanel.add(loadTexture);
		rightPanel.add(new StudiumLabel(10, 183, "Texture", Main.font));
		//Component text
		compText = new CompText(10, 213);
		compText.setVisible(false);
		rightPanel.add(compText);
		//Component text offset
		compTextX = new CompTextX(10, 263);
		compTextX.setVisible(false);
		rightPanel.add(compTextX);
		compTextY = new CompTextY(60, 263);
		compTextY.setVisible(false);
		rightPanel.add(compTextY);
		//List of components
		componentsList = new ComponentsList(10, rightPanel.getHeight() - 110);
		rightPanel.add(componentsList);
		
		fileChooser = new CorniceFileChooser(Gdx.graphics.getWidth()/2 - 200, Gdx.graphics.getHeight()/2 - 150);
		fileChooser.setVisible(false);
		fileChooser.setFilter("jpg, txt");
		add(fileChooser);
		
		add(addToolbar);
		add(rightPanel);
	}
	
	//Component selected
	public static void componentSelected()
	{
		if(selectedComponent == null)
		{
			componentName.setText("");
			componentName.setActive(false);
			componentX.setText("");
			componentX.setActive(false);
			componentY.setText("");
			componentY.setActive(false);
			
			compText.setVisible(false);
			compTextX.setVisible(false);
			compTextY.setVisible(false);
		}
		else
		{
			componentName.setActive(true);
			componentX.setActive(true);
			componentY.setActive(true);
			
			
			compTextX.setVisible(true);
			compTextY.setVisible(true);
			
			componentName.setText(selectedComponent.getName());
			//Compontent Text
			if(selectedComponent.getComponent() instanceof StudiumLabel)
			{
				compText.setVisible(true);
				compText.setText(((StudiumLabel)selectedComponent.getComponent()).getText());
			}
			else
				compText.setVisible(false);
			//Component Text offset
			if(selectedComponent.getComponent() instanceof StudiumTextField)
			{
				compTextX.setVisible(true);
				compTextX.setText(""+((StudiumTextField)selectedComponent.getComponent()).getTextOffset().x);
				compTextY.setVisible(true);
				compTextY.setText(""+((StudiumTextField)selectedComponent.getComponent()).getTextOffset().y);
			}
			else{
				compTextX.setVisible(false);
				compTextY.setVisible(false);
			}
		}
	}
	
	//Resizing app window
	public void resize(int width, int height) 
	{
		super.resize(width, height);
		
		//Right panel
		int w = (int)(0.18 * Gdx.graphics.getWidth());
		if(w > 180)
			rightPanel.setWidth(w);
		else
		{
			rightPanel.setWidth(300);
			w = 180;
		}
		rightPanel.setX(Gdx.graphics.getWidth() - w);
		rightPanel.setHeight(Gdx.graphics.getHeight());
		//Components list
		componentsList.setWidth(w - 20);
		componentsList.setY(Gdx.graphics.getHeight() - componentsList.getHeight() - 10);
		if(componentsList.getWidth() > 200)
			componentsList.setMaxColumns(2);
		else
			componentsList.setMaxColumns(1);
		//Toolbar
		toolbar.setY(Gdx.graphics.getHeight() - toolbar.getHeight());
		toolbar.setWidth(Gdx.graphics.getWidth() - w);
		//Add Components Toolbar
		addToolbar.setY(Gdx.graphics.getHeight() - toolbar.getHeight() - addToolbar.getHeight());
		addToolbar.setWidth(Gdx.graphics.getWidth() - w);
		//Workspace
		wWidth = Gdx.graphics.getWidth() - w;
		wHeight = Gdx.graphics.getHeight();
		//File chooser
		fileChooser.setX(Gdx.graphics.getWidth()/2 - fileChooser.getWidth()/2);
		fileChooser.setY(Gdx.graphics.getHeight()/2 - fileChooser.getHeight()/2);
	}
	
	//Draw
	public void draw() 
	{
		
		batch.end();
		shapeRenderer.begin(ShapeType.Filled);
		//Drawing editor background
		shapeRenderer.setColor(new Color(.9f, .9f, .85f, 1));
		shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//Drawing screen
		shapeRenderer.setColor(Color.BLACK);
		shapeRenderer.rect(wWidth/2 - UserInterface.width/2, wHeight/2 - UserInterface.height/2, UserInterface.width, UserInterface.height);
		shapeRenderer.end();
		batch.begin();

		//Drawing & Updating components
		if(editedScreen != null)
			for(CreatorComponent c : editedScreen.getComponents())
				c.update(batch, shapeRenderer);
		
		super.draw();
		
	}
	
	//Screen clicked
	public void screenClicked(int button) 
	{
		if(Gdx.input.getX() <= wWidth)
			selectedComponent = null;
		
		float mX = Gdx.input.getX();
		float mY = Gdx.graphics.getHeight() - Gdx.input.getY();
		
		//Drawing & Updating components
		if(editedScreen != null)
		{
			for(CreatorComponent c : editedScreen.getComponents())
			{
				//Unfocusing
				if(c.getComponent() instanceof StudiumTextField)
					((StudiumTextField)c.getComponent()).setFocused(false);
				
				if(mX > c.getPositionFixed().x && mX < c.getPositionFixed().x + c.getWidth())
				{
					if(mY > c.getPositionFixed().y && mY < c.getPositionFixed().y + c.getHeight())
					{
						c.clicked();
						selectedComponent = c;
						grabPoint = new Vector2(mX - c.getPositionFixed().x, mY - c.getPositionFixed().y);
					}
				}
			}
		}
		
		componentSelected();
		
		super.screenClicked(button);
	}
}
