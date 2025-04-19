import java.io.*;
import java.util.*;

public class Main {
	static int T, N, result;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < T; i++) {
			N = Integer.parseInt(br.readLine().trim());
			
			result = 0;
			presentation(N);
			sb.append(result).append("\n");
		}
		System.out.print(sb);
	}
	static void presentation(int cnt) {
		if(cnt == 0) result++;
		if(cnt-1 >= 0) presentation(cnt-1);
		if(cnt-2 >= 0) presentation(cnt-2);
		if(cnt-3 >= 0) presentation(cnt-3);
	}

}
