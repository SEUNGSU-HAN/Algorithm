import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static LinkedList<Integer> stack;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		
		/* 초기화 */
		stack = new LinkedList<>();
		
		/* 로직 */
		int data = 1;
		StringBuilder sb = new StringBuilder();
		
		loop:
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(br.readLine().trim());
			while(data <= n) {
				if(stack.peekLast() != null) {
					if(stack.peekLast() == n) break;
					if( stack.peekLast() > n) {
						sb.delete(0, sb.length());
						sb.append("NO");
						break loop;
					}
				}
				stack.add(data++);
				sb.append("+\n");
			}
			int t = stack.pollLast();
			if(t != n) {
				sb.delete(0, sb.length());
				sb.append("NO");
				break;
			}else sb.append("-\n");
		}
		
		
		/* 출력 */
		System.out.print(sb.toString());
	}

}
