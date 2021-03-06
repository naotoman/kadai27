package others;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>課題27のテストケースとなるファイルを作成するクラスです。
 * 他のクラスと相互作用することはなく、完全に独立して動作します。
 * 問題例を手軽に作成したいときにご利用ください。
 */
public class TestCaseGenerator {

	private static final double prob1 = 0.2;//要素が1になる確率
	private static final int N = 10;//行（列）の数

	//作成した問題ファイルの出力先
	private static final String FILE_NAME = "input/sample27.txt";

	public static void main(String[] args) {
		File file = new File(FILE_NAME);
		try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
			pw.println(N);
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					int bit = (Math.random() < prob1) ? 1 : 0;
					if(j > 0) pw.print(" ");
					pw.print(bit);
				}
				pw.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
