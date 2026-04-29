import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int N = prices.length; //시간
        int[] answer = new int[N];
        
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        int mp = 0;
        
        for(int i=0; i<N; i++) {
            int p = prices[i];
            int curTime = i+1;
            answer[i] = N-curTime;
            
            if(p >= mp) {
                dq.offer(new int[] {curTime, p});
                mp = p;
                continue;
            }
            
            while(!dq.isEmpty()) {
                int[] prev_p = dq.pollLast();
                
                if(prev_p[1] <= p) {
                    dq.offer(prev_p);
                    break;
                }
                
                answer[prev_p[0]-1] = curTime - prev_p[0];
            }
            
            dq.offer(new int[] {curTime, p});
            mp = p;
        }
        
        return answer;
    }
}