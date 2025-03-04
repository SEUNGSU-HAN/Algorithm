import java.io.*;
import java.util.*;

public class Main {
	static int R, C, time;
	static char[][] board, temp;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int[] dochi, biber;
	static Queue<int[]> q;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		board = new char[R][C];
		visited = new boolean[R][C];
		q = new ArrayDeque<>();
		for (int i = 0; i < R; i++) {
			board[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if(board[i][j] == '*') {
					q.offer(new int[] {i, j});
					visited[i][j] = true;
				}
				else if(board[i][j] == 'S') {
					dochi = new int[] {i, j, 0};
					visited[i][j] = true;
				}
				else if(board[i][j] == 'D') biber = new int[] {i, j};
			}
		}
		temp = new char[R][C];
		//물 먼저, 그 다음 고슴도치 추가
		q.offer(dochi);
		
		/* 로직 */
		loop:
		while(!q.isEmpty()) {
			int n = q.size();
			deepCopy(board, temp);
			//같은 depth의 애들끼리 돌리기
			while(n-- > 0) {
				int[] cur = q.poll();
				for (int i = 0; i < 4; i++) {
					int nr = cur[0]+dr[i];
					int nc = cur[1]+dc[i];
					if(check(nr, nc) && board[nr][nc] != 'X' && board[nr][nc] != '*') {
						if(temp[cur[0]][cur[1]] == '*') {
							if(temp[nr][nc] == 'D') continue;
							q.offer(new int[] {nr, nc});
							visited[nr][nc] = true;
							board[nr][nc] = '*';
						}else if(temp[cur[0]][cur[1]] == 'S') {
							if(visited[nr][nc]) continue;
							if(temp[nr][nc] == 'D') {
								//도치가 비버 집에 도착했다면
								time = cur[2]+1;
								break loop;
							}
							q.offer(new int[] {nr, nc, cur[2]+1});
							visited[nr][nc] = true;
							board[nr][nc] = 'S';
						}
					}
				}
			}
		}
		
		/* 출력 */
		System.out.print(time == 0 ? "KAKTUS" : time);
	}

	static void print(char[][] b) {
		System.out.println("============");
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(b[i][j] + " ");
			}
			System.out.println();
		}
	}

	static void deepCopy(char[][] src, char[][] dest) {
		for (int i = 0; i < R; i++) {
			System.arraycopy(src[i], 0, dest[i], 0, src[i].length);
		}
	}

	static boolean check(int nr, int nc) {
		return (0 <= nr && nr < R) && (0 <= nc && nc < C);
	}

}
