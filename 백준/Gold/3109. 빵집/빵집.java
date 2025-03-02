import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R, C;
	static char[][] board;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1};
	static int pipe;

	public static void main(String[] args) throws Exception{
		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		//초기화
		board = new char[R][C];
		visited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			board[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if(board[i][j] == 'x') visited[i][j] = true;
			}
		}
		
		
		//로직
		for (int i = 0; i < R; i++) {
			if(dfs(0, new int[] {i, 0}, false)) pipe++;
		}
		
		//출력
		System.out.println(pipe);
		
	}

	static boolean dfs(int cnt, int[] cur, boolean isConn) {
		if(isConn) return true;
		
		if(cnt == C-1) {
			isConn = true;
			return true;
		}
		for (int i = 0; i < 3; i++) {
			int nr = cur[0]+dr[i];
			int nc = cur[1]+1;
			if(check(nr, nc) && board[nr][nc] == '.' && !isConn) {
				board[nr][nc] = 'o';
				isConn = dfs(cnt+1, new int[] {nr, nc}, isConn);
			}
		}
				
		return isConn;
	}

	static boolean check(int nr, int nc) {
		return (0 <= nr && nr < R) && (0 <= nc && nc < C);
	}

}
