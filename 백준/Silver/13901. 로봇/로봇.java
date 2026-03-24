import java.io.*;
import java.util.*;

public class Main {
	static int R, C;
	static int[][] board;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[] direct_order; //0->3 = 상,하,좌,우 순
	static int[] result;

	public static void main(String[] args) throws Exception{
		/* 셋팅 */
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		board = new int[R][C];
		direct_order = new int[4];
		result = new int[4];
		
		int brick = Integer.parseInt(reader.readLine());
		for(int i=0; i<brick; i++) {
			st = new StringTokenizer(reader.readLine());
			int br = Integer.parseInt(st.nextToken());
			int bc = Integer.parseInt(st.nextToken());
			board[br][bc] = -1;
		}
		
		st = new StringTokenizer(reader.readLine());
		int sr = Integer.parseInt(st.nextToken());
		int sc = Integer.parseInt(st.nextToken());
		board[sr][sc] = 1;
		
		st = new StringTokenizer(reader.readLine());
		for(int i=0; i<4; i++) {
			direct_order[i] = Integer.parseInt(st.nextToken())-1;
		}
		
		/* 로직 */
		int direct = 0;
		int escape_count = 0;
		int[] robot = {sr, sc, direct};
		
		while(escape_count < 4) {
			escape_count = 0;
						
			//정해진 방향으로 전진
			for(int i=1; i<R+C; i++) {
				int nr = robot[0]+dr[direct_order[direct]];
				int nc = robot[1]+dc[direct_order[direct]];
				if(check(nr, nc) && board[nr][nc] == 0) {
					//갈 수 있으면 계속 가자.
					robot[0]=nr;
					robot[1]=nc;
					board[nr][nc] = 1;
				}else {
					break;
				}
			}

			
			//더 이상 갈 수 없으니 방향 모색 후 설정
			for(int i=0; i<4; i++) {
				int nr = robot[0]+dr[direct_order[(direct+i)%4]];
				int nc = robot[1]+dc[direct_order[(direct+i)%4]];
				if(check(nr, nc) && board[nr][nc] == 0) {
					direct = (direct+i)%4;
					break;
				}
				else {
					escape_count++;
				}
			}
		}
		
			
		/* 출력 */
		StringBuilder sb = new StringBuilder();
		sb.append(robot[0]).append(" ").append(robot[1]);
		System.out.print(sb.toString());
	}

	static boolean check(int nr, int nc) {
		return (0 <= nr && nr < R) && (0 <= nc && nc < C);
	}

}
