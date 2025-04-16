import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] kids;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
        /* 입력 */
        N = Integer.parseInt(br.readLine().trim());

        /* 초기화 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        kids = new int[N+1];

        /* 로직 */
        int ans = 0;
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            kids[n] = kids[n-1]+1;
            if(ans < kids[n]) ans = kids[n];
        }

        /* 출력 */
		System.out.print(N-ans);
	}

}
