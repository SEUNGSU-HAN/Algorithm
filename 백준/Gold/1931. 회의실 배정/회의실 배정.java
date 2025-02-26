import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	
	static class Meeting implements Comparable<Meeting>{
		int start, end; //회의 시작, 종료 시간

		public Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}

		//회의 시작, 종료 시간이 같은 경우도 있을 경우
		// => 종료시간이 빠른 순, 같다면 시작시간이 빠른 순
		// (1,2), (2,3), (3,3) : 3개 => 답
		// (1,2), (3,3), (2,3) : 2개
		@Override
		public int compareTo(Meeting o) {
			return this.end != o.end ?  this.end - o.end : this.start - o.start;
		}

	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		Meeting[] meetings = new Meeting[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			meetings[i] = new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(meetings); // 오른 차순 정렬(내가 위에서 정의한 compareTo 메소드가 호출됨
		List<Meeting> result = new ArrayList<>();
		result.add(meetings[0]); //첫 회의는 반드시 배정!
		
		for (int i = 1; i < N; i++) {
			if(result.get(result.size()-1).end <= meetings[i].start) {//배정된 마지막 회의의 종료시간보다 고려하는 회의의 시작시간이 같거나 이후라면 
				result.add(meetings[i]); //배정 가능
			}
		}
		
		System.out.println(result.size()); //총 배정된 회의 수
//		for (Meeting meeting : result) {
//			System.out.println(meeting.start + " " + meeting.end);
//		}
	}
}
