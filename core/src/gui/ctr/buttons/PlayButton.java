package gui.ctr.buttons;

import gui.ctr.UserInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class PlayButton extends CreatorButton{

	//Constructor
	public PlayButton(float x, float y)
	{
		super(x, y, "Play");
		
		setBgImg(new Texture(Gdx.files.internal("buttonPlay.png")));
		tex = getBgImg();
		playClicked = new Texture(Gdx.files.internal("playClicked.png"));
	}
	
	//Clicked
	Texture tex, playClicked;
	public void clicked(int button) 
	{
		super.clicked(button);
		
		UserInterface.run();
		
		if(UserInterface.isRunning)
			setBgImg(playClicked);
		else
			setBgImg(tex);
	}

	
}
