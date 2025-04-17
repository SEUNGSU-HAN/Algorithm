import java.io.*;
import java.util.*;

public class Main {
	static int N, result=-1;
	static int[] people;
	static ArrayList<Integer>[] graph;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		
		/* 초기화 */
		StringTokenizer st = new StringTokenizer(br.readLine());
		people = new int[N];
		for (int i = 0; i < N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}
		graph = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			for (int j = 0; j < m; j++) {
				int e = Integer.parseInt(st.nextToken())-1;
				graph[i].add(e);
			}
		}
		
		/* 로직 */
		//1. 선거구를 분리 (subset 활용 -> N이 10이하라 가능)
		//비트마스킹으로 방문관리
		seperateDistrict(0, 0);
		
		/* 출력 */
		System.out.print(result);
	}

	static void seperateDistrict(int cnt, int flag) {
		if(cnt == N) {
			if(flag == 0 || Integer.bitCount(flag) == N) return; //모두 야당 혹은 여당인 경우
			
			//2. 각 선거구가 연결되있는지 확인
			int trueCount = Integer.bitCount(flag); //여당 수
			int falseCount = N-Integer.bitCount(flag); //야당 수
			
			//true파(여당) 확인
			boolean trueIsConn = trueCount == 1 ? true : false;
			if(!trueIsConn) {
				for (int i = 0; i < N; i++) {
					if((flag & 1<<i) > 0) {
						trueIsConn = bfs(i, flag, true);
						break;
					}
				}
			}
			//false파(야당) 확인
			boolean falseIsConn = falseCount == 1 ? true : false;
			if(!falseIsConn) {
				for (int i = 0; i < N; i++) {
					if((flag & 1<<i) == 0) {
						falseIsConn = bfs(i, flag, false);
						break;
					}
				}
			}
			
			//둘 중 하나라도 연결 안되 있으면 불가능 
			if(!trueIsConn || !falseIsConn) return;
			
			//3. 각 선거구 인구 더하기
			int trueSum=0, falseSum=0;
			for (int i = 0; i < N; i++) {
				if((flag & 1<<i) > 0) trueSum += people[i];
				else falseSum += people[i];
			}
			
			//인구차의 최소 값 구하기
			int tdDiff = Math.abs(trueSum-falseSum);
			result = result == -1 ? tdDiff : Math.min(result, tdDiff);
			return;
		}
		seperateDistrict(cnt+1, flag | 1<<cnt); 
		seperateDistrict(cnt+1, flag); 
	}

	static boolean bfs(int start, int flag, boolean tf) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(start);
		int visited = 1<<start;
		while(!q.isEmpty()) {
			int cur = q.poll();
			for (int next : graph[cur]) {
				if(tf) {
					if((flag & 1<<next) > 0 && (visited & 1<<next) == 0) {
						visited |= 1<<next;
						q.offer(next);
					}
				}else {
					if((flag & 1<<next) == 0 && (visited & 1<<next) == 0) {
						visited |= 1<<next;
						q.offer(next);
					}
				}
			}
		}
		
		if(tf) {
			return visited == flag ? true : false;
		}else {
			return Integer.bitCount(visited) == N-Integer.bitCount(flag) ? true : false;
		}
	}

}
