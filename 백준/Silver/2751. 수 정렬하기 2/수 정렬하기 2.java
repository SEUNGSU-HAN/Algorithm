import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static Set<Integer> numSet;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		
		/* 초기화 */
		numSet = new TreeSet<>();
		/* 로직 */
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(br.readLine().trim());
			numSet.add(n);
		}
		
		/* 출력 */
		StringBuilder sb = new StringBuilder();
		for (int n : numSet) {
			sb.append(n).append("\n");
		}
		System.out.print(sb);
	}

}
