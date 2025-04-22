import java.io.*;
import java.util.*;

public class Solution {
	static int T , N, result;
	static int[][] board;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine().trim());
			
			board = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			result = Integer.MAX_VALUE;
			
			choiceFood(0, 0);
			
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}

	static void choiceFood(int cnt, int flag) {
		if(Integer.bitCount(flag) > N/2) return;
		if(cnt == N) {
			if(Integer.bitCount(flag) != N/2) return;
			int sumA = 0, sumB = 0;
			for (int i = 0; i < N; i++) {
				for (int j = i+1; j < N; j++) {
					
					if(((flag & 1<<i) > 0 && (flag & 1<<j) > 0) || ((flag & 1<<i) == 0 && (flag & 1<<j) ==0)) {
						if((flag & 1<<i) > 0) {
							sumA += board[i][j];
							sumA += board[j][i];
						}
						else {
							sumB += board[i][j];
							sumB += board[j][i];
						}
					}
				}
			}			
			result = Math.min(result, Math.abs(sumA-sumB));
			
			return;
		}
		choiceFood(cnt+1, flag | 1<<cnt);
		choiceFood(cnt+1, flag);
	}

}
