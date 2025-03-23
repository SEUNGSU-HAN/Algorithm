import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static String command;
	static class Node {
		int x, y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "(" + x + ", " + y + ")";
		}
	}
	static Node drone;
	static Set<String> obstacles;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
				
		/* 초기화 */
		drone = new Node(0, 0);
		obstacles = new HashSet<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			Node node = new Node(x, y);
			obstacles.add(node.toString());
		}
		command = br.readLine().trim();
		
		
		/* 로직 */
		for (int i = 0; i < command.length(); i++) {
			int nx = drone.x;
			int ny = drone.y;
			char nc = command.charAt(i);
			if(nc == 'L') nx -= 1;
			else if(nc == 'R') nx += 1;
			else if(nc == 'U') ny += 1;
			else ny -= 1;
			Node next = new Node(nx, ny);
			if(obstacles.contains(next.toString())) continue;
			drone = next;
		}
		
		/* 출력 */
		StringBuilder sb = new StringBuilder();
		sb.append(drone.x).append(" ").append(drone.y);
		System.out.print(sb);
	}

}
