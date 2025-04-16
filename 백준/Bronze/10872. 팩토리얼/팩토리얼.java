import java.io.*;
import java.util.*;

public class Main {
	static int N;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		
		System.out.println(N == 0 ? 1 : factorial(N));
	}

	static int factorial(int n) {
		if(n == 1) return 1;
		return factorial(n-1)*n;
	}

}
