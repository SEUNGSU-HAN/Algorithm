import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int T, M, A, N=10;
	static class User{
		int x, y, sum;
		ArrayList<BC> bc;
		int[] move;
		
		public User(int x, int y, int[] move) {
			this.x = x;
			this.y = y;
			this.move = move;
			this.bc = new ArrayList<>();
			this.sum = 0;
		}
	}
	static User[] user;
	static int[] dy = {0, -1, 0, 1, 0};
	static int[] dx = {0, 0, 1, 0, -1};
	
	static class BC {
		int num;
		int x, y, c, p;

		public BC(int num, int x, int y, int c, int p) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}
	}
	static BC[] bc;
	static ArrayList<BC>[][] board;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			/* 입력 */
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			
			/* 초기화 */
			user = new User[2];
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				int[] m = new int[M+1];
				for (int j = 0; j < M; j++) {
					m[j] = Integer.parseInt(st.nextToken());
				}
				if(i == 0) user[i] = new User(0, 0, m);
				else user[i] = new User(9, 9, m);
			}
			bc = new BC[A];
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken())-1;
				int y = Integer.parseInt(st.nextToken())-1;
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				bc[i] = new BC(i+1, x, y, c, p);
			}
			board = new ArrayList[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					board[i][j] = new ArrayList<>();
				}
			}
			
			/* 로직 */
			//1. board에 BC를 배치 (04방 bfs)
			setBC();
			
			//2. 이동 시작
			int t = 0;
			while(t <= M) {
				//2-1. 유저 위치 데이터 파악 후 이동
				//유저 위치의 충전소 파악 (101 -> 1번, 3번 충전소가 있다.)후 기록
				for (int i = 0; i < 2; i++) {
					user[i].bc = new ArrayList<>();
					if(board[user[i].y][user[i].x].size() == 1) 
						user[i].bc.add(board[user[i].y][user[i].x].get(0));
					else {
						for (int j = 0; j < board[user[i].y][user[i].x].size(); j++) {
							user[i].bc.add(board[user[i].y][user[i].x].get(j));
						}
					}
				}
				
				int aincrease = 0, bincrease = 0;
				if(user[0].bc.size() == 0 || user[1].bc.size() == 0) {
					//둘 다 충전 영역 밖이거나
					//둘 중 한놈만 충전 영역 안인 경우 (충전영역 안인놈은 그 중 최대 충전값을 가져야함)
					for (int i = 0; i < user[0].bc.size(); i++) {
						aincrease = Math.max(aincrease, user[0].bc.get(i).p);
					}
					for (int i = 0; i < user[1].bc.size(); i++) {
						bincrease = Math.max(bincrease, user[1].bc.get(i).p);
					}
				}else if(user[0].bc.size() == 1 && user[1].bc.size() == 1) {
					//서로 같은 영역에 있는 경우
					if(user[0].bc.get(0).num == user[1].bc.get(0).num) {
						aincrease = user[0].bc.get(0).p/2;
						bincrease = user[1].bc.get(0).p/2;
					}
					//서로 다른 영역에 있는 경우
					else {
						aincrease = user[0].bc.get(0).p;
						bincrease = user[1].bc.get(0).p;
					}
				}else {
					int[][] tmp = new int[9][9]; //BC의 최대 개수는 8개
					for (int i = 0; i < user[0].bc.size(); i++) {
						for (int j = 0; j < user[1].bc.size(); j++) {
							if(user[0].bc.get(i).num == user[1].bc.get(j).num) {
								tmp[user[0].bc.get(i).num][user[1].bc.get(j).num] = user[0].bc.get(i).p;
							}else {
								tmp[user[0].bc.get(i).num][user[1].bc.get(j).num] = user[0].bc.get(i).p+user[1].bc.get(j).p;
							}
						}
					}
					
					//최댓값 찾기
					int maxCharge = 0;
					int ab = 0, bb = 0;
					for (int i = 0; i < 9; i++) {
						for (int j = 0; j < 9; j++) {
							if(tmp[i][j] == 0) continue;
							if(maxCharge < tmp[i][j]) {
								maxCharge = tmp[i][j];
								ab = i;
								bb = j;
							}
						}
					}
					
					aincrease = bc[ab-1].p;
					bincrease = bc[bb-1].p;
				}
				
				//유저 충전량 증가
				user[0].sum += aincrease;
				user[1].sum += bincrease;
				
				//유저 이동
				for (int i = 0; i < 2; i++) {
					user[i].x = user[i].x+dx[user[i].move[t]];
					user[i].y = user[i].y+dy[user[i].move[t]];
				}
				t++;
			}
			
			/* 출력 */
			System.out.println("#"+test_case+" "+(user[0].sum+user[1].sum));
		}
	}

	static void setBC() {
		for (int i = 0; i < A; i++) {
			int x = bc[i].x;
			int y = bc[i].y;
			visited = new boolean[N][N];	
			
			//bfs
			Queue<int[]> q = new ArrayDeque<>();
			q.offer(new int[] {x, y, 0});
			visited[y][x] = true;
			board[y][x].add(bc[i]);
			while(!q.isEmpty()) {
				int[] cur = q.poll();
				for (int j = 1; j <= 4; j++) {
					int ny = cur[1]+dy[j];
					int nx = cur[0]+dx[j];
					if(check(ny, nx) && !visited[ny][nx] && cur[2] != bc[i].c) {
						q.offer(new int[] {nx, ny, cur[2]+1});
						visited[ny][nx] = true;
						board[ny][nx].add(bc[i]);
					}
				}
			}
		}
	}


	static boolean check(int ny, int nx) {
		return (0<=ny&&ny<N) && (0<=nx&&nx<N);
	}

}
