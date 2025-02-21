import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
	static int T, N;
	static Map<String, ArrayList<String>> clothes;
	static int[] cnums;
	static int result;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		
		for (int t = 0; t < T; t++) {
			/* 입력 */
			N = Integer.parseInt(br.readLine().trim());
			
			/* 초기화 */
			result = 1;
			clothes = new HashMap<>();
			for (int i = 0; i < N; i++) {
				String[] cl = br.readLine().split(" ");
				if(!clothes.containsKey(cl[1]))	clothes.put(cl[1], new ArrayList<>());
				clothes.get(cl[1]).add(cl[0]);
			}
			cnums = new int[clothes.size()];
			int idx = 0;
			for (String s : clothes.keySet()) {
				cnums[idx++] = clothes.get(s).size()+1;
			}

			
			/* 로직 */
			for (int i = 0; i < clothes.size(); i++) {
				result *= cnums[i];
			}
			
			
			sb.append(result-1).append("\n");
		}
		
		/* 출력 */
		System.out.print(sb);
	}

}
