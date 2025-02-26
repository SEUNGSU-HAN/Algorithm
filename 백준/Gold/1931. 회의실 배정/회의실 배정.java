import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static ArrayList<int[]> meeting;
	static int count;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		/* 초기화 */
		meeting = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			meeting.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
		}
		
		/* 로직 */
		//끝나는 시간 기준으로 정렬(오름 차순)
		//회의 끝나는 시간이 같은게 여러개 있다면 시작 시간 기준으로 오름 차순
		//즉, base는 끝나는 시간 기준 오름 차순 정렬 but, 끝나는 시간이 같은게 여러개라면 걔네중에선 시작시간 기준 오름 차순으로 정렬
		Collections.sort(meeting, (o1, o2) -> {
			return o1[1] == o2[1] ? Integer.compare(o1[0], o2[0]) : Integer.compare(o1[1], o2[1]);
			}
		);
		int endTime = meeting.get(0)[1];
		count++;
		for (int i = 1; i < N; i++) {
			if(meeting.get(i)[0] >= endTime) {
				count++;
				endTime = meeting.get(i)[1];
				
			}
		}
		
		/* 출력 */
		System.out.print(count);
	}

}
