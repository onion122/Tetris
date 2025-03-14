package tetris;

import java.awt.Color;
import java.awt.Point;

public class Board {
	private final int WIDTH = 10;
	private final int HEIHGT = 20;
	private Color[][] grid;
	
	public Board() {
		grid = new Color[WIDTH][HEIHGT];
	}
	
	public boolean canMove(Tetromino tetromino, int dx, int dy) {
		for(Point p : tetromino.getShape()) {
			int newX = p.x + dx;
			int newY = p.y + dy;
			
			if(newX < 0 || newX >= WIDTH || newY >= HEIHGT || (newY >= 0 && grid[newY][newX] != null)) {
				return false;
			}
		}
		return true;
	}
	
	public void placeTetromino(Tetromino tetromino) {
		for (Point p : tetromino.getShape()) {
			grid[p.y][p.x] = tetromino.getColor();
		}
	}
	
	public int clearLines() {
		int linesCleared = 0;
		for(int y = 0; y < HEIHGT; y++) {
			boolean full = true;
			for(int x = 0; x < WIDTH; x++) {
				if(grid[y][x] == null) {
					full = false;
					break;
				}
			}
			if(full) {
				linesCleared++;
				for(int shiftY = y; shiftY > 0; shiftY--) {
					grid[shiftY] = grid[shiftY - 1].clone();
				}
				grid[0] = new Color[WIDTH];
			}
		}
		return linesCleared;
	}
}
