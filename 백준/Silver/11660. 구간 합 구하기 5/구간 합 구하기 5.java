import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] board;
	static int[][] sumBoard;
	static Pos[] pos;
	
	static class Pos{
		int x1, x2;
		int y1, y2;
		int area;
		Pos(int x1, int y1, int x2, int y2){
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			this.area = 0;
		}
	}

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		board = new int[N+2][N+2];
		sumBoard = new int[N+2][N+2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		pos = new Pos[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			pos[i] = new Pos(x1, y1, x2, y2);
		}
		
		/* 로직 */
		//sumBoard 채우기
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				sumBoard[i][j] = board[i][j]+sumBoard[i-1][j]+sumBoard[i][j-1]-sumBoard[i-1][j-1];
			}
		}
		
		//값 구하기
		//sumBoard 활용
		//(x1, y1)~(x2,y2) 넓이 = (x2,y2)-(x1-1,y2)-(x2,y1-1)+(x1-1,y1-1)
		StringBuilder sb = new StringBuilder();
		for (Pos p : pos) {
			p.area = sumBoard[p.x2][p.y2]-sumBoard[p.x1-1][p.y2]-sumBoard[p.x2][p.y1-1]+sumBoard[p.x1-1][p.y1-1];
			sb.append(p.area).append("\n");
		}
		
		/* 출력 */
		System.out.print(sb);
	}

}
