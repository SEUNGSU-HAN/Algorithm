import java.util.Scanner;

public class Main {
	static int N, K;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		
		System.out.print(factorial(N)/(factorial(N-K)*factorial(K)));
	}

	private static int factorial(int n) {
		if(n <= 1) return 1;
		return n*factorial(n-1);
	}

}
