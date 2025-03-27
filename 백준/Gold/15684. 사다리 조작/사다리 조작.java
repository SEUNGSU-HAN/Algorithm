import java.io.*;
import java.util.*;

public class Main {
	static int N, M, H, result=4;
	static boolean[][] bridge;
	static boolean isSuccess;
	
	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
				
		/* 초기화 */
		bridge = new boolean[H+1][N+1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()); //다리가 놓일 행
			int s = Integer.parseInt(st.nextToken()); //다리가 놓이는 시작 열
			bridge[r][s] = true; //다리 표시
		}
		
		/* 로직 */
		
		// https://www.acmicpc.net/source/41138340 참고하여 추가
		/*
			사다리가 원래 자리로 돌아오려면 반드시!
			사다리 사이의 다리가 짝수 개여야함
			그래서 사다리 사이의 다리 개수가 홀수인 곳이 3군데보다 많다?
			-> 절대 불가능 바로 폐기
		 */
		if(oddBridge() > 3) {
			System.out.print(-1);
			return;
		}
		
		
		//다리는 차피 최대 3개임
		//0,1,2,3개 순으로 다리를 놓아보며 모든 경우의수 보기(조합 이용)
		for (int i = 0; i <= 3; i++) {
			//1. 다리 놓기
			combi(0, 1, 1, i);
			if(isSuccess) break;
		}
		
		/* 출력 */
		System.out.print(isSuccess ? result : -1);
	}

	static int oddBridge() {
		int odd = 0;
		for (int i = 1; i < N; i++) {
			int temp = 0;
			for (int j = 1; j <= H; j++) {
				if(bridge[j][i]) temp++;
			}
			if(temp%2 == 1) odd++;
		}
		return odd;
	}

	static boolean checkLine() {
		for (int i = 1; i <= N; i++) {
			int pos = i;
			for (int j = 1; j <= H ; j++) {
				if(bridge[j][pos]) pos++; //우측 선으로 이동
				else if(pos > 1 && bridge[j][pos-1]) pos--; //좌측 선으로 이동
			}
			if(pos != i) return false; //시작 선이 아니면 아웃
		}
		return true;
	}

	static void combi(int cnt, int rs, int cs, int R) {
		if(isSuccess) return; //이미 현재 개수의 다리로 성공했다면 더 볼 필요 없음
		if(cnt == R) {
			//2. 각 라인 검사
			if(checkLine()) {
				result = R;
				isSuccess = true;
			}
			return;
		}
		for (int i = rs; i <= H; i++) {
			for (int j = (i == rs ? cs : 1); j < N; j++) {
				//현재위치, 왼쪽, 오른쪽에 이미 선이 있으면 불가능
				if(bridge[i][j] || (j>1 && bridge[i][j-1]) || bridge[i][j+1])
					continue;
				
				bridge[i][j] = true; //다리 놓기
				
				if(j < N-1) {
					combi(cnt+1, i, j+2, R);
				}else {
					combi(cnt+1, i+1, 1, R);
				}
				bridge[i][j] = false; //다리 철거
			}
		}
	}
}
