import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
	static BigInteger[] nums;
	static BigInteger sum;
	static BigInteger[] result;	

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		nums = new BigInteger[5];
		for (int i = 0; i < 5; i++) {
			nums[i] = new BigInteger(st.nextToken());
		}
		
		sum = new BigInteger("0");
		for (int i = 0; i < 5; i++) {
			sum = sum.add(nums[i].pow(2));
		}
		result = sum.divideAndRemainder(new BigInteger("10"));
		
		System.out.print(result[1]);
		
	}
}
