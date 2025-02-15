import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, X;
	static ArrayList<Integer> result;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		result = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			if(n < X) result.add(n);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int n : result) {
			sb.append(n).append(" ");
		}
		System.out.println(sb);
	}

}
