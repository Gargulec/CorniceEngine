package gui.ctr.app.export;

import gui.ctr.UserInterface;
import gui.ctr.app.component.CreatorComponent;
import gui.ctr.app.screen.CreatorScreen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Consumer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;

import lib.gui.CorniceButton;
import lib.gui.CorniceLabeledField;
import lib.gui.StudiumComponent;
import lib.gui.StudiumLabel;
import lib.gui.StudiumTextField;

public class Export {

	static String mainScreen = "";
	
	//Exporting files
	public static void creatingFiles()
	{
		try
		{
			System.err.println("Exporting!");
			
			//Creating folders
			File folder = new File("export");
			folder.mkdir();
			File folder2 = new File("export/game");
			folder2.mkdir();
			File folder3 = new File("export/graphics");
			folder3.mkdir();
			
			//Game .jar
			FileHandle gameJar = Gdx.files.classpath("game.jar");
			
			PrintWriter pw;
			
			//Creating screens
			screens();
			
			//Creating main.java
			File main = new File("export/game/Main.java");
			pw = new PrintWriter(main);
			pw.println("package game;");
			pw.println("import lib.gui.StudiumScreen;");
			pw.println("import com.badlogic.gdx.Game;");
			pw.println("public class Main extends Game");
			pw.println("{");
			pw.println("_mS screen;".replace("_mS", mainScreen));
			pw.println("public void create()");
			pw.println("{");
			pw.println("//Creating screens");
			pw.println("screen = new _mS();".replace("_mS", mainScreen));
			pw.println("StudiumScreen.change(this, screen);");
			pw.println("}");
			pw.println("}");
			pw.close();
			main.createNewFile();
			
			//Copying "game.jar"
			gameJar.copyTo(new FileHandle("export/game.jar"));
			gameJar.copyTo(new FileHandle("export/game/game.jar"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//Creating screens
	static void screens()
	{
		File screen;
		PrintWriter pw;
		
		for(CreatorScreen cs : UserInterface.screens)
		{
			try
			{
				screen = new File("export/game/" + cs.getName() + ".java");
				pw = new PrintWriter(screen);
				String name = cs.getName();
				if(mainScreen == "")
					mainScreen = name;
				pw.println("package game;");
				pw.println("import com.badlogic.gdx.Gdx;");
				pw.println("import com.badlogic.gdx.graphics.Texture;");
				pw.println("import lib.gui.StudiumScreen;");
				pw.println("import lib.gui.StudiumLabel;");
				pw.println("import lib.gui.StudiumTextField;");
				pw.println("import lib.gui.StudiumComponent;");
				pw.println("public class _n extends StudiumScreen{".replace("_n", name));
				//Components
				for(CreatorComponent cc : cs.getComponents())
				{
					StudiumComponent c = cc.getComponent();
					
					//Exporting TEXTURE
					if(c.getBgImg() != null)
					{
						c.getBgImg().getTextureData().prepare();
						PixmapIO.writePNG(new FileHandle("export/graphics/" + cc.getName() + ".png"), c.getBgImg().getTextureData().consumePixmap());
						c.getBgImg().getTextureData().disposePixmap();
					}
					///////////////////
					
					//Label
					if (c instanceof StudiumLabel) 
						pw.println("StudiumLabel " + cc.getName() + ";");
					//TextField
					else if (c instanceof StudiumTextField)
						pw.println("StudiumTextField " + cc.getName() + ";");
					//Button
					else if (c instanceof CorniceButton)
						pw.println("CorniceButton " + cc.getName() + ";");
					//Component
					else
						pw.println("StudiumComponent " + cc.getName() + ";");
				}
				////////////
				pw.println("//Constructor");
				pw.println("public _n() {".replace("_n", name));
				for(CreatorComponent cc : cs.getComponents())
				{
					StudiumComponent c = cc.getComponent();
					
					float x = cc.getPosition().x;
					float y = cc.getPosition().y;
					/**************************/
					//Label
					if (c instanceof StudiumLabel) 
						pw.println(cc.getName() + " = " + "new StudiumLabel(" + x + ", " + y + ", " + ((StudiumLabel)c).getText() + ");");
					//TextField
					else if (c instanceof StudiumTextField)
						pw.println(cc.getName() + " = " + "new StudiumTextField(" + x + ", " + y + ", " + c.getWidth() + ", " + c.getHeight() + ");");
					//Button
					else if (c instanceof CorniceButton)
						pw.println(cc.getName() + " = " + "new CorniceButton(" + x + ", " + y + ", " + c.getWidth() + ", " + c.getHeight() + ", " + ((CorniceButton)c).getText() + ");");
					//Component
					else
						pw.println(cc.getName() + " = " + "new StudiumComponent(" + x + "f, " + y + "f, " + c.getWidth()+"f, " + c.getHeight() +"f" + ");");
					/**************************/
					//Setting TEXTURE
					if(c.getBgImg() != null)
						pw.println(cc.getName()+".setBgImg(new Texture(Gdx.files.internal(\"graphics/"+cc.getName()+".png\")));");
					
					pw.println("add("+cc.getName()+");");
				}
				pw.println("}");
				pw.println("//Draw");
				pw.println("public void draw() {");
				pw.println("super.draw();");
				pw.println("}");
				pw.println("}");
				pw.close();
				screen.createNewFile();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	//Creating app
	public static boolean createApp()
	{
		try
		{
			//Creating files
			creatingFiles();
			
			//Creating compile.bat file
			File compileBat = new File("export/game/compile.bat");
			PrintWriter pw = new PrintWriter(compileBat);
			pw.println("cd export");
			pw.println("cd game");
			pw.println("javac -cp game.jar; Main.java MainScreen.java");
			pw.println("del game.jar Main.java MainScreen.java");
			pw.println("cd ..");
			pw.println("jar -uf game.jar game graphics");
			pw.println("java -jar game.jar");
			pw.println("del Main.class MainScreen.class");
			pw.println("del graphics");
			
			pw.close();
			compileBat.createNewFile();
			
			//Running compile.bat file
			Runtime.getRuntime().exec("export/game/compile.bat");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
}
