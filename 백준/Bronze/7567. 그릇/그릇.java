import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] cup = br.readLine().toCharArray();
		
		int h = 10;
		for (int i = 1; i < cup.length; i++) {
			char preCup = cup[i-1];
			if(preCup == cup[i]) h += 5;
			else h += 10;
		}
		
		System.out.printf("%d", h);
	}

}
