import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N;
	
	public static void main(String[] args) throws Exception{
		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		//로직
		for (int i = 0; i < N; i++) {
			//공백1
			for (int j = N-1-i; j > 0; j--) {
				sb.append(" ");
			}
			sb.append("*");
			//별, 공백2
			if(i != 0) {
				for (int k = 0; k < 2*i-1; k++) {
					sb.append(" ");
				}
				sb.append("*");
			}
			sb.append("\n");
		}
		
		//출력
		System.out.println(sb.toString());
	}

}
