import java.io.*;
import java.util.*;

public class Main {
	static int N;
	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		/* 초기화 */
		Queue<Integer> q = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		int rear = -1;
		
		/* 로직 */
		while(N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			if(str.equals("push")) {
				int x = Integer.parseInt(st.nextToken());
				rear = x;
				q.offer(x);
				continue;
			}else if(str.equals("pop")) {
				if(q.isEmpty()) sb.append(-1);
				else sb.append(q.poll());
			}else if(str.equals("size")) {
				sb.append(q.size());
			}else if(str.equals("empty")) {
				if(q.isEmpty()) sb.append(1);
				else sb.append(0);
			}else if(str.equals("front")) {
				if(q.isEmpty()) sb.append(-1);
				else sb.append(q.peek());
			}else {
				if(q.isEmpty()) sb.append(-1);
				else sb.append(rear);
			}
			
			sb.append("\n");
		}
		
		/* 출력 */ 
		System.out.print(sb);
	}

}
