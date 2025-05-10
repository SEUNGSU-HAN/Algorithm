import java.io.*;
import java.math.BigInteger;

public class Main {
	static BigInteger A, B;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		A = new BigInteger(br.readLine());
		B = new BigInteger(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		sb.append(A.add(B)).append("\n");
		sb.append(A.subtract(B)).append("\n");
		sb.append(A.multiply(B)).append("\n");
		
		System.out.print(sb);
	}

}
