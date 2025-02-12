import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] people;
	static boolean[] visited;
	static ArrayList<Integer>[] graph;
	static boolean[] tfVisited;
	static int minDiff = Integer.MAX_VALUE;
	static int sum;
	
	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		people = new int[N+1];
		visited = new boolean[N+1];
		tfVisited = new boolean[N+1];
		graph = new ArrayList[N+1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int temp = Integer.parseInt(st.nextToken());
			for (int j = 0; j < temp; j++) {
				int n = Integer.parseInt(st.nextToken());
				graph[i].add(n);
				graph[n].add(i);
			}
		}
		for (int i = 1; i <= N; i++) {
			sum += people[i];
		}
		
		/* 로직 */
		subSet(1, 0);
		
		/* 출력 */
		if(minDiff == Integer.MAX_VALUE) minDiff = -1;
		System.out.println(minDiff);
	}

	private static void subSet(int cnt, int tot) {
		if(cnt == N+1) {
			ArrayList<Integer> tp = new ArrayList<>(); //true 구역
			ArrayList<Integer> fp = new ArrayList<>();//false 구역
			for (int i = 1; i <= N; i++) {
				if(visited[i]) tp.add(i);
				else fp.add(i);
			}
			
			//불가능한 방법1 - 한쪽 선거구가 비었음
			if(tp.size() == 0 || fp.size() == 0) return;
			int con = bfs(tp, fp);
			minDiff = Math.min(minDiff, con);
			return;
		}
		visited[cnt] = true;
		subSet(cnt+1, tot+people[cnt]);
		visited[cnt] = false;
		subSet(cnt+1, tot);
	}

	private static int bfs(ArrayList<Integer> tp, ArrayList<Integer> fp) {
		Queue<Integer> q = new LinkedList<>();

		int temp = tp.get(0);
		int tConn = people[temp];
		q.offer(temp);
		tfVisited[temp] = true;
		//true 구역 집계
		while(!q.isEmpty()) {
			temp = q.poll();
			for (int i = 0; i < graph[temp].size(); i++) {
				int next = graph[temp].get(i);
				if(check(tp, next)) {
					q.offer(next);
					tConn += people[next];
					tfVisited[next] = true;
				}
			}
		}
		
		//false 구역 집계
		temp = fp.get(0);
		int fConn = people[temp];
		q.offer(temp);
		tfVisited[temp] = true;
		while(!q.isEmpty()) {
			temp = q.poll();
			for (int i = 0; i < graph[temp].size(); i++) {
				int next = graph[temp].get(i);
				if(check(fp, next)) {
					q.offer(next);
					fConn += people[next];
					tfVisited[next] = true;
				}
			}
		}
		tfVisited = new boolean[N+1];
		if(sum != (tConn+fConn)) return Integer.MAX_VALUE;
		
		return Math.abs(tConn-fConn);
	}

	private static boolean check(ArrayList<Integer> p, int next) {
		if(p.contains(next) && !tfVisited[next]) return true;
		return false;
	}



}
