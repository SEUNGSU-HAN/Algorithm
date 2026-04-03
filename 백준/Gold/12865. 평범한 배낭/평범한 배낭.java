import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
    	/* 셋팅 + 로직 동시 처리*/
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        // 공간 복잡도 O(K)를 위한 1차원 DP 배열
        int[] dp = new int[K + 1];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            
            // 뒤에서부터(K부터 w까지) 거꾸로 탐색하며 갱신! -> 1차원 배열로만 풀기 위해
            // j < w 인 경우는 어차피 물건이 들어갈 수 없으므로 탐색할 필요가 없음.
            for (int j = K; j >= w; j--) {
                dp[j] = Math.max(dp[j], dp[j - w] + v);
            }
        }
        
        /* 출력 */
        // 최대 허용 무게 K일 때의 최대 가치 출력
        System.out.println(dp[K]);
    }
}