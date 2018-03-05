package map;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import sample.*;

public class Position {

	double x;//x-axis
	double y;//y-axis
	
	double width;
	double height;
	
	double row;
	double column;

	public static void initScreenDimentions(){

		Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
		}
	

	
	public Position(double row, double column){

		this.row = row;
		this.column = column;
		
    	this.x = ((Settings.SCENE_WIDTH)/(Settings.COLUMN_CELL_COUNT))*column;//satıra döşüyor
		this.y = (Settings.SCENE_HEIGHT/Settings.ROW_CELL_COUNT)*row;//sütunu döşüyor(Ters mantık)
		this.width = Settings.SCENE_WIDTH/Settings.ROW_CELL_COUNT;
		this.height = Settings.SCENE_HEIGHT/Settings.ROW_CELL_COUNT;
		
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getRow() {
		return row;
	}

	public void setRow(double row) {
		this.row = row;
	}

	public double getColumn() {
		return column;
	}

	public void setColumn(double column) {
		this.column = column;
	}

}
