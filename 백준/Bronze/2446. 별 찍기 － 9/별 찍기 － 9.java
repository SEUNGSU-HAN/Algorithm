import java.util.Scanner;

public class Main {
	static int N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < 2*N-1; i++) {
			for (int k = 0; k < -Math.abs(i-(N-1))+(N-1); k++) {
				sb.append(" ");
			}
			for (int j = 0; j < 2*Math.abs(i-(N-1))+1; j++) {
				sb.append("*");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
