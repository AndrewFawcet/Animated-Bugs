package application;

import java.util.ArrayList;

//package livecoding2022;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bug extends Circle {

//	private int energy;

	private String name;
	private int direction;
	private int hunger;

	public Bug(String name, double x, double y, int h, int direction) {

		super(x, y, 30);
		this.setDirection(direction);
		this.setHunger(h);
		this.setName(name);

	}

	public int getHunger() {
		return hunger;
	}

	public void setHunger(int hunger) {
		this.hunger = hunger;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	//resets bug rotation
	public void resetBugDirection() {

		if (this.getRotate() == 90) {
			this.setRotate(-90);
		}
		if (this.getRotate() == 180) {
			this.setRotate(-180);
		}
		if (this.getRotate() == 270) {
			this.setRotate(-270);
		}

	}

	//rotates the bug image to the direction being moved
	public void bugDirection() {

		if (this.getDirection() == 0) {
			this.setRotate(90);
		} else if (this.getDirection() == 1) {
			this.setRotate(270);
		} else if (this.getDirection() == 2) {
			this.setRotate(180);
		} else if (this.getDirection() == 3) {
			this.setRotate(0);
		}
	}

//	calculates the distance between two objects using pythagoras
	public double distanceBetweenObjects(double x1, double y1, double x2, double y2) {
		double distance = Math
				.sqrt((Math.abs(x1 - x2) * Math.abs(x1 - x2)) + ((Math.abs(y1 - y2) * Math.abs(y1 - y2))));

		return distance;
	}
	
	//Bug eats food, will always eat if on plant	
	public void eatFood(BugMain BM) {
		double bugEatDistance = 0;
		for (Plant p : BM.getPlants()) {
			double distance = distanceBetweenObjects(this.getCenterX(), this.getCenterY(), p.getCenterX(),
					p.getCenterY());
			if (distance < bugEatDistance + p.getRadius()) {
				p.setRadius(p.getRadius()-5);
				this.setHunger(this.getHunger()+5);
			}
		}
		
	}

	// Finds if a plant is within smell range, if so passes onto moveToFood
	public void smellFood(BugMain BM) {
		double bugSmellDistance = 200;
		Plant moveToPlant = null;
		double smallestDistance = BM.getWidth();
		for (Plant p : BM.getPlants()) {
			double distance = distanceBetweenObjects(this.getCenterX(), this.getCenterY(), p.getCenterX(),
					p.getCenterY());
			if ((distance < bugSmellDistance + p.getRadius()) && (distance < smallestDistance)) {
				moveToPlant = p;
				smallestDistance = distance;
			}
		}
		if (moveToPlant != null) {
			this.moveToFood(moveToPlant, BM);
		}
	}

	// moves the bug towards food.
	public void moveToFood(Plant plant, BugMain BM) {
		double ranMove = (int) (5 + Math.random() * 5);
		double xOld = this.getCenterX();
		double yOld = this.getCenterY();

		if (Math.abs(plant.getCenterX() - this.getCenterX()) > Math.abs(plant.getCenterY() - this.getCenterY())) {
			// move on x plane
			if ((plant.getCenterX() - this.getCenterX()) > 0) {
				this.setCenterX(xOld + ranMove);
				this.setDirection(0);
			} else {
				this.setCenterX(xOld - ranMove);
				this.setDirection(1);
			}
		} else {
			if ((plant.getCenterY() - this.getCenterY()) > 0) {
				this.setCenterY(yOld + ranMove);
				this.setDirection(2);
			} else {
				this.setCenterY(yOld - ranMove);
				this.setDirection(3);
			}
		}
		if (!checkMove(BM.getBugs())) {
			this.setCenterX(xOld);
			this.setCenterY(yOld);
			randomMove(BM.getBugs(), BM.getWidth(), BM.getHeight());
		}
		BM.setMoved(true);

	}

	// checks move is ok relative to other bugs
	public boolean checkMove(ArrayList<Bug> lst) {

		for (Bug b : lst) {
			if (b == this) {
				continue;
			}
			if ((Math.abs(this.getCenterX() - b.getCenterX())) < (this.getRadius())) {
				if ((Math.abs(this.getCenterY() - b.getCenterY())) < (this.getRadius())) {
					return false;
				}
			}
		}
		return true;
	}

	//makes the bug move randomly. Movement is made for several iterations in a given direction to 
	//give smooth animation
	public void randomMove(ArrayList<Bug> lst, int width, int height) {
//		this.resetBugDirection();
		// 10% chance to change direction
		if (Math.random() > .9) {
			this.setDirection((int) (Math.random() * 4));
		}
		double ranMove = (int) (5 + Math.random() * 5);
		double xOld = this.getCenterX();
		double yOld = this.getCenterY();
		if (this.getDirection() == 0) {
			this.setCenterX(xOld + ranMove);
		} else if (this.getDirection() == 1) {
			this.setCenterX(xOld - ranMove);
		} else if (this.getDirection() == 2) {
			this.setCenterY(yOld + ranMove);
		} else if (this.getDirection() == 3) {
			this.setCenterY(yOld - ranMove);
		} else {
			System.out.println("random direction err");
		}

		// moving bug back into bounds
		if (this.getCenterX() + this.getRadius() >= width) {
			this.setCenterX(xOld - ranMove);
		}
		if (this.getCenterX() - this.getRadius() <= 0) {
			this.setCenterX(xOld + ranMove);
		}
		if (this.getCenterY() + this.getRadius() >= height) {
			this.setCenterY(yOld - ranMove);
		}
		if (this.getCenterY() - this.getRadius() <= 0) {
			this.setCenterY(yOld + ranMove);
		}
		if (!checkMove(lst)) {
			this.setCenterX(xOld);
			this.setCenterY(yOld);
			randomMove(lst, width, height);

		}
		
		System.out.println("(X,Y) = (" + this.getCenterX() + ", " + this.getCenterY() + ")");

	}


}