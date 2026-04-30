import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int N = citations.length;
        Arrays.sort(citations);
        int s = 0, e = citations[N-1];
        int answer = e;
        
        while(s <= e) {
            int h = (s+e)/2;
            
            int upCheck = 0;
            for(int i=N-1; i>=0; i--) {
                if(citations[i] >= h) upCheck++;
                else break;
            }

            if(upCheck < h){
                e = h-1;
                continue;
            }
            
            s = h+1;
            answer = h;
        }
        
        return answer;
    }
}