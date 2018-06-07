package main;

import java.io.File;

/*
 * 課題27を解くメイン部分．
 * 実行する前に，二つの変数INPUT_PATHとALGORITHM_TO_RUNの値を設定しておく．
 */
public class MainKadai27 {

	enum Algorithm {
		BRUTE_FORCE,//Brute Forceなアルゴリズム．割り当てを全通り試す．
		DINIC,//最大フロー問題に帰着．Dinicのアルゴリズムで解く．
		BI_MATCH,//Forc-Fulkersonのアルゴリズムをベースとした高速な手法．（参考：プログラミングコンテストチャレンジブック）
	}


	//入力となるファイルまでのパスを設定する．
	private static final String INPUT_PATH = "input/sample27.txt";

	//実行したいアルゴリズムを設定．上記のenum(Algorithm)のなかから選択する．
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
