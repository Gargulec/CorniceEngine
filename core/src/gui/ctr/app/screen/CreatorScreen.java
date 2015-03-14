package gui.ctr.app.screen;

import gui.ctr.app.component.CreatorComponent;

import java.util.ArrayList;

public class CreatorScreen {

	//Name
	private String name;
	//List of components
	private ArrayList<CreatorComponent> components = new ArrayList<CreatorComponent>();

	//Add new component
	public void add(CreatorComponent component)
	{
		components.add(component);
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
	
	public ArrayList<CreatorComponent> getComponents()
	{
		return components;
	}
	public void setComponents(ArrayList<CreatorComponent> components)
	{
		this.components = components;
	}

}
