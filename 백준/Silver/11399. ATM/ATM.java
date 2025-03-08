import java.io.*;
import java.util.*;

public class Main {
	static int N, result;
	static int[] time;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		time = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			time[i] = Integer.parseInt(st.nextToken());
		}
		
		/* 로직 */
		Arrays.sort(time);
		int sum = 0;
		for (int i = 0; i < N; i++) {
			sum = sum+time[i];
			result += sum;
		}
		
		/* 출력 */
		System.out.print(result);
	}

}
