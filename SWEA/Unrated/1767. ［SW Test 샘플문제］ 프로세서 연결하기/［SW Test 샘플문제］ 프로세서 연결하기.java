import java.io.*;
import java.util.*;

public class Solution {
	static int N, T, min, maxConn;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int[][] board;
	static ArrayList<int[]> core;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine().trim());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			/* 입력 */
			N = Integer.parseInt(br.readLine().trim());
			
			/* 초기화 */
			board = new int[N+2][N+2];
			for (int i = 0; i < N+2; i++) {
				Arrays.fill(board[i], -2);
			}
			core = new ArrayList<>();
			maxConn = 0;
			for (int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if(board[i][j] == 1) board[i][j] = -1;
					if((i > 1 && i < N && j > 1 && j < N) && board[i][j] == -1) {
						core.add(new int[] {i, j});
					}
				}
			}
			min = Integer.MAX_VALUE;
			
			/* 로직 */
			putLine(0, 0, 0);
			
			/* 출력 */
			sb.append("#").append(test_case).append(" ").append(min).append("\n");
		}
		
		System.out.print(sb);
	}

	static void putLine(int cnt, int count, int connCore) {
		if(cnt == core.size()) {
			if(connCore > maxConn) {
				min = count;
				maxConn = connCore;
			}else if(connCore == maxConn) {
				min = Math.min(min, count);
			}
			return;
		}
		int[] cur = core.get(cnt);
		for (int i = 0; i < 4; i++) {
			int c = 0;
			for (int j = 1; j <= N; j++) {
				int nr = cur[0]+dr[i]*j;
				int nc = cur[1]+dc[i]*j;
				if(board[nr][nc] == -1 || board[nr][nc] > 0) break;
				if(board[nr][nc] == -2) { //전선이 사이드에 닿았음 -> 다음 코어로 넘겨
					putLine(cnt+1, count+c, connCore+1);
					break; //다음 방향을 넘어감
				}
				board[nr][nc] = cnt+1;
				c++;
			}
			//복구 로직
			for (int j = 1; j <= N; j++) {
				int nr = cur[0]+dr[i]*j;
				int nc = cur[1]+dc[i]*j;
				if(board[nr][nc] != cnt+1) break;
				if(board[nr][nc] == -2) { //전선이 사이드에 닿았음 -> 다음 코어로 넘겨
					break; //다음 방향을 넘어감
				}
				board[nr][nc] = 0;
			}
		}
		putLine(cnt+1, count, connCore);
	}
}
