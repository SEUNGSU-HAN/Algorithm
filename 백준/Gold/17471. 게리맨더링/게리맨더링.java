

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static int N, min, cnt;
	static int[] people;
	static int[][] map;
	static boolean[] visited;
	static boolean[] teamVisit;
	static ArrayList<Integer>[] teams;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		people = new int[N];
		map = new int[N][N];

		visited = new boolean[N];
		cnt = 0;

		for (int i = 0; i < N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			for (int j = 0; j < n; j++) {
				int a = Integer.parseInt(st.nextToken());
				map[i][a - 1] = 1;
			}
		}
		min = Integer.MAX_VALUE;
		subset(0);
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	private static void subset(int depth) {
		if (depth == N) {
			cnt = getAns(); // 팀 => subset으로 팀 나눠짐
			if (cnt == -1) return;
			min = Math.min(cnt, min);
			return;
		}
		visited[depth] = true;
		subset(depth + 1);
		visited[depth] = false;
		subset(depth + 1);
	}

	private static int getAns() {
		List<Integer> teamA = new ArrayList<>();
		List<Integer> teamB = new ArrayList<>();

		for (int i = 0; i < visited.length; i++) {
			if (visited[i])
				teamA.add(i);
			else
				teamB.add(i);
		}
		if (teamA.size() == 0 || teamB.size() == 0) return -1;
		
		teamVisit = new boolean[N];

		bfs(teamA);
		bfs(teamB);

		for (int i = 0; i < teamVisit.length; i++) {
			if (!teamVisit[i])
				return -1;
		}

		int sumA = 0, sumB = 0;
		for (int a : teamA) {
			sumA += people[a];
		}
		for (int b : teamB) {
			sumB += people[b];
		}
		return Math.abs(sumB - sumA);
	}

	private static void bfs(List<Integer> team) {
		Queue<Integer> que = new ArrayDeque<>();
		que.offer(team.get(0));
		teamVisit[team.get(0)] = true;
		while (!que.isEmpty()) {
			int a = que.poll();
			for (int i = 0; i < team.size(); i++) {
				if (teamVisit[team.get(i)]) continue;
				if (map[a][team.get(i)] == 1) {
					que.offer(team.get(i));
					teamVisit[team.get(i)] = true;
				}
			}
		}
	}
}
