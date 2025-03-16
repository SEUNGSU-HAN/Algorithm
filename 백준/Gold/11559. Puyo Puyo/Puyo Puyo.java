import java.io.*;
import java.util.*;

public class Main {
	static int N=12, M=6, boom, result;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static char[][] board;
	static char[][] temp;
	static Queue<int[]> bp; //터트릴 뿌요 모음
	
	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		/* 초기화 */
		board = new char[N][M];
		for (int i = 0; i < N; i++) {
			board[i] = br.readLine().toCharArray();
		}
		temp = new char[N][M];
		bp = new ArrayDeque<>();
		
		/* 로직 */
		// 'D'는 삭제 예정 뿌요 표시
		// 'C'는 이미 체크 했는데 답 없는 애들(어차피 안터질 애들)중복 체크 방지용
		do {
			boom = 0;
			deepCopy(board, temp);
			for (int i = N-1; i >= 0; i--) {
				for (int j = M-1; j >= 0; j--) {
					if(temp[i][j] != '.' && temp[i][j] != 'D' && temp[i][j] != 'C') {
						//check puyo (터티를 뿌요 체크 및 어차피 안터질 뿌요 체크)
						boom += checkPuyo(new int[] {i, j}, new boolean[N][M], temp[i][j]);
					}
				}
			}
			if(boom != 0) {
				//뿌요 터트리기
				while(!bp.isEmpty()) {
					int[] cur = bp.poll();
					board[cur[0]][cur[1]] = '.';
				}
				result++;
				//맵 수정
				mapBuild();
			}
		}while(boom != 0);
		

		/* 출력 */
		System.out.print(result);
	}

	static void mapBuild() {
		int c = 0;
		while(c < M) {
			int s = N-1;
			int e = N-2;
			while(s >= 0 && e >= 0) {
				//빈공간 위치 찾기
				while(board[s][c] != '.' && s > 0) s--;
				if(s <= 0) break;
				e = s;
				//같은 줄 뿌요 찾기
				while(board[e][c] == '.' && e > 0) e--;
				//뿌요 위치 내리기(swap)
				swap(s, e, c);
				//한칸씩 올라갓
				s--; e--;
			}
			c++;
		}
	}

	static void swap(int s, int e, int c) {
		char t = board[s][c];
		board[s][c] = board[e][c];
		board[e][c] = t;
	}

	private static void print(char[][] b) {
		System.out.println("==================");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(b[i][j] + " ");
			}
			System.out.println();
		}
	}

	static int checkPuyo(int[] start, boolean[][] visited, char py) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(start);
		visited[start[0]][start[1]] = true;
		int count = 0;
		ArrayList<int[]> puyo = new ArrayList<>();
		puyo.add(start);
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			count++;
			for (int i = 0; i < 4; i++) {
				int nr = cur[0]+dr[i];
				int nc = cur[1]+dc[i];
				if(check(nr, nc) && !visited[nr][nc] && temp[nr][nc] == py) {
					int[] next = new int[] {nr, nc};
					q.offer(next);
					puyo.add(next);
					visited[nr][nc] = true;
				}
			}
		}
		
		if(count < 4) {
			//한사이클 돌기 전까지 답없는 뿌요들 체크
			for (int[] p : puyo) {
				temp[p[0]][p[1]] = 'C';
			}
			return 0;
		}else {
			//한사이클 돌 때 터질 것이 예약된 뿌요 체크
			for (int[] p : puyo) {
				bp.offer(p);
				temp[p[0]][p[1]] = '.';
			}
		}
		return count;
	}

	static boolean check(int nr, int nc) {
		return (0 <= nr && nr < N) && (0 <= nc && nc < M);
	}

	static void deepCopy(char[][] src, char[][] dest) {
		for (int i = 0; i < N; i++) {
			System.arraycopy(src[i], 0, dest[i], 0, src[i].length);
		}
	}

}
