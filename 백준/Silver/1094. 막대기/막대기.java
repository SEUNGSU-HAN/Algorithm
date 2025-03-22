import java.io.*;
import java.util.*;

public class Main {
	static int X;
	
	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		X = Integer.parseInt(br.readLine().trim());
        
		/* 로직 + 출력 */
		//라이브러리 활용 풀이
		System.out.print(Integer.bitCount(X));
		
	}
}
