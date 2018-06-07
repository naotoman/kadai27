package main;

import java.io.File;

public class MainKadai27 {

	enum Algorithm {
		BRUTE_FORCE,
		DINIC,
		BI_MATCH,
	}



	private static final String INPUT_PATH = "input/sample27.txt";

	private static final Algorithm ALGORITHM_TO_RUN = Algorithm.BI_MATCH;



	public static void main(String[] args) {
		AlgorithmFactory factory = new AlgorithmFactory();
		Solver27 sol = factory.choose(ALGORITHM_TO_RUN);

		File input = new File(INPUT_PATH);

		sol.readInput(input);
		sol.solve();
		sol.showResult();
	}
}
