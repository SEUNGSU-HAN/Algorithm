import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] board;
	static BBQ[] chicken;
	static ArrayList<BBQ> bbq;
	static ArrayList<Home> home;
	static int chickenDistance = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		board = new int[N][N];
		chicken = new BBQ[M];
		bbq = new ArrayList<>();
		home = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 2) bbq.add(new BBQ(i, j));
				if(board[i][j] == 1) home.add(new Home(i, j));
			}
		}
		
		/* 로직 */
		//존재하는 치킨집 중 m개를 먼저 뽑아 -> nHm (중복 조합)
		//각 경우의 치킨 거리 값 구하고 최솟값인 치킨 거리 구하기
		//치킨 거리 값 구하기 => 고른 치킨 집 중심으로 bfs 돌려서 depth 더하기
		combi(0, 0);
		
		/* 출력 */
		System.out.println(chickenDistance);
	}
	
	private static void combi(int cnt, int start) {
		if(cnt == M) { //치킨 다 고른 경우
			int chkDis = 0;
			for (int i = 0; i < home.size(); i++) {
				int d1 = Math.abs(home.get(i).r-chicken[0].r) 
						+ Math.abs(home.get(i).c-chicken[0].c);
				for (int j = 1; j < M; j++) {
					int d2 = Math.abs(home.get(i).r-chicken[j].r) 
							+ Math.abs(home.get(i).c-chicken[j].c);
					d1 = Math.min(d1, d2);
				}
				chkDis += d1;
			}
			chickenDistance = Math.min(chickenDistance, chkDis);
			return;
		}
		for (int i = start; i < bbq.size(); i++) {
			chicken[cnt] = bbq.get(i);
			combi(cnt+1, i+1);
			chicken[cnt] = null;
		}
		
	}

	static class Home{
		int r, c;
		Home(int r, int c){ this.r = r; this.c = c;}
	}
	
	static class BBQ{
		int r, c;
		BBQ(int r, int c){ this.r = r; this.c = c;}
	}

}
