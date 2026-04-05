import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K;
	static int[][] notebook;

	public static void main(String[] args) throws Exception{
		/* 셋팅 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		notebook = new int[N][M];
		
		/* 로직 */
		for(int k=0; k<K; k++) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			int[][] sticker = new int[R][C];
			
			for(int i=0; i<R; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<C; j++) {
					sticker[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
//			System.out.println("[현재 노트북 상태]");
//			print_notebook(notebook);
			
//			System.out.println("[이번에 붙힐 스티커]");
//			print_sticker(sticker, R, C);
			
			//노트북 모눈 칸에 들어갈 수 있는지 검증
			//각 위치별로 4개 각도를 검증
			boolean isin = false;
			for(int d=0; d<4; d++) {
				if(isin) break;
				
//				System.out.println("[스티커 붙여볼까]");
				for(int i=0; i<N; i++) {
					if(isin) break; //다음스티커 내놔
					for(int j=0; j<M; j++) {
//						System.out.println("노트북 (" + i +", " + j +")에서 검증 시작");
						//노트북의 각 격자칸을 좌상단 시작점이라 헀을 때 스티커 모눈종이가 이 노트북칸에 포함 가능 체크
						if(i+R > N || j+C > M) continue;
						
						//가능하다면 스티커 겹치는지 체크
						boolean isit = false;
						for(int r=0; r<R; r++) {
							if(isit) break;
							for(int c=0; c<C; c++) {
								if(notebook[i+r][j+c] + sticker[r][c] == 2) {
									isit = true;
//									System.out.println("노트북 (" + (i+r) +", " + (j+c) +")칸에서 겹치네");
									break;
								}
							}
						}
						
						//불가능하면 다음 격자칸으로 가서 체크
						if(isit) continue;;
						
						//가능하다면 붙이자
//						System.out.println("[붙일 수 있다 붙인다]");
						for(int r=0; r<R; r++) {
							for(int c=0; c<C; c++) {
								notebook[i+r][j+c] = notebook[i+r][j+c] + sticker[r][c];
							}
						}
						isin = true;
//						System.out.println("[다붙였다!]");
//						print_notebook(notebook);
						break;
					}
				}
				
				if(isin) break;
				
//				System.out.println("[스핀 한다!] 현재각도: " + d);
				sticker = spin(sticker, R, C, d); //배열 90도 돌리기
				R = sticker.length;
				C = sticker[0].length;
//				System.out.println("[스핀 완료] 현재각도: " + (d+1) + ", R: " + R + ", C: " + C);
//				print_sticker(sticker, R, C);
			}
		}
		
		int sum = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				sum += notebook[i][j];
			}
		}
		
		/* 출력 */
		System.out.print(sum);
	}
	
	static void print_notebook (int[][] n) {
		System.out.println("========노트북 출력==========");
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(n[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	static void print_sticker(int[][] s, int R, int C) {
		System.out.println("========스티커 출력==========");
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				System.out.print(s[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	static int[][] spin(int[][] sticker, int R, int C, int d) {
		int[][] newSticker = new int[C][R];
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				newSticker[j][R-1-i] = sticker[i][j];
//				//0 -> 90 or 180 -> 270일 때
//				if(d%2 == 0) {
//					newSticker[j][R-1-i]= sticker[i][j];
//				}
//				//90 -> 180일 때
//				else {
//					newSticker[j][R-1-i] = sticker[i][j];
//				}
			}
		}
		
		return newSticker;
	}

	static boolean isIn(int R, int C) {
		return R <= N && C <= M;
	}

}
