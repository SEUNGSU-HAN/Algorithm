import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static Map<String, String> pw;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		pw = new HashMap<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			pw.put(st.nextToken(), st.nextToken());
		}
		
		/* 로직 */
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			sb.append(pw.get(st.nextElement())).append("\n");
		}
		
		/* 출력 */
		System.out.print(sb);
	}

}
