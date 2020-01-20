import java.io.IOException;
import java.util.Scanner;

public class PuzzleSearch {

	public static void main(String[] args) {
		PuzzleSearch program;
		try {
			program = new PuzzleSearch();
			program.run();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		System.out.println("------------------");
		System.out.println("8 Puzzle Search");
		System.out.println("------------------");
		System.out.println("[1] Blind Search\n[2] Greedy Search: Misplaced Tiles\n"
				+ "[3] Greedy Search: Manhattan Distance\n[4] A* Search: Misplaced Tiles\n"
				+ "[5] A* Search: Manhattan Distance");
		try {
			System.out.print("Enter Choice: ");
			choice = Integer.parseInt(sc.nextLine());
		} catch (Exception e) {
			System.out.println("Please enter a valid choice.");
		}

		sc.close();
		try {
			determineSearchAlgo(choice);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void determineSearchAlgo(int c) throws IOException {
		switch (c) {
		case 1:
			// add
			break;
		case 2:
			new GreedySearch().run("Misplaced Tiles");
			break;
		case 3:
			new GreedySearch().run("Manhattan Distance");
			break;
		case 4:
			new AStarSearch().run('t');
			break;
		case 5:
			new AStarSearch().run('m');
			break;
		default:
			System.out.println("Please enter one of the choices (1-5).");
		}
	}

}
