import java.io.*;
import java.util.*;

public class Main {
	static int H, M;
	
	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
				
		/* 초기화 */
		StringBuilder sb = new StringBuilder();
		
		/* 로직 */
		int mdiff = M-45;
		if(mdiff >= 0) sb.append(H).append(" ").append(mdiff);
		else sb.append(H-1 < 0 ? 23 : H-1).append(" ").append(60+mdiff);
		
		/* 출력 */
		System.out.print(sb);
	}

}
