import java.io.*;
import java.util.*;

public class Main {
	static int M;
	static boolean[] cup;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		cup = new boolean[4];
		cup[1] = true;
		
		/* 로직 */
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			boolean temp = cup[s];
			cup[s] = cup[e];
			cup[e] = temp;
		}
		
		/* 출력 */
		for (int i = 1; i <= 3; i++) {
			if(cup[i]) {
				System.out.print(i);
				break;
			}
		}
		
	}

}
