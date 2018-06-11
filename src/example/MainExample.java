package example;

import java.io.File;

import algorithm.BiMatching;
import algorithm.BruteForce27;
import algorithm.Dinic;
import algorithm.Solver27;

/**
 * <p>課題27を解くメイン関数の一例です。メイン関数自体は4行程度なので、詳しくはコードを実際に読んでみてください。
 */
public class MainExample {

	//入力ファイルのパスを指定
	private static final String INPUT_PATH = "input/sample27.txt";


	//実行するアルゴリズムを選択するために定義。chooseAlgorithmメソッドで使用。
	private static final int BRUTE_FORCE = 0;
	private static final int DINIC = 1;
	private static final int BI_MATCH = 2;



	public static void main(String[] args) {
		Solver27 sol = chooseAlgorithm(0);

		sol.readInput(new File(INPUT_PATH));
		sol.solve();
		sol.showResult();
	}


	/*
	 * 引数で渡された数に応じたアルゴリズムを実装したクラスを返す。
	 */
	private static Solver27 chooseAlgorithm(int x) {
		switch(x) {
		case BRUTE_FORCE :
			return new BruteForce27();
		case DINIC :
			return new Dinic();
		case BI_MATCH :
			return new BiMatching();
		default:
			throw new IllegalArgumentException("No Such Solver");
		}
	}

	private MainExample(){};

}
