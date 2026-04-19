import java.util.*;

class Solution {      
    public int solution(String[][] clothes) {
        int result = 1;
        
        Map<String, Integer> clothesCount = new HashMap<>();
        
        for(int i=0; i<clothes.length; i++) 
            clothesCount.put(clothes[i][1], clothesCount.getOrDefault(clothes[i][1], 0) + 1);
        
        for(int n : clothesCount.values()) 
            result *= (n+1);
        
        return result-1;
    }
}