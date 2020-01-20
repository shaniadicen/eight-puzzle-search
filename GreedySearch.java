import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class GreedySearch {
	private Puzzle initialState;
	private Puzzle goalState;
	private ArrayList<State> solution = new ArrayList<State>();
	private ArrayList<State> statesVisited = new ArrayList<State>();
	private PriorityQueue<State> frontier;
	private static String algo = "";
	private int tc = 0, sc=0;

	public static void main(String[] args) {
		GreedySearch p;
		try {
			p = new GreedySearch();
			p.run(algo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run(String algo) throws IOException {
		initialState = new Puzzle("puzzle.txt");
		goalState = new Puzzle("puzzlegoal.txt");

		if (algo.equals("Manhattan Distance")) {
			solveManhattanDistance();
		} else if (algo.equals("Misplaced Tiles")) {
			solveMisplacedTiles();
		}

		printAlgorithmResult(algo);
	}

	public ArrayList<State> solveMisplacedTiles() {
		boolean solutionFound = false, stateVisited = false;
		State currentState = null;
		ArrayList<State> children = new ArrayList<State>();
		frontier = new PriorityQueue<State>();
		currentState = new State(initialState, new Heuristic());
		currentState.detMisplacedGreedyHeuristic(goalState);
		frontier.add(currentState);

		while (!frontier.isEmpty() && solutionFound != true) {
			currentState = frontier.poll();
			tc++;

			if (currentState.getGrid().equalTo(goalState)) {
				solutionFound = true;
				break; // if solution is found, exit the frontier
			}

			children = currentState.generateSuccessors();
			for (int i = 0; i < children.size(); i++) {
				

				State child = children.get(i);
				stateVisited = false;

				for (State tempNode : statesVisited) {
					if (child.equalTo(tempNode)) {
						stateVisited = true;
						break;
					}
				}

				for (State tempNode : frontier) {
					if (child.equalTo(tempNode)) {
						stateVisited = true;
						break;
					}
				}

				if (stateVisited != true) {
					sc++;
					child.detMisplacedGreedyHeuristic(goalState);
					frontier.add(child);
				}
			}
			statesVisited.add(currentState);
		}

		// retrace the solution path
		if (solutionFound == true) {
			boolean pathFound = false;
			solution.add(currentState);
			while (pathFound == false) {
				currentState = currentState.getParent();
				solution.add(currentState);
				if (currentState.getGrid().equalTo(initialState))
					pathFound = true;
			}
		} else {// Solution not found
			System.out.println("No solution");
		}
		return solution;
	}

	public ArrayList<State> solveManhattanDistance() {
		boolean solutionFound = false, stateVisited = false;
		State currentState = null;
		ArrayList<State> children = new ArrayList<State>();
		frontier = new PriorityQueue<State>();
		currentState = new State(initialState, new Heuristic());
		currentState.detManhattanGreedyHeuristic(goalState);
		frontier.add(currentState);

		while (!frontier.isEmpty() && solutionFound != true) {
			currentState = frontier.poll();
			sc = frontier.size();
			tc++;

			if (currentState.getGrid().equalTo(goalState)) {
				solutionFound = true;
				break; // if solution is found, exit the frontier
			}

			children = currentState.generateSuccessors();
			for (int i = 0; i < children.size(); i++) {

				State child = children.get(i);
				stateVisited = false;

				for (State tempNode : statesVisited) {
					if (child.equalTo(tempNode)) {
						stateVisited = true;
						break;
					}
				}

				for (State tempNode : frontier) {
					if (child.equalTo(tempNode)) {
						stateVisited = true;
						break;
					}
				}

				if (stateVisited != true) {
					child.detManhattanGreedyHeuristic(goalState);
					frontier.add(child);
					sc = Math.max(sc, frontier.size());
				}
			}
			statesVisited.add(currentState);
		}

		// retrace the solution path
		if (solutionFound == true) {
			boolean pathFound = false;
			solution.add(currentState);
			while (pathFound == false) {
				currentState = currentState.getParent();
				solution.add(currentState);
				if (currentState.getGrid().equalTo(initialState))
					pathFound = true;
			}
		} else {// Solution not found
			System.out.println("No solution");
		}
		return solution;
	}

	public void printAlgorithmResult(String algo) {
		int count = 1;
		System.out.println("\n----------------------------------------");
		System.out.println("Greedy Search: " + algo);
		System.out.println("----------------------------------------");

		System.out.println("   Solution Size: " + solution.size() + "\n Time Complexity: " + tc + "\nSpace Complexity: "
				+ sc);
		System.out.println();
		System.out.println("===============================");
		for (int i = solution.size() - 1; i >= 0; i--) {
			System.out.print("[" + count + "]");
			solution.get(i).getGrid().printPuzzle();
			count++;
		}
		System.out.println("===============================");
	}
}
