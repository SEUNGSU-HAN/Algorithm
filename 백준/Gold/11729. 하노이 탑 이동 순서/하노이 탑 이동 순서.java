import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//하노이 탑 이동 순서
public class Main {
	static int N, count;
	static ArrayList<int[]> move;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); 
		StringBuilder sb = new StringBuilder();
		
		/* 초기화 */
		move = new ArrayList<>();
		
		/* 로직 */
		hanoi(N, 1, 2, 3, sb);
		
		/* 출력 */
		String result = count+"\n";
		sb.insert(0, result);
		System.out.println(sb);
	}

	private static void hanoi(int n, int s, int tmp, int d, StringBuilder sb) {
		//옮길 원판이 없는 경우 (기저 조건)
		if(n == 0) return;
		
		hanoi(n-1, s, d, tmp, sb);
		count++;
//		System.out.println(n + ": " + s +" -> " + d);
		sb.append(s).append(" ").append(d).append("\n");
		hanoi(n-1, tmp, s, d, sb);
	}


}
