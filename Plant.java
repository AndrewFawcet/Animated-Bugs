package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Plant extends Circle{

	private String name;

	public Plant(String name, double x, double y, double z, Color c) {

		super(x, y, z);
		this.setFill(c);
		this.setName(name);

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	//plants will grow this amount each iteration
	public void plantGrow() {
		this.setRadius(this.getRadius()+1);
	}
	
	
	
}
