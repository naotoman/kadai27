package algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


/**
 * <p>{@code Solver27}インターフェースの実装の一つで、2部マッチング問題を高速に解くアルゴリズムです。
 * このアルゴリズムは、最大フロー問題を解くFord-Fulkerson法をベースとしています。
 * （参考：プログラミングコンテストチャレンジブック）
 */
public class BiMatching implements Solver27 {

	private int n;
	private int V;
	private List<List<Integer>> graph = new ArrayList<>();

	private int maxMatch;
	private int[] match;


	@Override
	public void readInput(File input)  {
		try(Scanner sc = new Scanner(input)) {
			n = sc.nextInt();
			V = 2 * n;
			match = new int[V];
			for(int i=0; i<V; i++) {
				graph.add(new ArrayList<>());
			}
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(sc.nextInt() == 1) {
						graph.get(i).add(n+j);
						graph.get(n+j).add(i);
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void solve() {
		Arrays.fill(match, -1);
		maxMatch = 0;
		for(int v=0; v<V; v++) {
			if(match[v] == -1) {
				boolean[] used = new boolean[V];
				if(dfs(v, used)) {
					maxMatch++;
				}
			}
		}
	}

	@Override
	public void showResult() {
		System.out.println("選ばれた1の数： " + maxMatch);
		int right = n-1;
		for(int i=0; i<n; i++) {
			if(match[i] == -1) {
				while(match[++right] != -1) {}
				match[i] = right;
			}
		}
		System.out.print((match[0]-n+1));
		for(int i=1; i<n; i++) {
			System.out.print(" " + (match[i]-n+1));
		}
		System.out.println();
	}

	private boolean dfs(int u, boolean[] used) {
		used[u] = true;
		for(int v : graph.get(u)) {
			int w = match[v];
			if(w == -1 || !used[w]&&dfs(w, used)) {
				match[u] = v;
				match[v] = u;
				return true;
			}
		}
		return false;
	}

}
