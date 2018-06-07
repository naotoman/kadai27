package main;

import algorithm.BiMatching;
import algorithm.BruteForce27;
import algorithm.Dinic;
import main.MainKadai27.Algorithm;

class AlgorithmFactory {

	Solver27 choose(Algorithm x) {
		switch(x) {
			case BRUTE_FORCE :
				return new BruteForce27();
			case DINIC :
				return new Dinic();
			case BI_MATCH :
				return new BiMatching();
			default:
				throw new IllegalArgumentException("Bug");
		}
	}
}
