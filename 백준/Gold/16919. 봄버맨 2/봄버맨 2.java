import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R, C;
	static long N;
	static int[][] board;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception{
		//--------------솔루션 코드를 작성하세요.--------------------------------
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Long.parseLong(st.nextToken());
		
		board = new int[R][C];
		for (int i = 0; i < R; i++) {
			char[] s = br.readLine().trim().toCharArray();
			for (int j = 0; j < C; j++) {
				if(s[j] == 'O') board[i][j] = 3; 
			}
		}


		//로직
		//0s -> 1s , 처음 1초 흐름
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(board[i][j] == 3) board[i][j]--;
			}
		}
		
		if(N > 1) {
			if(N%2 == 0) {
				//짝수인 경우는 모두 폭탄
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						board[i][j] = 1;
					}
				}

			}else {
				//1s -> 2s 2초 경과
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						if(board[i][j] == 0) board[i][j] = 3;
						else board[i][j]--;
					}
				}
				
				//4로 나눈 나머지 값에 따라 분기
				//3일 경우 -> 3초 일때 모습
				//1일 경우 -> 5초 일때 모습
				
				//2s -> 3s, 3초 경과 (나머지 1일 경우)
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						if(board[i][j] == 1) {
							board[i][j] = 0;
							for (int k = 0; k < 4; k++) {
								int nr = i+dr[k];
								int nc = j+dc[k];
								if(check(nr, nc)) {
									if(board[nr][nc] == 1) continue;
									board[nr][nc] = 0;
								}
							}
						}else if(board[i][j] != 0) board[i][j]--;
					}
				}
				
				if(N%4 == 1) {
					//3s -> 4s, 4초 경과(폭탄 채우기)
					for (int i = 0; i < R; i++) {
						for (int j = 0; j < C; j++) {
							if(board[i][j] == 0) board[i][j] = 3;
							else board[i][j]--;
						}
					}
					
					//4s -> 5s, 5초 경과
					for (int i = 0; i < R; i++) {
						for (int j = 0; j < C; j++) {
							if(board[i][j] == 1) {
								board[i][j] = 0;
								for (int k = 0; k < 4; k++) {
									int nr = i+dr[k];
									int nc = j+dc[k];
									if(check(nr, nc)) {
										if(board[nr][nc] == 1) continue;
										board[nr][nc] = 0;
									}
								}
							}else if(board[i][j] != 0) board[i][j]--;
						}
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(board[i][j] == 0) sb.append('.');
				else sb.append('O');
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
	}

	private static boolean check(int nr, int nc) {
		if((0 <= nr && nr < R) && (0 <= nc && nc < C)) return true;
		return false;
	}

}
