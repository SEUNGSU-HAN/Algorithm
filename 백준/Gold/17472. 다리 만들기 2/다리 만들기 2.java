import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] board;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int landCount; //섬의 개수
	static ArrayList<int[]>[] land; //섬들 모음
	static ArrayList<ArrayList<Integer>> conn; //각 섬들의 다리 연결 정보
	
	static class Edge implements Comparable<Edge>{
		int s, e, w;

		public Edge(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}

		//중복 다리 제거를 위한 equals 메서드 오버라이딩
		@Override
		public boolean equals(Object obj) {
			if(!(obj instanceof Edge)) return false;
			Edge edge = (Edge)obj;
			//2 -> 3 4 와 3 -> 2 4 는 같은 거기에 배제
			return ((this.s == edge.s) && (this.e == edge.e) && (this.w == edge.w)
					|| (this.s == edge.e) && (this.e == edge.s) && (this.w == edge.w));
		}
	}
	
	static PriorityQueue<Edge> points; //최종 건설된 다리의 정보
	static int[] p, r;
	static int min = -1;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		board = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = (Integer.parseInt(st.nextToken()) == 1) ? -1 : 0;
			}
		}
		visited = new boolean[N][M];
		land = new ArrayList[101];
		for (int i = 0; i < 101; i++) {
			land[i] = new ArrayList<>();
		}
		points = new PriorityQueue<>();
		
		
		/* 로직 */
		//1. 단 FF 돌려 (섬을 찾아)
		ff();
		conn = new ArrayList<>();
		for (int i = 0; i <= landCount; i++) {
			conn.add(new ArrayList<>());
		}
		
		//2. 제 다리를 연결해
		// 각 섬에서 연결할 수 있는 모든 다리를 일단 연결해서 points에 정보 저장
		// 이후 MST를 거쳐서 최소 경로를 찾으면 됨
		for (int i = 1; i <= landCount; i++) {
			if(land[i].size() == 0) continue;
			visited = new boolean[N][M];
			createBridge(i);
		}
		
		
		//3. MST를 돌려
		//+)모든 섬이 연결되어 있는지 확인해줘야함
		if(points.size() != 0 && isConn(1)) {
			//다리가 1개 이상일 때만 돌려
			min = 0;
			init();
			int cnt = 0;
			while(cnt != landCount-1) {
				Edge edge = points.poll();
				if(union(edge.s, edge.e)) {
					cnt++;
					min += edge.w;
				}
			}
		}
		
		
		/* 출력 */
		System.out.print(min);
	}


	private static boolean isConn(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(start);
		int flag = 1<<start;
		int count = 1;
		while(!q.isEmpty()) {
			int cur = q.poll();
			for (int i = 0; i < conn.get(cur).size(); i++) {
				int next = conn.get(cur).get(i);
				if((flag & 1<<next) == 0) {
					q.offer(next);
					flag |= 1<<next;
					count++;
				}
			}
		}
		return count == landCount ? true : false;
	}

	static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x == y) return false;
		if(r[x] < r[y]) {
			r[y] += r[x];
			p[x] = y;
		}else {
			r[x] += r[y];
			p[y] = x;
		}
		return true;
	}

	static int find(int x) {
		if(x == p[x]) return p[x];
		else return p[x] = find(p[x]);
	}

	static void init() {
		p = new int[landCount+1];
		r = new int[landCount+1];
		for (int i = 0; i < landCount+1; i++) {
			p[i] = i;
			r[i] = 1;
		}
	}

	static void createBridge(int s) {
		for (int i = 0; i < land[s].size(); i++) {
			int[] cur = land[s].get(i);
			for (int j = 0; j < 4; j++) {
				int nr = cur[0]+dr[j];
				int nc = cur[1]+dc[j];
				int bridgeCount = 0; //건설할 다리 개수(다리의 가중치 값)
				int e = 0; //현재섬(s)와 연결될섬
				while(check(nr, nc) && board[nr][nc] != s) {
					//바다가 아니면 다리를 지을 수 없음
					if(board[nr][nc] != 0) {
						e = board[nr][nc];
						break;
					}
					//섬 주변에 바다가 보이면 일진선으로 쭉~ 간다. (다른 섬에 닿을 때 까지)
					bridgeCount++; //다리 한 칸 건설
					nr += dr[j];
					nc += dc[j];
				}
				//건설된 다리의 길이가 2이상이면 건설 성공!(아니면 빠꾸)
				if(bridgeCount >= 2 && e != 0) {
					Edge edge = new Edge(s, e, bridgeCount);
					//섬들간 다리 연결 정보 저장 (양방향)
					if(!conn.get(s).contains(e)) conn.get(s).add(e);
					//시작점, 목적지, 가중치를 모두 얻었기에 우선순위 큐에 엣지 추가
					if(!points.contains(edge)) points.offer(edge);
				}
			}
		}
	}

	static void ff() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(board[i][j] == -1) {
					landCount++;
					bfs(new int[] {i, j});
				}
			}
		}
	}

	static void bfs(int[] start) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(start);
		visited[start[0]][start[1]] = true;
		board[start[0]][start[1]] = landCount;
		land[landCount].add(start);
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur[0]+dr[i];
				int nc = cur[1]+dc[i];
				if(check(nr, nc) && board[nr][nc] == -1) {
					visited[nr][nc] = true;
					int[] nextNode = new int[] {nr, nc};
					q.offer(nextNode);
					board[nr][nc] = landCount;
					land[landCount].add(nextNode);
				}
			}
		}
	}

	private static boolean check(int nr, int nc) {
		return (0<=nr&&nr<N) && (0<=nc&&nc<M);
	}

}
