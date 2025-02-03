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
			//공백1
			for (int j = N-1-i; j > 0; j--) {
				sb.append(" ");
			}
			if(i != N-1) {
				//별 + 공백
				for (int j = 0; j < 2*i+1; j++) {
					if(j == 0 || j == 2*i) sb.append("*");
					else sb.append(" ");
				}
			}else {
				//all 별
				for (int j = 0; j < 2*i+1; j++) {
					sb.append("*");
				}
			}
			sb.append("\n");
			
		}
		
		//출력
		System.out.println(sb.toString());
	}

}
