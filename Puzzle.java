import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Puzzle {
	private int[][] grid = new int[3][3];
	private int emptyX;
	private int emptyY;

	public Puzzle() {
		grid = new int[3][3];
		emptyX = 0;
		emptyY = 0;
	}

	public Puzzle(Puzzle p) {
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				grid[x][y] = p.getGrid()[x][y];
			}
		}
		emptyX = p.getEmptyX();
		emptyY = p.getEmptyY();
	}

	public Puzzle(String file) {
		try {
			readFile(file);
		} catch (IOException e) {
			System.out.println("File can't be read");
		}
	}

	public boolean moveTile(int x, int y) {
		boolean success = false;

		/**
		 * Move the empty tile horizontally and vertically
		 */
		if (x != 0) { // move horizontally
			if (x > 0 && emptyX < 2) { // move right
				grid[emptyX][emptyY] = grid[emptyX + 1][emptyY];
				grid[emptyX + 1][emptyY] = 0;

				emptyX = emptyX + 1;
				success = true;

			} else if (x < 0 && emptyX > 0) { // move left
				grid[emptyX][emptyY] = grid[emptyX - 1][emptyY];
				grid[emptyX - 1][emptyY] = 0;

				emptyX = emptyX - 1;
				success = true;
			}

		} else if (y != 0) {
			if (y > 0 && emptyY < 2) { // move down
				grid[emptyX][emptyY] = grid[emptyX][emptyY + 1];
				grid[emptyX][emptyY + 1] = 0;

				emptyY = emptyY + 1;
				success = true;
			} else if (y < 0 && emptyY > 0) { // move up
				grid[emptyX][emptyY] = grid[emptyX][emptyY - 1];
				grid[emptyX][emptyY - 1] = 0;

				emptyY = emptyY - 1;
				success = true;
			}
		}

		return success;
	}

	public int[] getCoordinates(int tile) {
		int[] position = new int[2];

		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				if (grid[x][y] == tile) {
					position[0] = x;
					position[1] = y;

					break;
				}
			}
		}
		return position;
	}

	public boolean equalTo(Puzzle pg) {
		boolean equal = true;

		for (int y = 0; y < 3 && equal == true; y++) {
			for (int x = 0; x < 3 && equal == true; x++) {

				if (grid[x][y] != pg.getGrid()[x][y]) {
					equal = false;
				}
			}
		}

		return equal;
	}

	public void readFile(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String[] data = new String[3];
		String line = null;
		int i = 0, j = 0;

		line = br.readLine();

		while (line != null) {
			data = line.split(",");

			for (i = 0; i <= 2; i++) {
				grid[i][j] = Integer.parseInt(data[i]);

				if (grid[i][j] == 0) {
					emptyX = i;
					emptyY = j;
				}
			}

			j++;
			line = br.readLine();
		}
		br.close();
	}

	/**
	 * @return the grid
	 */
	public int[][] getGrid() {
		return grid;
	}

	/**
	 * @param grid
	 *            the grid to set
	 */
	public void setGrid(int[][] grid) {
		this.grid = grid;
	}

	/**
	 * @return the emptyX
	 */
	public int getEmptyX() {
		return emptyX;
	}

	/**
	 * @param emptyX
	 *            the emptyX to set
	 */
	public void setEmptyX(int emptyX) {
		this.emptyX = emptyX;
	}

	/**
	 * @return the emptyY
	 */
	public int getEmptyY() {
		return emptyY;
	}

	/**
	 * @param emptyY
	 *            the emptyY to set
	 */
	public void setEmptyY(int emptyY) {
		this.emptyY = emptyY;
	}

	public void printPuzzle() {
		for (int y = 0; y < 3; y++) {
			System.out.print("\t");
			for (int x = 0; x < 3; x++) {

				if (x == emptyX && y == emptyY)
					System.out.print(" " + "\t");
				else
					System.out.print(grid[x][y] + "\t");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
}
