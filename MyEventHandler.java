package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

class MyEventHandler implements EventHandler<ActionEvent> {
	private int i = 0;

	BugMain m;

	public MyEventHandler(BugMain m) {
		this.m = m;
	}

	
	@Override
	public void handle(ActionEvent event) {
		
		int bugNum = -1;
		for (Bug b : m.getBugs()) {
			m.setMoved(false);
			int width = m.getWidth();
			int height = m.getHeight();
			System.out.println("Running...." + b.getName());
			b.resetBugDirection();		//resets the image rotation
			if (b.getHunger() < 30) {
				b.smellFood(this.m);
			}
			if (!m.isMoved()) {
				b.randomMove(m.getBugs(), width, height);
			}
			b.eatFood(m);
			b.setHunger(b.getHunger() - 1);
			b.bugDirection();			//sets the bug rotation based on direction
			
			//kills bug, removes from bug array, replaces with skull image on a different array
			if (b.getHunger() < 0) {

				double x = b.getCenterX();
				double y = b.getCenterY();

				Dead deadBug = new Dead(x, y, 30);
				bugNum = m.getBugs().indexOf(b);
				Image skull = new Image("dead.png");
				deadBug.setFill(new ImagePattern(skull));
				m.getRoot().getChildren().add(deadBug);
				m.getRoot().getChildren().remove(b);
			}
		}
		if (bugNum != -1) {
			m.getBugs().remove(bugNum);
		}
		int treeNum = -1;
		//kills plant, removes from plant array, replaces with dead plant image on a different array

		for (Plant p : m.getPlants()) {
			
			if(p.getRadius()<5) {
				double x = p.getCenterX();
				double y = p.getCenterY();

				Dead deadTree = new Dead(x, y, 30);
				treeNum = m.getPlants().indexOf(p);
				Image skull = new Image("deadTree.png");
				deadTree.setFill(new ImagePattern(skull));
				m.getRoot().getChildren().add(deadTree);
				m.getRoot().getChildren().remove(p);				
			}			
			p.plantGrow();
		}
		if (treeNum != -1) {
			m.getPlants().remove(treeNum);
		}
		
		
	}

}