import java.io.*;
import java.util.*;

class Solution {
    int N, K;
    int min = 987654321;
    
    public int solution(int[][] cost, int[][] hint) {
        //셋팅
        N = cost.length;
        K = N-1;
        
                
        //로직
        subset(0, new int[N], 0, cost, hint);
        
        //출력
        return min;
    }
    
    public void subset(int stage, int[] ticketCount, int total, int[][] cost, int[][] hint) {
        //일찌감치 최솟값보다 가망 없으면 제껴
        if(min <= total) return;
        
        //각 스테이지 별 티켓 구매 여부 체크 끝(마지막 스테이지 제외)
        if(stage == K) {
            //스테이지를 돌면서 티켓 여부와 매수에 따라 최종 비용 산정          
            for(int i=0; i<N; i++) {
                //total이 현재의 최솟값 보다 크면 폐기
                if(min < total) return;
                
                //티켓이 있을 때만 할인률 진행
                if(ticketCount[i] != 0) {
                    int count = ticketCount[i];
                    if(count >= N-1) count = N-1;
                    total += cost[i][count];
                }else {
                    //티켓 없으면 정가로 ㄱㄱ
                    total += cost[i][0];
                }
            }
            
            //정산 끝냈으면 최솟값 여부 체크
            min = Math.min(min, total);
            return;
        }
        
        //티켓을 안 사는 경우
        subset(stage+1, ticketCount, total, cost, hint);
        //티켓을 사는 경우
        for(int i=1; i<hint[stage].length; i++) {
            int hintStageNum = hint[stage][i]-1;
            ticketCount[hintStageNum]++;
        }
        subset(stage+1, ticketCount, total+hint[stage][0], cost, hint);
        for(int i=1; i<hint[stage].length; i++) {
            int hintStageNum = hint[stage][i]-1;
            ticketCount[hintStageNum]--;
        }
    }
}