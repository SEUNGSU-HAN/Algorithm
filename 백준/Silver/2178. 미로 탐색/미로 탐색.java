import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static char[][] board;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		board = new char[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			board[i] = br.readLine().toCharArray();
		}
		
		/* 로직+출력 */
		bfs(0, 0, 1);

	}

	private static void bfs(int sr, int sc, int d) {
		Queue<Node> q = new LinkedList<>();
		
		q.offer(new Node(sr, sc, d)); //큐 넣기
		
		visited[sr][sc] = true; //시작 노드 방문 처리
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			if(node.r == N-1 && node.c == M-1) {
				System.out.println(node.d);
				return;
			}
			for (int i = 0; i < 4; i++) {
				int nr = node.r+dr[i];
				int nc = node.c+dc[i];
				if(isValid(nr, nc) && !visited[nr][nc] && board[nr][nc] == '1') {
					q.offer(new Node(nr, nc, node.d+1));
					visited[nr][nc] = true; //방문 처리
				}
			}
		}
	}
	
	private static boolean isValid(int nr, int nc) {
		if((0 <= nr && nr < N) && (0 <= nc && nc < M)) return true;
		return false;
	}

	static class Node{
		int r;
		int c;
		int d;
		
		Node(int r, int c, int d){
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}

}
