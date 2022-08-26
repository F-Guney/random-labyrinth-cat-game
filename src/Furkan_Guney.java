import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.Random;
import java.util.Scanner;

public class Furkan_Guney {
	/**
	 * @author Furkan Güney - 041801106
	 * @param args
	 */
	public static void main(String[] args) {

		int canvas_width = 950;
		int canvas_height = 950;
		StdDraw.setCanvasSize(canvas_width, canvas_height);
		StdDraw.enableDoubleBuffering();
		// set the background as black to get lines between squares
		StdDraw.clear(StdDraw.BLACK);
		// reading file
		String filename = "world.txt";
		File worldFile = new File(filename);
		Scanner reader = null;
		Color catColor = StdDraw.PRINCETON_ORANGE;
		Color wallColor = StdDraw.LIGHT_GRAY;
		Color seaColor = StdDraw.BOOK_BLUE;
		Color foodColor = StdDraw.MAGENTA;
		Color spaceColor = StdDraw.WHITE;

		try {
			reader = new Scanner(worldFile);
		} catch (FileNotFoundException e) {
			System.out.println(filename + ": Input file can not be found!\nExiting program...");
			System.exit(1);
		}

		// get rid of from first line


		String line1 = reader.nextLine();
		String[] firstLine = line1.split(" ");
		/*
		 * n1 is column, n2 is row
		 */
		int n1 = 40;
		int n2 = 40;
		int[][] values = new int[n1][n2];

		int count = 0;

		StdDraw.setXscale(0, n1);
		StdDraw.setYscale(0, n2);

		while (reader.hasNext()) {
			String line2 = reader.next();
			String[] parts = line2.split(";");
			// put parts elements into values array by converting to integers and deleting

			/*
			 * Drawing the world from "world.txt" file with these conditions 0: Empty 1:
			 * Wall 2: Sea 3: Food
			 */

			for (int i = n1; i > 0; i--) {
				if (Integer.parseInt(parts[n1 - i]) == 1) {
					StdDraw.setPenColor(wallColor);
					StdDraw.filledSquare(n1 - i + 0.5, n2 - 1 - count + 0.5, 0.472);
				} else if (Integer.parseInt(parts[n1 - i]) == 2) {
					StdDraw.setPenColor(seaColor);
					StdDraw.filledSquare(n1 - i + 0.5, n2 - 1 - count + 0.5, 0.472);
				} else if (Integer.parseInt(parts[n1 - i]) == 3) {
					StdDraw.setPenColor(foodColor);
					StdDraw.filledSquare(n1 - i + 0.5, n2 - 1 - count + 0.5, 0.472);
				} else if (Integer.parseInt(parts[n1 - i]) == 0) {
					StdDraw.setPenColor(spaceColor);
					StdDraw.filledSquare(n1 - i + 0.5, n2 - 1 - count + 0.5, 0.472);
				}

			}
			for (int j = 0; j < n1; j++) {
				values[(n1 - 1) - count][(n2 - 1) - j] = Integer.parseInt(parts[n1 - 1 - j]);
			}
			// put world values inside of values array
			count++;

		}
		int startingX = 5;
		int startingY = 5;
		Cat cat = new Cat(startingX, startingY, catColor);
		int moveCount = 50_000;
		Random r = new Random();

		for (int i = 0; i < moveCount; i++) {
			int direction = r.nextInt(4);
			if ((cat.getY() > 1) && values[cat.getY() - 1][cat.getX()] == 3) {
				values[cat.getY() - 1][cat.getX()] = 0;
				StdDraw.setPenColor(spaceColor);
				StdDraw.filledSquare(cat.getX() + 0.5, cat.getY() + 0.5, 0.46);
				cat.setY(cat.getY() - 1);
				cat.setFoodCount(cat.getFoodCount() + 1);
			}

			if ((cat.getX() > 1) && values[cat.getY()][cat.getX() - 1] == 3) {
				values[cat.getY()][cat.getX() - 1] = 0;
				StdDraw.setPenColor(spaceColor);
				StdDraw.filledSquare(cat.getX() + 0.5, cat.getY() + 0.5, 0.46);
				cat.setX(cat.getX() - 1);
				cat.setFoodCount(cat.getFoodCount() + 1);
			}

			if ((cat.getY() < n2 - 2) && values[cat.getY() + 1][cat.getX()] == 3) {
				values[cat.getY() + 1][cat.getX()] = 0;
				StdDraw.setPenColor(spaceColor);
				StdDraw.filledSquare(cat.getX() + 0.5, cat.getY() + 0.5, 0.46);
				cat.setY(cat.getY() + 1);
				cat.setFoodCount(cat.getFoodCount() + 1);
			}

			if ((cat.getX() < n1 - 2) && values[cat.getY()][cat.getX() + 1] == 3) {
				values[cat.getY()][cat.getX() + 1] = 0;
				StdDraw.setPenColor(spaceColor);
				StdDraw.filledSquare(cat.getX() + 0.5, cat.getY() + 0.5, 0.46);
				cat.setX(cat.getX() + 1);
				cat.setFoodCount(cat.getFoodCount() + 1);
			}

			move(cat, direction, values, spaceColor, n1);

			cat.draw();

			StdDraw.pause(100);
			StdDraw.show();

		}
	}

	/**
	 * 0 : down, 1 : left, 2: up, 3 : right 
	 * method checks direction, boundaries and
	 * sea coordinates if the conditions are assured then cat can move
	 * 
	 * @param inputCat
	 * @param num      is direction of the cat
	 * @param array    has the values of the world
	 */
	public static void move(Cat inputCat, int num, int[][] array, Color color, int worldSize) {

		if ((num == 0) && (inputCat.getY() > 1) && array[inputCat.getY() - 1][inputCat.getX()] != 2) {
			StdDraw.setPenColor(color);
			StdDraw.filledSquare(inputCat.getX() + 0.5, inputCat.getY() + 0.5, 0.46);
			inputCat.setY(inputCat.getY() - 1);
		}

		if ((num == 1) && (inputCat.getX() > 1) && array[inputCat.getY()][inputCat.getX() - 1] != 2) {
			StdDraw.setPenColor(color);
			StdDraw.filledSquare(inputCat.getX() + 0.5, inputCat.getY() + 0.5, 0.46);
			inputCat.setX(inputCat.getX() - 1);
		}

		if ((num == 2) && (inputCat.getY() < worldSize - 2) && array[inputCat.getY() + 1][inputCat.getX()] != 2) {
			StdDraw.setPenColor(color);
			StdDraw.filledSquare(inputCat.getX() + 0.5, inputCat.getY() + 0.5, 0.46);
			inputCat.setY(inputCat.getY() + 1);
		}

		if ((num == 3) && (inputCat.getX() < worldSize - 2) && array[inputCat.getY()][inputCat.getX() + 1] != 2) {
			StdDraw.setPenColor(color);
			StdDraw.filledSquare(inputCat.getX() + 0.5, inputCat.getY() + 0.5, 0.46);
			inputCat.setX(inputCat.getX() + 1);
		}
	}

}
