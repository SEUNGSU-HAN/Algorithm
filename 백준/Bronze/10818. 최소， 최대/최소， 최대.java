import java.io.*;
import java.util.*;

public class Main{
    static int N;
    static int[] nums;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        nums = new int[N];
        for(int i = 0; i < N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);
        
        System.out.print(nums[0] + " " + nums[N-1]);
    }
}