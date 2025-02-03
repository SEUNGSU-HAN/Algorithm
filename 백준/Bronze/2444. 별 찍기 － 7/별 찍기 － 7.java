import java.util.Scanner;

public class Main {
	static int n;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		
		for (int i = 0; i < 2*n-1; i++) {
			for (int j = 0; j < Math.abs(i-(n-1)); j++) {
				System.out.print(" ");
			}
			for (int k = 0; k < (2*n-1)-2*Math.abs(i-(n-1)); k++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

}
