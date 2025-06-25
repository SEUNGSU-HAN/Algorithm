import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static String ps;
	static ArrayDeque<Character> stack;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		/* 초기화 */
		stack = new ArrayDeque<>();
		
		/* 로직 */
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			ps = br.readLine();
			for (int j = 0; j < ps.length(); j++) {
				if(ps.charAt(j) == '(') {
					stack.offer('(');
				}
				else {
					if(!stack.isEmpty()) {
						if(stack.peekLast() == '(') stack.pollLast();
						else stack.offer(')');
					}else stack.offer(')');
				}
			}
			
			if(stack.isEmpty()) sb.append("YES");
			else sb.append("NO");
			sb.append("\n");
			stack.clear();
		}
		
		/* 출력 */
		System.out.println(sb.toString());
		
	}

}
