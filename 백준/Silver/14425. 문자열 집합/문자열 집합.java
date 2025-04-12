import java.io.*;
import java.util.*;

public class Main {
	static int N, M, count;
	static Set<String> hashset;
	
	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		hashset = new HashSet<>();
		for (int i = 0; i < N; i++) {
			hashset.add(br.readLine().trim());
		}
		
		/* 로직 */
		for (int i = 0; i < M; i++) {
			count += hashset.contains(br.readLine().trim()) ? 1 : 0;
		}
		
		/* 출력 */
		System.out.print(count);
	}
}
