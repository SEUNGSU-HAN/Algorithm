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
			//별+공백
			for (int j = 0; j < N; j++) {
				if(i%2 == 0) sb.append("* ");
				else sb.append(" *");
			}
			sb.append("\n");
			
		}
		
		//출력
		System.out.println(sb.toString());
	}

}
