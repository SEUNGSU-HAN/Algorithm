import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//웰컴 키트
public class Main {
	static long N, T, P;
	static long[] size;
	static long t;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Long.parseLong(st.nextToken());
		
		size = new long[6];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 6; i++) {
			size[i] = Long.parseLong(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		T = Long.parseLong(st.nextToken());
		P = Long.parseLong(st.nextToken());
		
		for (int i = 0; i < 6; i++) {
			t = t + size[i]/T + ((size[i]%T == 0) ? 0 : 1);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(t).append("\n").append(N/P).append(" ").append(N%P);
		System.out.print(sb);
	}
}
