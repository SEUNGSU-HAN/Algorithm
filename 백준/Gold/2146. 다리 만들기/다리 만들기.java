import java.io.*;
import java.util.*;

public class Main {
	static int N, landCount;
	static int[][] board;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static ArrayList<ArrayList<int[]>> land;
	static int bridge = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 1) board[i][j] = -1;
			}
		}
		land = new ArrayList<>();
		
		/* 로직 */
		//1.단 FF 돌려서 섬을 나눠
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(board[i][j] == -1) {
					landCount++;
					land.add(new ArrayList<>());
					visited = new boolean[N][N];
					bfs(new int[] {i, j});
				}
			}
		}
		//섬 별로 bfs 돌려서 다른 섬 찾으면 다리 연결
		//연결 시 거리 체크
		for (int i = 0; i < landCount; i++) {
			for (int j = 0; j < land.get(i).size(); j++) {
				visited = new boolean[N][N];
				createBridge(new int[] {land.get(i).get(j)[0], land.get(i).get(j)[1], 0}, i+1);
			}
		}
		
		/* 출력 */
		System.out.print(bridge);
	}

	static void createBridge(int[] start, int landNum) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(start);
		visited[start[0]][start[1]] = true;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			if(cur[2] > bridge) continue;
			for (int i = 0; i < 4; i++) {
				int nr = cur[0]+dr[i];
				int nc = cur[1]+dc[i];
				if(check(nr, nc) && !visited[nr][nc]) {
					if(board[nr][nc] == landNum) {
						visited[nr][nc] = true;
						continue;
					}
					if(board[nr][nc] == 0) {
						q.offer(new int[] {nr, nc, cur[2]+1});
						visited[nr][nc] = true;
					}else {
						//다른 섬을 만남 -> 다리 최소 길이 체크
						bridge = Math.min(bridge, cur[2]);
					}
				}
			}
		}
	}

	static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	static void bfs(int[] start) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(start);
		visited[start[0]][start[1]] = true;
		board[start[0]][start[1]] = landCount;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur[0]+dr[i];
				int nc = cur[1]+dc[i];
				if(check(nr, nc) && !visited[nr][nc]) {
					if(board[nr][nc] == 0) {
						//바다 근처 육지만 고름
						if(!land.get(landCount-1).contains(cur))
							land.get(landCount-1).add(cur);
						continue;
					}
					q.offer(new int[] {nr, nc});
					visited[nr][nc] = true;
					board[nr][nc] = landCount;
				}
			}
		}
	}

	static boolean check(int nr, int nc) {
		return(0 <= nr && nr < N) && (0 <= nc && nc < N);
	}

}
