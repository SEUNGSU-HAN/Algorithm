import java.io.*;
import java.util.*;

public class Main {
	static int N, x;
	static String str;
	static ArrayDeque<Integer> dq;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		dq = new ArrayDeque<>();
		
		StringBuilder sb = new StringBuilder();
		while(N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			if(st.countTokens() > 1) {
				str = st.nextToken();
				x = Integer.parseInt(st.nextToken());
			}else str = st.nextToken();

			switch(str) {
				case "push_front":
					dq.offerFirst(x);
					break;
				case "push_back":
					dq.offerLast(x);
					break;
				case "pop_front":
					sb.append(dq.isEmpty() ? -1 : dq.pollFirst()).append("\n");
					break;
				case "pop_back":
					sb.append(dq.isEmpty() ? -1 : dq.pollLast()).append("\n");
					break;
				case "size":
					sb.append(dq.size()).append("\n");
					break;
				case "empty":
					sb.append(dq.isEmpty() ? 1 : 0).append("\n");
					break;
				case "front":
					sb.append(dq.isEmpty() ? -1 : dq.peekFirst()).append("\n");
					break;
				case "back":
					sb.append(dq.isEmpty() ? -1 : dq.peekLast()).append("\n");
					break;
			}
		}
		
		System.out.println(sb.toString());
	}

}
