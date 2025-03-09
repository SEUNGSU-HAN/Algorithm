import java.io.*;

public class Main {
	static int max, idx;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 1; i <= 9; i++) {
			int n = Integer.parseInt(br.readLine().trim());
			if(max < n) {
				max = n;
				idx = i;
			}
		}
		
		System.out.print(max + "\n" + idx);
	}

}
