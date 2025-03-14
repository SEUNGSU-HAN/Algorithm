import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static int[] time;
	static Queue<Integer> q;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		time = new int[100_001];
		Arrays.fill(time, -1);
		q = new ArrayDeque<>();
		
		/* 로직 */
		q.offer(N);
		time[N] = 0;
		loop:
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			//0 100000 일 때 틀렸음
			//이유 : 1 -> 2로 갈때 1+1 은 횟수 1회 차지 하지만
			// 1*2는 똑같이 2로 가지만 횟수를 차지 하지 않기에
			// *2 연산을 +1보다 먼저해준다
			int[] next = {cur-1, cur*2, cur+1};
			
			for (int i = 0; i < 3; i++) {
				if(next[i] >= 0 && next[i] < 100_001) {
					if(time[next[i]] == -1) {
						if(i == 1) time[next[i]] = time[cur];
						else time[next[i]] = time[cur]+1;
						if(next[i] == K) break loop;
						q.offer(next[i]);					
					}
				}
			}
		}
		
		/* 출력 */
		System.out.println(time[K]);
	}

}
