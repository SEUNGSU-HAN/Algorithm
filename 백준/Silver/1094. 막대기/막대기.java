import java.io.*;
import java.util.*;

public class Main {
	static int X;
	
	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		X = Integer.parseInt(br.readLine().trim());
		
		/* 로직 + 출력 */
		System.out.println(findStick(64, X));
		
	}

	static int findStick(int s, int x) {
		if(s == x) return 1;
		
		if(x == 0) return 0;

		int half = s/2;
		if(x >= half)
			return findStick(half, x-half) + 1;
		else return findStick(half, x);
	}

}
