package net.talaatharb.guitests;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class GUITestController implements Initializable {

	@FXML
	private Button testButton;
	
	@FXML
	private Circle circle;
	
	@FXML
	private void addCircle() {
		int radius = 50;
		Circle newCircle = new Circle(radius, Paint.valueOf("red"));
		pane.getChildren().add(newCircle);
		newCircle.setLayoutX(radius);
		newCircle.setLayoutY(radius);
		
		enableDrag(newCircle);
	}
	
	@FXML
	private void addButton() {
		Button newButton = new Button("Test");
		pane.getChildren().add(newButton);
		enableDrag(newButton);
	}
	
	@FXML
	private Pane pane;
	

	// make a node movable by dragging it around with the mouse.
	private final static void enableDrag(final Node node) {
		final Delta dragDelta = new Delta();
		
		node.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				// record a delta distance for the drag and drop operation.
				dragDelta.x = mouseEvent.getX();
				dragDelta.y = mouseEvent.getY();
				node.getScene().setCursor(Cursor.MOVE);
			}
		});
		node.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				node.getScene().setCursor(Cursor.HAND);
			}
		});
		node.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				node.setLayoutX(node.getLayoutX() + mouseEvent.getX() - dragDelta.x);
				node.setLayoutY(node.getLayoutY() + mouseEvent.getY() - dragDelta.y);
			}
		});
		node.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (!mouseEvent.isPrimaryButtonDown()) {
					node.getScene().setCursor(Cursor.HAND);
				}
			}
		});
		node.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (!mouseEvent.isPrimaryButtonDown()) {
					node.getScene().setCursor(Cursor.DEFAULT);
				}
			}
		});
	}

	@Override
	public void initialize(URL url, ResourceBundle bundle) {
		enableDrag(circle);
		enableDrag(testButton);
	}
}

class Delta {
	double x, y;
}