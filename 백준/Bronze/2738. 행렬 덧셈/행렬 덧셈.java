import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] matrix;

	public static void main(String[] args) throws Exception{
		/* 입력 */ 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
				
		/* 초기화 + 로직 */ 
		matrix = new int[N][M];
		for (int k = 0; k < 2; k++) {
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					matrix[i][j] += Integer.parseInt(st.nextToken());
				}
			}
		}
		
		/* 출력 */ 
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(matrix[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

}
