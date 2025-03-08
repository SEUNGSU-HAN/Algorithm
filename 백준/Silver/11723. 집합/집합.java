import java.io.*;
import java.util.*;

public class Main {
	static int M, n;
	static String str;
	static Set<Integer> set;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		set = new HashSet<>();
		
		/* 로직 */
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			if(str.equals("all")) {
				int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
//				List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20));
				for (int j = 0; j < nums.length; j++) {
					set.add(nums[j]);
				}
//				set.addAll(list);
			} else if(str.equals("empty")) set.clear();
			else {
				int x = Integer.parseInt(st.nextToken());
				if(str.equals("add")) set.add(x);
				else if(str.equals("remove")) set.remove(x);
				else if(str.equals("check")) sb.append(set.contains(x) ? 1 : 0).append("\n");
				else if(str.equals("toggle")) {
					if(set.contains(x)) set.remove(x);
					else set.add(x);
				}
			}
		}
		
		/* 출력 */
		System.out.print(sb);
	}

}
