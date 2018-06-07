package algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import main.Solver27;



class Edge {
	int to, rev, cap;
	//to: この辺が指す頂点の番号
	//rev: この辺に対する逆辺がgraph.get(this.to).get(this.rev)になる
	//cap: この辺に流せる残りの流量

	Edge(int to, int rev, int cap) {
		this.to = to;
		this.rev= rev;
		this.cap = cap;
	}
}


/*
 * 課題27を解くプログラム．
 * 2部マッチング問題を最大フロー問題に帰着させて解く．
 * 最大フロー問題を解く際はDinic法を用いている．
 */
public class Dinic implements Solver27 {

	private static final int INF = 1<<30;


	int n;//行（列）の数

	int V;//頂点数
	List<List<Edge>> graph;//各頂点から出ている辺を保持
	int s, t;//最大フロー問題のsourceとsink
	int maxFlow;

	int[] dist;//Dinic法で用いる．sから増加パスのみをたどったときの各頂点までのグラフ上の距離．到達不能な場合は-1
	int[] iter;//Dinic法で用いる．深さ優先探索（dfsメソッド)により，各頂点から次に探索する辺（のインデックス）を記憶．


	@Override
	public void readInput(File input) {
		try(Scanner sc = new Scanner(input)) {
			n = sc.nextInt();
			V = 2*n+2;
			s = V-2;
			t = V-1;
			dist = new int[V];
			iter = new int[V];
			graph = new ArrayList<>(V);
			for(int i=0; i<V; i++) {
				graph.add(new ArrayList<>());
			}
			for(int i=0; i<n; i++) {
				addEdge(s, i);
				addEdge(n+i, t);
			}
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(sc.nextInt() == 1) {
						addEdge(i, n+j);
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void solve() {
		maxFlow = 0;
		while(true) {
			calcDistancesBFS();
			if(dist[t] < 0) return;
			Arrays.fill(iter, 0);
			int f;
			while((f = dfs(s, INF)) > 0) {
				maxFlow += f;
			}
		}
	}

	@Override
	public void showResult() {
		int cnt = 0;
		boolean[] used = new boolean[n];
		int[] match = new int[n];
		for(int i=0; i<n; i++) {
			for(Edge e : graph.get(i)) {
				if(e.cap == 0 && e.to!=s) {
					match[i] = e.to-n+1;
					used[match[i]-1] = true;
					cnt++;
				}
			}
		}
		int right = -1;
		for(int i=0; i<n; i++) {
			if(match[i] == 0) {
				while(used[++right] == true) {}
				match[i] = right + 1;
			}
		}
		System.out.println("選ばれた1の数： " + cnt);
		System.out.print(match[0]);
		for(int i=1; i<n; i++) {
			System.out.print(" " + match[i]);
		}
		System.out.println();
	}

	private void calcDistancesBFS() {
		Arrays.fill(dist, -1);
		Queue<Integer> que = new ArrayDeque<>();
		dist[s] = 0;
		que.add(s);
		while(!que.isEmpty()) {
			int v = que.poll();
			for(Edge e : graph.get(v)) {
				if(e.cap > 0 && dist[e.to] < 0) {
					dist[e.to] = dist[v] + 1;
					que.add(e.to);
				}
			}
		}
	}

	private int dfs(int v, int preFlow) {
		if(v == t) return preFlow;
		List<Edge> edgesFromV = graph.get(v);
		for(; iter[v] < edgesFromV.size(); iter[v]++) {
			Edge e = edgesFromV.get(iter[v]);
			if(e.cap > 0 && dist[v] < dist[e.to]) {
				int flow = dfs(e.to, Math.min(preFlow, e.cap));
				if(flow > 0) {
					e.cap -= flow;
					graph.get(e.to).get(e.rev).cap += flow;
					return flow;
				}
			}
		}
		return 0;
	}

	private void addEdge(int from, int to) {
		Edge e = new Edge(to, graph.get(to).size(), 1);
		Edge eRev = new Edge(from, graph.get(from).size(), 0);
		graph.get(from).add(e);
		graph.get(to).add(eRev);
	}

}
