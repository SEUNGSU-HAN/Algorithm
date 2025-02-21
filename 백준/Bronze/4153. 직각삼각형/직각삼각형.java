import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int a, b, c;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			b = (int)Math.pow(Integer.parseInt(st.nextToken()), 2);
			a = (int)Math.pow(Integer.parseInt(st.nextToken()), 2);
			c = (int)Math.pow(Integer.parseInt(st.nextToken()), 2);
			if(a == 0 && b == 0 && c == 0) break;
			if((a == b+c) || (b == a+c) || (c == a+b)) sb.append("right");
			else sb.append("wrong");
			sb.append("\n");
		}
		
		System.out.print(sb);
	}

}
