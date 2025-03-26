import java.io.*;
import java.util.*;

public class Main {
    static int N, M, H, result = 4;
    static int[][] bridge;
    static boolean isSuccess;
    
    public static void main(String[] args) throws Exception {
        /* 입력 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
                
        /* 초기화 */
        bridge = new int[H+1][N+1];
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            bridge[r][c] = 1;
        }
        
        /* 로직 */
        // 다리는 최대 3개까지만 추가 가능
        for (int i = 0; i <= 3; i++) {
            dfs(0, 1, 1, i);
            if (isSuccess) {
                result = i;
                break;
            }
        }
        
        /* 출력 */
        System.out.print(isSuccess ? result : -1);
    }

    // 사다리 게임 결과 확인
    static boolean checkLine() {
        for (int start = 1; start <= N; start++) {
            int pos = start;
            for (int i = 1; i <= H; i++) {
                if (bridge[i][pos] == 1) pos++; // 오른쪽으로 이동
                else if (pos > 1 && bridge[i][pos-1] == 1) pos--; // 왼쪽으로 이동
            }
            if (pos != start) return false;
        }
        return true;
    }

    // 가로선 추가하는 모든 경우 탐색 (DFS)
    static void dfs(int cnt, int x, int y, int limit) {
        if (isSuccess) return;
        
        if (cnt == limit) {
            if (checkLine()) {
                isSuccess = true;
            }
            return;
        }
        
        // 모든 가능한 가로선 위치 시도
        for (int i = x; i <= H; i++) {
            for (int j = (i == x ? y : 1); j < N; j++) {
                // 현재 위치, 왼쪽, 오른쪽에 이미 선이 있으면 불가능
                if (bridge[i][j] == 1 || (j > 1 && bridge[i][j-1] == 1) || bridge[i][j+1] == 1) 
                    continue;
                
                bridge[i][j] = 1;
                // 다음 위치부터 탐색 (중요: 현재 위치 이후부터만 탐색)
                if (j < N-1) {
                    dfs(cnt+1, i, j+2, limit);
                } else {
                    dfs(cnt+1, i+1, 1, limit);
                }
                bridge[i][j] = 0; // 백트래킹
            }
        }
    }
}