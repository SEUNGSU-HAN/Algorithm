import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		int min = num;
		for (int i = num; i >= 1; i--) {
			char[] nl = String.valueOf(i).toCharArray();
			int sum = i;
			for (int j = 0; j < nl.length; j++)	{
				sum = sum + (nl[j]-'0');
			}
			if(num == sum) min = Math.min(min, i);
		}
		
		System.out.print((min == num) ? 0 : min);
	}

}
