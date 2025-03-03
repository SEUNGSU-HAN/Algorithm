import java.io.*;
import java.util.*;

public class Main {
	static int N, M, time = -1;
	static int[][] board;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static ArrayList<int[]> canVirus;
	static int[] choice;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
				
		/* 초기화 */
		board = new int[N][N];
		canVirus = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 2) canVirus.add(new int[] {i, j});
			}
		}
		choice = new int[M];
		
		/* 로직 */
		combi(0, 0, 0);
		
		
		/* 출력 */
		System.out.print(time);
	}

	static void combi(int cnt, int start, int flag) {
		if(cnt == M) {
			int v = 0, area = 0; //바이러스 개수, 바이러스가 아닌 영역 개수
			visited = new boolean[N][N];
			//깊은 복사
			int[][] temp = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(board[i][j] == 0) {
						temp[i][j] = board[i][j];
						area++;
					}else if(board[i][j] == 2) {
						temp[i][j] = 0;
						area++;
					}else {
						visited[i][j] = true;
					}
				}
			}
			
			//테스트 보드에 바이러스 표시(나머지 바이러는 지웠음)
			Queue<int[]> q = new ArrayDeque<>();
			for (int i = 0; i < M; i++) {
				int r = canVirus.get(choice[i])[0];
				int c = canVirus.get(choice[i])[1];
				temp[r][c] = 2;
				q.offer(new int[] {r, c});
				visited[r][c] = true;
				v++;
			}
			
			//바이러스퍼트려~ (bfs)
			int t = -1; //board는 꽉찼지만 큐를 털기 위해 마지막에 한번 더 들어가기에 -1 스타트
			while(!q.isEmpty()) {
				int n = q.size();
				while(n-- > 0) { //같은 depth의 애들만 돌리기
					int[] cur = q.poll();
					for (int i = 0; i < 4; i++) {
						int nr = cur[0]+dr[i];
						int nc = cur[1]+dc[i];
						if(check(nr, nc) && !visited[nr][nc] && temp[nr][nc] != 1) {
							q.offer(new int[] {nr, nc});
							visited[nr][nc] = true;
							temp[nr][nc] = 2;
							v++;
						}
					}
				}
				t++; //바이러스 퍼트림
			}
			
			//바이러스 둔 위치별 최소 시간 찾기
			if(v == area) { //퍼진 바이러스량과 기존 일반 영역이 같으면 다 퍼트린거임
				if(time == -1) time = t;
				else time = Math.min(time, t);
			}
			return;
		}
		
		for (int i = start; i < canVirus.size(); i++) {
			if((flag & 1<<i) != 0) continue;
			choice[cnt] = i;
			combi(cnt+1, i, (flag | 1<<i));
		}
	}

	static boolean check(int nr, int nc) {
		return (0 <= nr && nr < N) && (0 <= nc && nc < N);
	}

}
