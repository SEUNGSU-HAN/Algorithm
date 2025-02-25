import java.io.*;
import java.util.*;

public class Solution {
	static int T, N;
	static int[] home, company;
	static Customer[] customers;
	static int[] nums;
	static int minDis;
	
	static class Customer{
		int x, y;
		Customer(int x, int y){
			this.x = x; this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		for (int testcase = 1; testcase <= T; testcase++) {
			/* 입력 */
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			/* 초기화 */
			home = new int[2];
			company = new int[2];
			customers = new Customer[N];
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				home[j] = Integer.parseInt(st.nextToken());
			}
			for (int j = 0; j < 2; j++) {
				company[j] = Integer.parseInt(st.nextToken());
			}
			for (int i = 0; i < N; i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				customers[i] = new Customer(x, y);
			}
			nums = new int[N];
			minDis = Integer.MAX_VALUE;
			
			/* 로직 */
			perm(0, 0);
			
			/* 출력 */
			sb.append("#").append(testcase).append(" ").append(minDis).append("\n");
		}
		System.out.print(sb);
	}

	private static void perm(int cnt, int flag) {
		if(cnt == N) {
			int sum = getDistance(company[0], company[1], customers[nums[0]].x, customers[nums[0]].y);
			for (int i = 1; i < N; i++) {
				sum = sum + getDistance(customers[nums[i-1]].x, customers[nums[i-1]].y, customers[nums[i]].x, customers[nums[i]].y); 
			}
			sum = sum + getDistance(home[0], home[1], customers[nums[N-1]].x, customers[nums[N-1]].y);
			minDis = Math.min(minDis, sum);
			return;
		}
		for (int i = 0; i < N; i++) {
			if((flag & 1 << i) != 0) continue;
			nums[cnt] = i;
			perm(cnt+1, flag | 1 << i);
		}
	}
	
	private static int getDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1-x2)+Math.abs(y1-y2);
	}

}
