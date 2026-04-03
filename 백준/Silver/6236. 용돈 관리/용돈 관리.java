import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] moneys;

	public static void main(String[] args) throws Exception{
		/* 셋팅 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		moneys = new int[N];
		int total = 0;
		
		int max = 0;
		for(int i=0; i<N; i++) {
			moneys[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, moneys[i]);
			total += moneys[i];
		}
		
		/* 로직 */
		
		int l=max, r=total;
		int result = Integer.MAX_VALUE;
		
		//이분탐색
		while(l <= r) {
			//K값 선정
			int k = (l+r)/2;
			
			int count = 0;
			int temp = 0;
			for(int i=0; i<N; i++) {				
				if(count > M) break;
				
				if(temp < moneys[i]) {
					count++;
					temp = k;
				}
				
				temp -= moneys[i];
			}
						
			//k값으로 인출 횟수 검수
			if(count <= M) {
				result = Math.min(result, k);
				//최소 횟수 보다 같거나 적다면 값을 줄여
				r = k-1;
			}else {
				l = k+1;
			}
		}
		
		
		/* 출력 */
		System.out.print(result);
	}

}
