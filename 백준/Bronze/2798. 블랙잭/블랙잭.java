import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//순서가 의미가 없다. -> 조합 사용 -> nCr

public class Main {
	static int N;
	static int M;
	static int R;
	static int[] cards;
	static int[] choice;
	static int diff;
	static int sum;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = 3;
		diff = Integer.MAX_VALUE;
		sum = 0;
		choice = new int[R];
		cards = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
		
		combi(0, 0, 0);
		
		System.out.println(sum);
	}
	
	public static void combi(int cnt, int start, int tot) {
		if(cnt == R) {
			if(tot > M) return;
			int d = Math.abs(M-tot);
			if(d <= diff) {
				diff = d;
				sum = tot;
			}
			return;
		}
		for (int i = start; i < N; i++) {
			choice[cnt] = cards[i];
			combi(cnt+1, i+1, tot+cards[i]);
			choice[cnt] = 0;
		}
	}

}
