import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static int N;
	static int[][] inningResult;
	static int[] sunser;
	static boolean[] visited;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		inningResult = new int[N][9];
		sunser = new int[9];
		sunser[3] = 0;

		visited = new boolean[9];
		visited[0] = true;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				inningResult[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		ans = -1;
		getSunser(0);
		System.out.println(ans);
	}

	private static void getSunser(int depth) {
		if (depth == 3) {
			getSunser(depth + 1);
			return;
		}

		if (depth == 9) {
			ans = Math.max(ans, getPoint());
			return;
		}
		for (int i = 0; i < 9; i++) {
			if (visited[i])continue;
			visited[i] = true;
			sunser[depth] = i;
			getSunser(depth + 1);
			sunser[depth] = 0;
			visited[i] = false;
		}
	}

	private static int getPoint() {
		int sunserIdx = 0;
		int totPoint = 0;
		for (int i = 0; i < N; i++) {
			int outCnt = 0;
			int point = 0;
			while (outCnt < 3) {
				int hit = inningResult[i][sunser[(sunserIdx++) % 9]];
				switch (hit) {
				case 1:
					point++;
					point <<= 1;
					break;
				case 2:
					point++;
					point <<= 2;
					break;
				case 3:
					point++;
					point <<= 3;
					break;
				case 4:
					point++;
					point <<= 4;
					break;
				case 0:
					outCnt++;
					continue;
				}
				for (int j = 128; j >= 16; j >>= 1) {
					if((point & j) == 0) continue;
					totPoint += point / j;
					point %= j;
				}
			}
		}
		return totPoint;
	}

}