import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = {0, 0};
        Set<String> turn = new HashSet<>();
        String prev = words[0];
        for(int i=0; i<words.length; i++) {
            if(i>0 && prev.charAt(prev.length()-1) != words[i].charAt(0)) {
                int me = i%n + 1;
                answer[0] = me;
                answer[1] = i/n + 1;
                break;
            }
            if(turn.contains(words[i])) {
                int me = i%n + 1;
                answer[0] = me;
                answer[1] = i/n + 1;
                break;
            }
            turn.add(words[i]);
            prev = words[i];
        }
        
        return answer;
    }
}