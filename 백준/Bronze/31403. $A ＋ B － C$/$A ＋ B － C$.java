import java.util.Scanner;

public class Main {
	static String A, B, C;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		A = sc.nextLine();
		B = sc.nextLine();
		C = sc.nextLine();
		
		System.out.println(Integer.parseInt(A)+Integer.parseInt(B)-Integer.parseInt(C));
		System.out.println(Integer.parseInt(A+B)-Integer.parseInt(C));
	}

}
