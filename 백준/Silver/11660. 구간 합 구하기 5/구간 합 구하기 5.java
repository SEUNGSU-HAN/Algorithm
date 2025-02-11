
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	
	static int N,M;
	static int[][] nums;
	static int[][] ans;
	static int startR,startC,endR,endC;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[N][N];
		ans = new int [N][N];
		for (int i = 0; i <N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j <N; j++) {
				nums[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			ans[i][0] = nums[i][0];
			for (int j = 1; j<N; j++) {
				ans[i][j] = ans[i][j-1] + nums[i][j];
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			startR = Integer.parseInt(st.nextToken()) -1;
			startC = Integer.parseInt(st.nextToken()) -1;
			endR = Integer.parseInt(st.nextToken()) -1;
			endC = Integer.parseInt(st.nextToken()) -1;
			int a = 0;
			for (int k = startR; k <= endR; k++) {
				int sum = 0;
					if (startC == 0) {
						sum = ans[k][endC];
					} else {
						sum = ans[k][endC]-ans[k][startC-1];
					}
					a += sum;
			}
			sb.append(a +"\n");
		}
		System.out.println(sb.toString());
	}

}
