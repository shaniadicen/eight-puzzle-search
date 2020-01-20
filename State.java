import java.util.ArrayList;

public class State implements Comparable<State> {
	private Puzzle grid;
	private State parent;
	private Heuristic heuristic;
	private ArrayList<State> children = new ArrayList<State>();

	public State(Puzzle g, Heuristic h) {
		grid = g;
		heuristic = h;
	}

	public ArrayList<State> generateSuccessors() {
		State state;
		Puzzle puzzle;

		// Up
		puzzle = new Puzzle(grid);
		if (puzzle.moveTile(0, -1)) {
			state = new State(puzzle, new Heuristic(heuristic));
			state.setParent(this);
			children.add(state);
		}

		// Down
		puzzle = new Puzzle(grid);
		if (puzzle.moveTile(0, 1)) {
			state = new State(puzzle, new Heuristic(heuristic));
			state.setParent(this);
			children.add(state);
		}

		// Left
		puzzle = new Puzzle(grid);
		if (puzzle.moveTile(-1, 0)) {
			state = new State(puzzle, new Heuristic(heuristic));
			state.setParent(this);
			children.add(state);
		}

		// Right
		puzzle = new Puzzle(grid);
		if (puzzle.moveTile(1, 0)) {
			state = new State(puzzle, new Heuristic(heuristic));
			state.setParent(this);
			children.add(state);
		}

		return children;
	}

	public void detManhattanGreedyHeuristic(Puzzle goalState) {
		if (heuristic != null) {
			heuristic.calcManhattanGreedyHeuristic(this, goalState);
		}
	}
	
	public void detMisplacedGreedyHeuristic(Puzzle goalState) {
		if (heuristic != null) {
			heuristic.calcMisplacedGreedyHeuristic(this, goalState);
		}
	}

	public int getHeuristicValue() {
		if (heuristic != null) {
			return heuristic.getHCost();
		} else {
			return 0;
		}
	}

	public boolean equalTo(State pn) {
		return grid.equalTo(pn.getGrid());
	}

	/**
	 * @return the grid
	 */
	public Puzzle getGrid() {
		return grid;
	}

	/**
	 * @param grid
	 *            the grid to set
	 */
	public void setGrid(Puzzle grid) {
		this.grid = grid;
	}

	/**
	 * @return the parent
	 */
	public State getParent() {
		return parent;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(State parent) {
		this.parent = parent;
	}

	/**
	 * @return the heuristic
	 */
	public Heuristic getHeuristic() {
		return heuristic;
	}

	/**
	 * @param heuristic
	 *            the heuristic to set
	 */
	public void setHeuristic(Heuristic heuristic) {
		this.heuristic = heuristic;
	}

	/**
	 * @return the children
	 */
	public ArrayList<State> getChildren() {
		return children;
	}

	/**
	 * @param children
	 *            the children to set
	 */
	public void setChildren(ArrayList<State> children) {
		this.children = children;
	}

	public int compareTo(State another) {
		if (this.getHeuristic().getHCost() < another.getHeuristic().getHCost()) {
			return -1;
		} else if (this.getHeuristic().getHCost() > another.getHeuristic().getHCost()) {
			return 1;
		} else
			return 0;
	}
}
