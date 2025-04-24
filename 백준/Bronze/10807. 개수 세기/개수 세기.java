import java.io.*;
import java.util.*;

public class Main {
    static int N, v;
    static int[] nums;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        nums = new int[N];
        for(int i = 0; i < N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        
        v = Integer.parseInt(br.readLine());
        
        int ans = 0;
        for(int n : nums){
            if(v == n) ans++;
        }

        System.out.print(ans);
    }
}