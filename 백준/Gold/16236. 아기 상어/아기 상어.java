import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//아기 상어 -> Collections.sort 말고 사방 탐색시 상,좌,우,하 순으로 탐색
public class Main {
	static int N;
	static int fishes, eat, move, size = 2; //총물고기 수, 상어가 먹은 물고기 수, 이동 시간, 상어 크기
	static int[][] board;
	static int[] dr = {-1, 0, 0, 1}; //상좌우하 순
	static int[] dc = {0, -1, 1, 0};
	static int[] shark;

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
				if(board[i][j] == 9) shark = new int[] {i, j};
				else if(board[i][j] > 0) fishes++;
			}
		}		
				
		/* 로직 */
		while(fishes > 0) {
			Queue<int[]> q = new ArrayDeque<>();
			boolean[][] visited = new boolean[N][N];
			q.offer(new int[] {shark[0], shark[1], 0});
			visited[shark[0]][shark[1]] = true;
			
			int eatR = -1, eatC = -1; //상어가 먹을 먹이 좌표
			int minDis = 999;
			while(!q.isEmpty()) {
				int[] cur = q.poll();
				for (int i = 0; i < 4; i++) {
					int nr = cur[0]+dr[i];
					int nc = cur[1]+dc[i];
					int nd = cur[2]+1;
					if(check(nr, nc) && !visited[nr][nc] && board[nr][nc] <= size) {
						if(board[nr][nc] == 0 || board[nr][nc] == size) {//먹이 아니면 갈 길 가야지 (먹이찾으러)
							q.offer(new int[] {nr, nc, nd});
							visited[nr][nc] = true;
						}else {	//왔더니만 먹이다?
							//먹이 좌표 설정
							if(nd < minDis) {
								//가까운 먹이 발견시 갱신
								eatR = nr; eatC = nc;
								minDis = nd;
							}else if(nd == minDis) {
								//같은 거리 먹이 존재시 더 윗놈
								if(eatR > nr) {
									eatR = nr;
									eatC = nc;
								}else if(eatR == nr) { //행도 같다면 더 왼쪽 놈
									if(eatC > nc) {
										eatC = nc;
									}
								}
							}
							visited[nr][nc] = true;
						}
						
					}
				}
			}
			//먹이 찾았는지 체크
			if(eatR == -1 || eatC == -1 || minDis == 999) break;
			
			//찾았다면 보드 및 상어 데이터 갱신
			board[eatR][eatC] = 9;
			board[shark[0]][shark[1]] = 0;
			shark[0] = eatR;
			shark[1] = eatC;
			move += minDis;
			fishes--;
			eat++;
			if(eat == size) {
				size++;
				eat = 0;
			}
		}
		
		/* 출력 */
		System.out.print(move);
		
	}

	static boolean check(int nr, int nc) {
		return (0 <= nr && nr < N) && (0 <= nc && nc < N);
	}

}
