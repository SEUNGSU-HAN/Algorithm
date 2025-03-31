import java.io.*;
import java.util.*;

public class Main {
	static int N, M, blindSpot;
	static int[][] board;
	static int[][][] dtv1 = {
			{},
			{{-1}, {0}},
			{{0}, {1}},
			{{1}, {0}},
			{{0}, {-1}}
	};
	static int[][][] dtv2 = {
			{},
			{{-1, 1}, {0, 0}},
			{{0, 0}, {1, -1}}
	};
	static int[][][] dtv3 = {
			{},
			{{-1, 0}, {0, 1}},
			{{0, 1}, {1, 0}},
			{{1, 0}, {0, -1}},
			{{0, -1}, {-1, 0}}
	};
	static int[][][] dtv4 = {
			{},
			{{-1, 0, 0}, {0, -1, 1}},
			{{0, -1, 1}, {1, 0, 0}},
			{{1, 0, 0}, {0, -1, 1}},
			{{0, -1, 1}, {-1, 0, 0}},
	};
	static int[][][] dtv5 = {
			{},
			{{-1, 0, 1, 0}, {0, 1, 0, -1}}
	};
	static class CCTV {
		int n, r, c, uid;

		public CCTV(int n, int r, int c, int uid) {
			this.n = n;
			this.r = r;
			this.c = c;
			this.uid = uid;
		}
	}
	static ArrayList<CCTV> cctv;
	static ArrayList<int[][][]> cctvDirection;
	
	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		blindSpot = N*M;
		
		/* 초기화 */
		cctv = new ArrayList<>();
		board = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] != 0) blindSpot--;
				if(board[i][j] >= 1 && board[i][j] < 6)
					cctv.add(new CCTV(board[i][j], i, j, (i+1)*10+(j+1)));
			}
		}
		cctvDirection = new ArrayList<>();
		cctvDirection.add(new int[][][] {});
		cctvDirection.add(dtv1);
		cctvDirection.add(dtv2);
		cctvDirection.add(dtv3);
		cctvDirection.add(dtv4);
		cctvDirection.add(dtv5);
		
		/* 로직 */
		spy(0, 0, blindSpot);
		
		/* 출력 */
		System.out.print(blindSpot);
	}

	static void spy(int cnt, int watch, int bspot) {
		if(cnt == cctv.size()) {
			blindSpot = Math.min(blindSpot, bspot-watch);
			return;
		}
		CCTV cur = cctv.get(cnt);
		int[][][] dtv = cctvDirection.get(cur.n);
		for (int z = 1; z < dtv.length; z++) { //각도 별 확인
			int count = 0;
			for (int i = 0; i < dtv[z][0].length; i++) { //방향별 확인
				for (int j = 1; j < N*M; j++) {
					int nr = cur.r+dtv[z][0][i]*j;
					int nc = cur.c+dtv[z][1][i]*j;
					if(!check(nr, nc) || board[nr][nc] == 6) break;
					if(board[nr][nc] == 0) {
						count++;
						board[nr][nc] = cur.uid;
					}
				}
			}
			spy(cnt+1, watch+count, bspot); //다음 감시카메라
			for (int i = 0; i < dtv[z][0].length; i++) { //방향별 확인
				//감시 영역 복구
				for (int j = 1; j < N*M; j++) {
					int nr = cur.r+dtv[z][0][i]*j;
					int nc = cur.c+dtv[z][1][i]*j;
					if(!check(nr, nc) || board[nr][nc] == 6) break;
					if(board[nr][nc] == cur.uid) {
						board[nr][nc] = 0;
					}
				}
			}
		}
	}

	static boolean check(int nr, int nc) {
		return (0 <= nr && nr < N) && (0 <= nc && nc < M);
	}	
}
