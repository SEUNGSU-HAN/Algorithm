import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < 2*n-1; i++) {
			for (int j = 0; j < -Math.abs(i-(n-1))+n; j++) {
				sb.append("*");
			}	
			for (int k = 0; k < 2*Math.abs(i-(n-1)); k++) {
				sb.append(" ");
			}
			for (int j = 0; j < -Math.abs(i-(n-1))+n; j++) {
				sb.append("*");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
