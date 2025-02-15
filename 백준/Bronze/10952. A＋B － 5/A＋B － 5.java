import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		StringBuilder sb = new StringBuilder();
		String line = br.readLine();
		while(!line.equals("0 0")) {
			st = new StringTokenizer(line);
			int n = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
			sb.append(n).append("\n");
			line = br.readLine();
		}

		System.out.println(sb);
	}

}
