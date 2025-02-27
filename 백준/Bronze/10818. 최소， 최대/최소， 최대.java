import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int min = 9_999_999, max = -9_999_999;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            int n = Integer.parseInt(st.nextToken());
            min = Math.min(min, n);
            max = Math.max(max, n);
        }
        
        System.out.print(min + " " + max);
    }
}