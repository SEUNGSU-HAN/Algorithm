import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static Map<String, Integer> map;
	static ArrayList<String> job;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
				
		/* 초기화 */
		map = new HashMap<>();
		job = new ArrayList<>();
		
		/* 로직 */
		for (int i = 0; i < N+M; i++) {
			String str = br.readLine();
			if(map.containsKey(str)) map.put(str, map.get(str)+1);
			else map.put(str, 1);
		}
		
		//듣보잡 뽑기
		for (String key : map.keySet()) if(map.get(key) == 2) job.add(key);
		Collections.sort(job);
		
		/* 출력 */
		StringBuilder sb = new StringBuilder();
		sb.append(job.size()).append("\n");
		for (String str : job) {
			sb.append(str).append("\n");
		}
		System.out.print(sb);
	}

}
