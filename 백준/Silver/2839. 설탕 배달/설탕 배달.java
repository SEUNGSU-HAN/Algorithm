import java.util.*;

public class Main {
	static int N, result = -1;

	public static void main(String[] args) {
		/* 입력 */
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		/* 로직 */
		for (int i = 0; i < 1701; i++) {
			for (int j = 0; j < 1701; j++) {
				int n = 5*i+3*j;
				if(n == N) {
					if(result == -1) result = i+j;
					else result = Math.min(result, i+j);
				}
			}
		}
		
		/* 출력 */
		System.out.print(result);
	}


}
