import java.io.*;
import java.util.*;

public class Main {
	static int N, count;
	static ArrayList<Integer> colList;
	
	public static void main(String[] args) throws Exception{
		/* 셋팅 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		colList = new ArrayList<>();
		
		/* 로직 */
		backtracking(0, 0, 0, 0);
		
		/* 출력 */
		System.out.print(count);
	}

	static void backtracking(int cnt, int colCheck, int rDigCheck, int lDigCheck) {
		if(cnt == N) {
			count++;
			return;
		}
		for(int i=0; i<N; i++) {
			//열 체크
			if((colCheck & 1<<i) != 0) continue;
			
			//대각선 체크
			if(((rDigCheck & 1<<(cnt+i)) != 0) || 
				((lDigCheck & 1<<(cnt-i+N)) != 0)) continue;
			
			backtracking(cnt+1, (colCheck | 1<<i), (rDigCheck | 1<<(cnt+i)), (lDigCheck | 1<<(cnt-i+N)));
		}
	}

}
