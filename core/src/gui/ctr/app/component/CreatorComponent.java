package gui.ctr.app.component;

import static gui.ctr.screens.ScreenEditor.*;
import static gui.ctr.UserInterface.*;
import gui.ctr.UserInterface;
import gui.ctr.screens.ScreenEditor;
import lib.gui.StudiumComponent;
import lib.gui.StudiumLabel;
import lib.gui.StudiumTextField;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class CreatorComponent {

	//Name
	private String name = "Component";
	//Position
	private Vector2 position = new Vector2();
	//Size
	private int width, height;
	//Texture
	private Texture texture;
	//Attached component
	private StudiumComponent component;
	
	//Constructor
	public CreatorComponent(Vector2 position, int width, int height, Texture texture, StudiumComponent component) 
	{
		setPosition(position);
		setWidth(width);
		setHeight(height);
		setTexture(texture);
		
		if(texture != null)
			component.setBgImg(texture);
		
		setComponent(component);
		
		if (component instanceof StudiumLabel)
			setName("Label");
		if (component instanceof StudiumTextField)
			setName("TextField");
	}
	
	//Update
	public void update(SpriteBatch batch, ShapeRenderer renderer)
	{
		if(component != null)
		{
			//Posiiton
			component.setX(getPositionFixed().x);
			component.setY(getPositionFixed().y);
			//Size
			component.setWidth(width);
			component.setHeight(height);
			
			//Texture
			if(selectedComponent == this){
				if(fileChooser.isVisible() && fileChooser.getChoosed() != null){
					if(fileChooser.getChoosed().extension().equals("png") || fileChooser.getChoosed().extension().equals("jpg")){
						setTexture(new Texture(fileChooser.getChoosed()));
						fileChooser.setChoosed(null);
					}
				}
			}
			component.setBgImg(getTexture());
			
			//Moving with mouse
			float mX = Gdx.input.getX();
			float mY = Gdx.graphics.getHeight() - Gdx.input.getY();
			if(selectedComponent == this && mX <= wWidth && !fileChooser.isVisible())
			{
				if(Gdx.input.isTouched())
					setPosition(new Vector2(Gdx.input.getX() - getZeroPoint().x - grabPoint.x, Gdx.graphics.getHeight() - Gdx.input.getY() - getZeroPoint().y - grabPoint.y));
			}
			
			//Drawing component
			if(component instanceof StudiumTextField)
				((StudiumTextField)component).draw(batch, renderer);
			else
				component.draw(batch, renderer);
			
			//Drawing bounds
			if(drawBounds)
			{
				batch.end();
				Gdx.gl.glEnable(GL20.GL_BLEND);
			    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
				renderer.begin(ShapeType.Line);
				renderer.setColor(new Color(1, 1, 1, 1));
				renderer.rect(getPositionFixed().x, getPositionFixed().y, getWidth(), getHeight());
				renderer.end();
				if(selectedComponent == this)
				{
					renderer.begin(ShapeType.Filled);
					renderer.setColor(new Color(1, 1, 1, .32f));
					renderer.rect(getPositionFixed().x, getPositionFixed().y, getWidth(), getHeight());
					renderer.end();
				}
				Gdx.gl.glDisable(GL20.GL_BLEND);
				batch.begin();
			}
		}
	}
	
	//Component clicked
	public void clicked()
	{
		ScreenEditor.componentName.setText(getName());
		ScreenEditor.componentX.setText(""+getPosition().x);
		ScreenEditor.componentY.setText(""+getPosition().y);
		ScreenEditor.compWidth.setText(""+getWidth());
		ScreenEditor.compHeight.setText(""+getHeight());
	}
	
	//Getters & Setters
	public String getName() 
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
	public Vector2 getPosition() 
	{
		return this.position;
	}
	public Vector2 getPositionFixed() 
	{
		return new Vector2(position.x + getZeroPoint().x, position.y + getZeroPoint().y);
		
	}
	public void setPosition(Vector2 position) 
	{
		this.position = position;
	}

	public StudiumComponent getComponent() 
	{
		return component;
	}
	public void setComponent(StudiumComponent component) 
	{
		this.component = component;
	}
	
	public int getWidth() 
	{
		return width;
	}
	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight()
	{
		return height;
	}
	public void setHeight(int height) 
	{
		this.height = height;
	}

	public Texture getTexture() 
	{
		return texture;
	}
	public void setTexture(Texture texture) 
	{
		this.texture = texture;
	}

}
