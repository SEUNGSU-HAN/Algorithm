

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static int N, curSize, curR, curC, curYummy, ansDis, targetR, targetC;
	static int[][] board;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		curSize = 2;
		curR = -1;
		curC = -1;
		targetR = Integer.MAX_VALUE;
		targetC = Integer.MAX_VALUE;
		curYummy = 0;
		ansDis = 0;
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 9) {
					curR = i;
					curC = j;
				}
			}
		}
		while (getAns()) {
			if (curYummy == curSize) {
				curSize++;
				curYummy = 0;
			}
		}
		System.out.println(ansDis);
	}

	private static boolean getAns() {
		Queue<int[]> que = new ArrayDeque<>();
		ArrayList<int[]> fishPoint = new ArrayList<>();
		visited = new boolean[N][N];
		que.offer(new int[] { curR, curC, 0 });
		visited[curR][curC] = true;
		int fixedDis = -1;
		while (!que.isEmpty()) {
			int[] posi = que.poll();
			int curRR = posi[0];
			int curCC = posi[1];
			int dis = posi[2];
			if (fixedDis != -1 && dis + 1 != fixedDis)
				continue;
			for (int d = 0; d < 4; d++) {
				int nextR = curRR + dr[d];
				int nextC = curCC + dc[d];
				if (!check(nextR, nextC) || visited[nextR][nextC] || board[nextR][nextC] > curSize)
					continue;
				if (board[nextR][nextC] == 0 || board[nextR][nextC] == curSize) {
					que.offer(new int[] { nextR, nextC, dis + 1 });
					visited[nextR][nextC] = true;
					continue;
				} else if (board[nextR][nextC] < curSize) {
					fixedDis = dis + 1;
					fishPoint.add(new int[] { nextR, nextC });
					continue;
				}
			}
		}
		if (fishPoint.size() == 0)
			return false;
		fishPoint.sort((int[] a, int[] b) -> {
			if (a[0] != b[0])
				return Integer.compare(a[0], b[0]);
			else
				return Integer.compare(a[1], b[1]);
		});
		targetR = fishPoint.get(0)[0];
		targetC = fishPoint.get(0)[1];
		board[targetR][targetC] = 0;
		curYummy++;
		board[curR][curC] = 0;
		curR = targetR;
		curC = targetC;
		board[curR][curC] = 9;
		ansDis += fixedDis;
		return true;
	}

	private static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

}
