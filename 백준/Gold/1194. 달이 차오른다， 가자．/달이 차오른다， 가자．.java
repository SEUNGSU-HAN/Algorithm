import java.io.*;
import java.util.*;

public class Main {
	static int N, M, Key, result;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static char[][] board;
	static boolean[][][] visited;
	static class Node {
		int z, r, c, d;
		
		public Node(int z, int r, int c, int d) {
			this.z = z;
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
	static Node minsik;
	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		board = new char[N][M];
		for (int i = 0; i < N; i++) {
			String input = br.readLine().trim();
			for (int j = 0; j < M; j++) {
				board[i][j] = input.charAt(j);
				if('a' <= board[i][j] && board[i][j] <= 'f') Key++;
				if(board[i][j] == '0') minsik = new Node(1<<7, i, j, 0);
			}
		}
		visited = new boolean[(1<<8)-1][N][M];
		
		/* 로직 */
		result = bfs();
		
		/* 출력 */
		System.out.print(result);
		
	}
	static int bfs() {
		Queue<Node> q = new ArrayDeque<>();
		q.offer(minsik);
		visited[minsik.z][minsik.r][minsik.c] = true;
		while(!q.isEmpty()) {
			Node cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur.r+dr[i];
				int nc = cur.c+dc[i];
				int nz = cur.z;
				if(check(nr, nc) && !visited[nz][nr][nc] && board[nr][nc] != '#') {
					if(board[nr][nc] == '1') return cur.d+1;
					if('a' <= board[nr][nc] && board[nr][nc] <= 'f'
							&& (nz & 1<<(board[nr][nc]-'a')) == 0) {
						//열쇠를 만나면 열쇠를 먹고 차원 이동
						//새로운 visited로 새출발
						nz |= 1<<(board[nr][nc]-'a');
						visited[nz][nr][nc] = true;
						q.offer(new Node(nz, nr, nc, cur.d+1));
						continue;
					}
					if('A' <= board[nr][nc] && board[nr][nc] <= 'F'
							&& (nz & 1<<(board[nr][nc]-'A')) > 0) {
						//문을 만났는데 열쇠가 있으면 문열고 이동
						//열쇠가 없으면 벽이나 다름 없음
						visited[nz][nr][nc] = true;
						q.offer(new Node(nz, nr, nc, cur.d+1));
						continue;
					}
					//그냥 이동
					if(!('A' <= board[nr][nc] && board[nr][nc] <= 'F')) {
						visited[nz][nr][nc] = true;
						q.offer(new Node(nz, nr, nc, cur.d+1));						
					}
				}
			}
		}
		return -1;
	}
	
	static boolean check(int nr, int nc) {
		return (0 <= nr && nr < N) && (0 <= nc && nc < M);
	}

}
