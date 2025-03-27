import java.io.*;
import java.util.*;

public class Main {
	static int A, B, C;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int sum = 1;
		for (int i = 0; i < 3; i++) {
			int n = Integer.parseInt(br.readLine().trim());
			sum *= n;
		}
		
		char[] sl = String.valueOf(sum).toCharArray();
		int[] t = new int[10];
		for (int i = 0; i < sl.length; i++) {
			t[sl[i]-'0']++;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < t.length; i++) {
			sb.append(t[i]).append("\n");
		}
		System.out.print(sb);
	}

}
