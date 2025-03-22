import java.io.*;
import java.util.*;

public class Main {
	static int X;
	
	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		X = Integer.parseInt(br.readLine().trim());
        
		/* 로직 + 출력 */
		//비트마스킹 풀이
		int idx = 0;
		int count = 0;
		while((1<<idx) <= X)
            if((X & 1<<idx++) != 0) count++;
		System.out.print(count);
		
	}
}
