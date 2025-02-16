import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static String P;
	static int N;
	static boolean[] visited;
	static boolean isprint;
	static ArrayList<Integer> list;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		P = br.readLine();
		
		/* 초기화 */
		//순열 최댓 값 (max = 50)
		if(P.length() < 10) N = P.length();
		else {
			N = 9+(P.length()-9)/2;
		}
		visited = new boolean[N+1];
		list = new ArrayList<>();
		
		/* 로직 */
		findPerm(0);
		
		/* 출력 */
	}


	private static void findPerm(int cnt) {
		if(isprint) return;
		if(cnt == P.length()) {
			for (int i = 0; i < list.size(); i++) {
				System.out.print(list.get(i) + " ");
			}
			isprint = true;
			return;
		}
		String str = "";
		//1.단 1개
		str = P.substring(cnt, cnt+1);
		int n = Integer.parseInt(str);
		if(!visited[n]) {
			visited[n] = true;
			list.add(n);
			findPerm(cnt+1);
			list.remove(list.size()-1);
			visited[n] = false;
		}
		
		//2. 두 자리 자를 경우
		if(cnt < P.length()-1) {
			str = P.substring(cnt, cnt+2);
			n = Integer.parseInt(str);
			if(n > N || visited[n]) return;
			visited[n] = true;
			list.add(n);
			findPerm(cnt+2);
			list.remove(list.size()-1);
			visited[n] = false;
		}
	}



}
