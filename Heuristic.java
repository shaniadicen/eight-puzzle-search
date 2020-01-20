
public class Heuristic {
	private int hCost = 0; // h(n) cost
	private int fCost = 0; // f(n) cost

	public Heuristic(Heuristic h) {
		hCost = h.getHCost();
		fCost = h.getFcost();
	}

	public Heuristic() {
		hCost = 0;
		fCost = 0;
	}

	public void calcMisplacedGreedyHeuristic(State currentState, Puzzle goalState) {
		Puzzle currentPuzzle = currentState.getGrid();
		int misplacedTiles = 0;
		int i, j;
		for (i = 0; i < 3; i++) {
			for (j = 0; j < 3; j++) {
				if (currentPuzzle.getGrid()[i][j] != goalState.getGrid()[i][j]) {
					misplacedTiles++;
				}
			}
		}
		hCost = misplacedTiles;
	}

	public void calcManhattanGreedyHeuristic(State currentState, Puzzle goalState) {
		Puzzle currentGrid = currentState.getGrid();
		int[] currentPosition = new int[2];
		int[] destination = new int[2];
		int distance;
		int totalDistance = 0;

		for (int i = 0; i < 9; i++) {
			currentPosition = currentGrid.getCoordinates(i);
			destination = goalState.getCoordinates(i);
			distance = Math.abs(currentPosition[0] - destination[0]) + Math.abs(currentPosition[1] - destination[1]);
			totalDistance += distance;
		}
		hCost = totalDistance;
	}

	/**
	 * @return the heuristic
	 */
	public int getHCost() {
		return hCost;
	}

	/**
	 * @param heuristic
	 *            the heuristic to set
	 */
	public void setHCost(int heuristic) {
		this.hCost = heuristic;
	}

	/**
	 * @return the fcost
	 */
	public int getFcost() {
		return fCost;
	}

	/**
	 * @param fcost
	 *            the fcost to set
	 */
	public void setFcost(int fcost) {
		this.fCost = fcost;
	}
}
