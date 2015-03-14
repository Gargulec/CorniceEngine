package gui.ctr.buttons;

import static gui.ctr.Main.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import lib.gui.StudiumComponent;

public class CreatorButton extends StudiumComponent{

	//Text
	private String text = "";

	//Constructor
	public CreatorButton(float x, float y, String text) 
	{
		super(x, y, 0, 0);
		
		setText(text);
		setBgImg(new Texture(Gdx.files.internal("button.png")));
		
		setWidth(getBgImg().getWidth());
		setHeight(getBgImg().getHeight());
		
		//Loading big button texture
		if(font.getBounds(getText()).width > getWidth())
		{
			setBgImg(new Texture(Gdx.files.internal("buttonBig.png")));
			setWidth(getBgImg().getWidth());
		}
	}
	
	//Draw
	Texture clicked = new Texture(Gdx.files.internal("buttonClicked.png")); 
	public void draw(SpriteBatch batch, ShapeRenderer renderer)
	{
		super.draw(batch, renderer);
		
		//Drawing text
		font.setColor(Color.WHITE);
		float tWidth = font.getBounds(getText()).width;
		font.draw(batch, getText(), getX() + width/2 - tWidth/2, getY() + 4);
	}

	//Getters & Setters
	public String getText()
	{
		return this.text;
	}
	public void setText(String text)
	{
		this.text = text;
	}
	
}
