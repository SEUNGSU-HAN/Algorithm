import java.io.*;
import java.util.*;

public class Solution {
	static int T, N, W, H, wall, result;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int[][] board;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			/* 입력 */
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			
			/* 초기화 */
			wall = 0;
			result = Integer.MAX_VALUE;
			board = new int[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if(board[i][j] != 0) wall++;
				}
			}
			
			/* 로직 */
			breakWall(0, wall, deepCopy(board)); //발사 횟수, 남은 벽돌 수, 복사 맵
			
			/* 출력 */
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}

	static int[][] deepCopy(int[][] src) {
		int[][] dest = new int[H][W];
		for (int i = 0; i < H; i++) {
			System.arraycopy(src[i], 0, dest[i], 0, src[i].length);
		}
		return dest;
	}

	static void breakWall(int cnt, int w, int[][] b) {
		if(cnt == N) {
			result = Math.min(result, w);
			return;
		}
		for (int i = 0; i < W; i++) {
			int[][] temp = deepCopy(b);
			
			//1. 해당 열로 공을 발사
			int hitStone = -1;
			for (int j = 0; j < H; j++) {
				if(temp[j][i] != 0) {
					hitStone = j;
					break;
				}
			}
			if(hitStone == -1) continue;
			//2. 연쇄 폭발
			int broken = bfs(new int[] {hitStone, i}, temp);			
			
			//3. 벽돌 정리
			reBuildBoard(temp);
			
			//4. 다음 공 발사
			breakWall(cnt+1, w-broken, deepCopy(temp));
		}
		breakWall(cnt+1, w, deepCopy(b));
	}

	static void reBuildBoard(int[][] b) {
		for (int i = 0; i < W; i++) {
			int zeroPoint = H-1;
			int swapPoint = H-1;
			while(zeroPoint >= 0 && swapPoint >= 0) {
				//zeroPoint 이동
				while(zeroPoint > 0 && b[zeroPoint][i] != 0) zeroPoint--;
				if(zeroPoint <= 0) break;
				
				//swapPoint 이동
				swapPoint = zeroPoint;
				while(swapPoint > 0 && b[swapPoint][i] == 0) swapPoint--;
				
				//swap
				int temp = b[zeroPoint][i];
				b[zeroPoint][i] = b[swapPoint][i];
				b[swapPoint][i] = temp;
				
				//포인터 자리 정리
				zeroPoint--;
				swapPoint--;
			}
		}
	}

	static int bfs(int[] start, int[][] b) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(start);
		
		boolean[][] visited = new boolean[H][W];
		visited[start[0]][start[1]] = true;
		
		int count = 0;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int area = b[cur[0]][cur[1]]-1;
			b[cur[0]][cur[1]] = 0;
			count++;
			for (int i = 0; i < 4; i++) {
				for (int j = 1; j <= area; j++) {
					int nr = cur[0]+dr[i]*j;
					int nc = cur[1]+dc[i]*j;
					if(!check(nr, nc)) break;
					if(!visited[nr][nc] && b[nr][nc] != 0) {
						visited[nr][nc] = true;
						q.offer(new int[] {nr, nc});
					}
				}
			}
		}
		
		return count;
	}

	static boolean check(int nr, int nc) {
		return (0 <= nr && nr < H) && (0 <= nc && nc < W);
	}

}
