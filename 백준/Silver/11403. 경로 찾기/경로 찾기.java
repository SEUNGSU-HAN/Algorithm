import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] board;
	static ArrayList<ArrayList<Integer>> graph;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		board = new int[N][N];
		graph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 1) graph.get(i).add(j);
			}
		}
		
		/* 로직 */
		for (int i = 0; i < N; i++) {
			dfs(i, i, new boolean[N]);
		}
		
		/* 출력 */
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(board[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int start, int v, boolean[] visited) {		
		visited[v] = true; //방문처리
		
		if(v != start) board[start][v] = 1;
		
		for (int i = 0; i < graph.get(v).size(); i++) {
			int nv = graph.get(v).get(i);
			if(nv == start) board[start][start] = 1; //싸이클 체크
			if(!visited[nv]) dfs(start, nv, visited);
		}
		
	}

}
