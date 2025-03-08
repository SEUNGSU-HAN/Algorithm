import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static String[] poketmon; //숫자 -> 문자 탐색용
	static HashMap<String, Integer> poketnum; //문자 -> 숫자 탐색용

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
				
		/* 초기화 */
		poketmon = new String[N+1];
		poketnum = new HashMap<>();
		for (int i = 1; i <= N; i++) {
			String str = br.readLine();
			poketmon[i] = str;
			poketnum.put(str, i); //동명 포켓몬은 가장 높은 숫자로 갱신
		}
		
		/* 로직 */
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			String q = br.readLine();
			char c = q.charAt(0); //첫번째가 문자인지 숫자인지
			if(Character.isDigit(c)) {
				//숫자면 -> 해당 index 포켓몬으로
				int n = Integer.parseInt(q);
				sb.append(poketmon[n]);
			}
			else {
				//문자면 -> 해당 포켓몬의 index로
				int n = poketnum.get(q);
				sb.append(n);
			}
			sb.append("\n");
		}
		
		
		/* 출력 */
		System.out.print(sb);
		
	}

}
