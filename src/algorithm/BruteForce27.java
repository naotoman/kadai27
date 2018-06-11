package algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.BitSet;
import java.util.Scanner;


/**
 * <p>{@code Solver27}インターフェースの実装の一つで、可能な割り当てを全通り試すアルゴリズムです。
 * 計算量はO(n!)であるため、大きすぎる入力には対応できません。
 */
public class BruteForce27 implements Solver27 {

	private int n;
	private int[][] matrix;

	private int maxPoint;
	private int[] solMatching;


	private int[] tmpSol;
	private BitSet unused;


	@Override
	public void readInput(File input) {
		try(Scanner sc = new Scanner(input)) {
			n = sc.nextInt();
			matrix = new int[n][n];
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					matrix[i][j] = sc.nextInt();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void solve() {
		maxPoint = 0;
		solMatching = new int[n];
		tmpSol = new int[n];
		unused = new BitSet(n);
		unused.set(0, n);
		bruteForce(0, 0);
	}

	@Override
	public void showResult() {
		System.out.println("選ばれた1の数： " + maxPoint);
		System.out.print((solMatching[0]+1));
		for(int i=1; i<n; i++) {
			System.out.print(" " + (solMatching[i]+1));
		}
		System.out.println();
	}

	private void bruteForce(int row, int point) {
		if(point + n - row <= maxPoint) return;
		if(row == n && point > maxPoint) {
			System.arraycopy(tmpSol, 0, solMatching, 0, n);
			maxPoint = point;
		}
		if(row == n) return;
		for(int i=unused.nextSetBit(0); i>=0; i=unused.nextSetBit(i+1)) {
			unused.clear(i);
			tmpSol[row] = i;
			bruteForce(row+1, point + matrix[row][i]);
			unused.set(i);
		}
	}


}
