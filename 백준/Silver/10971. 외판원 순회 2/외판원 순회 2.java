import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//외판원 순회2
public class Main {
	static int N;
	static int[][] W, dp;
	static final int INF = 9999999;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		W = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp = new int[N][1<<N]; //N*2^N
		for (int i = 0; i < N; i++) {
			//INF로 초기화 하면 시간초과 뜸
			//-> 방문은 했는데 dp 값이 INF라서 방문 했는지 안했는지를 파악하기 힘듬
			//그래서 -1로 초기화하고 방문 여부를 INF를 통해서 판단함
			Arrays.fill(dp[i], -1);			
		}

		/* 로직 */
		System.out.print(tsp(0, 1));
		
		/* 출력 */
	}

	private static int tsp(int start, int visited) {
		if(visited == (1<<N)-1) { //모든 도시 방문 했을 경우 (11111)
			//다시 첫 도시로 갈 길이 있다면 갱신
			//없다면 결구 INF 값이 반환될 것임
			if(W[start][0] != 0) {
				dp[start][visited] = W[start][0];
				return dp[start][visited];
			}
			return INF;
		}
		
		//이미 기록된 값이 라면 재활용
		if(dp[start][visited] != -1) return dp[start][visited];
		
		dp[start][visited] = INF; //방문과 동시에 dp값을 초기화 (INF가 됬다 = 방문했다)
		for (int i = 1; i < N; i++) {
			if((visited & 1<<i) > 0 || W[start][i] == 0) continue; //방문 여부, 다음 도시로 갈 길이 없는 경우
			
			//다음도시 까지의 경로 값 + 다음 도시에 갔다 쳤을 때 나올 수 있는 최소 경로값
			dp[start][visited] = Math.min(dp[start][visited], tsp(i, visited | 1<<i) + W[start][i]);
		}
		
		
		return dp[start][visited];
	}

}
