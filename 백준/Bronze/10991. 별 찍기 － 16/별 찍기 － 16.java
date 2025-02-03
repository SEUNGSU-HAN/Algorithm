import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N;
	
	public static void main(String[] args) throws Exception{
		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		//로직
		for (int i = 0; i < N; i++) {
			//공백
			for (int k = N-1-i; k > 0; k--) {
				sb.append(" ");
			}
			//별
			for (int j = 0; j < i+1; j++) {
				sb.append("* ");
			}
			sb.append("\n");
		}
		
		//출력
		System.out.println(sb.toString());
	}

}
