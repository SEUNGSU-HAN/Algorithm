import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static char[][][] board;
	static boolean[][][] visited;
	static int dr[] = {-1, 0, 1, 0};
	static int dc[] = {0, 1, 0, -1};
	static class Minsik {
		int r, c, keys, move;

		public Minsik(int r, int c, int keys, int move) {
			this.r = r;
			this.c = c;
			this.keys = keys;
			this.move = move;
		}
	}
	static Minsik minsik;

	public static void main(String[] args) throws Exception{
		/* 셋팅 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new char[1<<8][N][M];
		visited = new boolean[1<<8][N][M];
		
		for(int i=0; i < N; i++) {
			String str = br.readLine();
			for(int j=0; j < M; j++) {
				char c = str.charAt(j);
				if(c == '0') {
					minsik = new Minsik(i, j, 1<<7, 0);
					board[0][i][j] = '.';
				}else board[0][i][j] = c;
			}
		}
		
		/* 로직+출력 */
		System.out.print(bfs());
	}

	static int bfs() {
		ArrayDeque<Minsik> dq = new ArrayDeque<Minsik>();
		dq.offer(minsik);
		board[minsik.keys][minsik.r][minsik.c] = '#';
		while(!dq.isEmpty()) {
			Minsik cur = dq.poll();
			for(int i=0; i<4; i++) {
				int nr = cur.r+dr[i];
				int nc = cur.c+dc[i];
				if(check(nr, nc) && board[0][nr][nc] != '#') {					
					//방문 여부
					if(board[cur.keys][nr][nc] == '#') continue;
					
					//탈출조건
					if(board[0][nr][nc] == '1') return cur.move+1;

					int next_key = cur.keys;
					
					//열쇠 get
					if(board[0][nr][nc] >= 'a' && board[0][nr][nc] <= 'f') {
						int key = board[0][nr][nc]-'a';
						
						//처음 먹는 열쇠다? -> 해당 열쇠를 포함한 차원으로 이동.(열쇠값 업데이트)
						if((cur.keys & 1<<key) == 0) 
							next_key = cur.keys|1<<key;
						//이미 있는 열쇠다? -> 땅바닥과 다름 없음.(그대로 이동)
					}
					
					//문 만났을 때
					if(board[0][nr][nc] >= 'A' && board[0][nr][nc] <= 'F') {
						int wall = board[0][nr][nc]-'A';
						
						//열쇠가 없다? -> 벽과 다름 없음.(이동 불가)
						if((cur.keys & 1<<wall) == 0) continue;
						//열쇠가 있다? -> 땅바닥과 다름 없음.(그대로 이동)
					}
					
					dq.offer(new Minsik(nr, nc, next_key, cur.move+1));
					board[next_key][nr][nc] = '#';
				}
			}
		}
		
		return -1;
	}


	static boolean check(int nr, int nc) {
		return (nr >= 0 && nr < N) && (nc >= 0 && nc < M);
	}
}
