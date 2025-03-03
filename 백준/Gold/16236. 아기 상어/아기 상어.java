import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static final int INF = 999;
	static int N;
	static int eat, move; //아기 상어가 먹은 물고기 수, 이동 시간
	static int[][] board;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	static class Fish implements Comparable<Fish>{
		int r, c, size;
		int distance;
		
		Fish(int r, int c, int size) {
			this.r = r;
			this.c = c;
			this.size = size;
		}
		
		@Override
		public int compareTo(Fish f) {
			return Integer.compare(this.size, f.size);
		}
	}
	static Fish shark;
	static PriorityQueue<Fish> feeds;
	static boolean cantEat;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		board = new int[N][N];
		feeds = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 9) shark = new Fish(i, j, 2);
				//우선은 사이즈 순으로 정렬 삽입
				else if(board[i][j] > 0) feeds.offer(new Fish(i, j, board[i][j]));
			}
		}		
				
		/* 로직 */
		LinkedList<Fish> fl = new LinkedList<>();
		//먹이를 정렬해놓고 하나씩 빼먹으며 위치 파악
		while(true) {			
			//크기를 기준으로 먹을 수 있는 애들 저장
			while(feeds.peek() != null && feeds.peek().size < shark.size) {
				fl.add(feeds.poll());
			}
			
			//더 이상 먹을 수 있는 먹이가 없으면 끝
			if(fl.size() == 0) break;
			
			//현재 먹을 수 있는 애들의 거리 조사
			for (int i = 0; i < fl.size(); i++) {
				fl.get(i).distance = setDistance(new int[] {fl.get(i).r, fl.get(i).c}, getVisited());				
			}
			
			//우선순위별로 정렬
			Collections.sort(fl, (Fish f1, Fish f2) -> {
				if(f1.distance == f2.distance) {
					if(f1.r == f2.r) {
						return Integer.compare(f1.c, f2.c);
					}else return Integer.compare(f1.r, f2.r);
				}
				return Integer.compare(f1.distance, f2.distance);
			});
			
			//정렬된 먹이를 기준으로 가장 첫번째 먹이를 먹으러감
			//첫번째 먹이 out
			Fish f = fl.poll();
			//단, 첫 번째 먹이의 거리가 INF(999)면 더이상 먹을게 없음 끝
			if(f.distance == INF) break;
			//아니라면 상어 위치를 첫 번째 먹이를 먹으면서 이동
			//보드판 갱신
			board[shark.r][shark.c] = 0;
			board[f.r][f.c] = 0; 
			move += f.distance; //상어 이동 횟수 증가
			//상어 이동
			shark.r = f.r;
			shark.c = f.c;
			eat++; //상어 경험치 증가
			if(eat == shark.size) {
				//상어 레벨업
				eat = 0;
				shark.size++;
			}
		}
		
		/* 출력 */
		System.out.print(move);
		
	}
	static int setDistance(int[] start, boolean[][] visited) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {start[0], start[1], 0});
		visited[start[0]][start[1]] = true;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			if(cur[0] == shark.r && cur[1] == shark.c) {
				return cur[2];
			}
			for (int i = 0; i < 4; i++) {
				int nr = cur[0]+dr[i];
				int nc = cur[1]+dc[i];
				if(check(nr,nc) && !visited[nr][nc]) {
					q.offer(new int[] {nr, nc, cur[2]+1});
					visited[nr][nc] = true;
				}
			}
		}
		
		return INF;
	}
	
	static boolean[][] getVisited(){
		boolean[][] visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(board[i][j] != 9 && board[i][j] > shark.size) visited[i][j] = true;
			}
		}
		return visited;
	}

	static boolean check(int nr, int nc) {
		return (0 <= nr && nr < N) && (0 <= nc && nc < N);
	}

}
