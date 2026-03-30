import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] pos;
	
	public static void main(String[] args) throws Exception{
		/* 셋팅 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
				
		StringTokenizer st;
		// 숫자가 1부터 N까지 있으므로 크기를 N+1로 만듭니다.
		// pos[몇 번째 배열][숫자] = 그 숫자가 있는 인덱스(위치)
		pos = new int[3][N + 1]; 

		for(int i = 0; i < 3; i++) {
		    st = new StringTokenizer(br.readLine());
		    for(int j = 0; j < N; j++) {
		        int num = Integer.parseInt(st.nextToken());
		        // 핵심! num이라는 숫자가 i번째 배열의 j번째 인덱스에 있다고 기록합니다.
		        pos[i][num] = j; 
		    }
		}
		
		/* 로직 */
		// 1부터 N까지의 숫자를 담을 리스트 준비
		List<Integer> list = new ArrayList<>();
		for(int i = 1; i <= N; i++) {
		    list.add(i);
		}

		// 우리가 만든 다수결 규칙에 따라 리스트를 정렬!
		Collections.sort(list, new Comparator<Integer>() {
		    @Override
		    public int compare(Integer a, Integer b) {
		        int countA = 0; // a가 b보다 앞에 있는(인덱스가 작은) 횟수
		        
		        // 3개의 배열(0, 1, 2)을 각각 확인합니다.
		        for(int i = 0; i < 3; i++) {
		            // TODO: 만약 i번째 배열에서 a의 위치가 b의 위치보다 앞서 있다면(작다면)?
		            // countA를 1 증가시킵니다.
		        	if(pos[i][a] < pos[i][b]) countA++;
		        }
		        
		        // 다수결 판별: a가 2번 이상 앞섰다면 a를 앞으로 보냄 (음수 반환)
		        // 그게 아니라면 b를 앞으로 보냄 (양수 반환)
		        if(countA >= 2) {
		            return -1; // a가 더 작다(앞에 간다)고 자바에게 알려줌
		        } else {
		            return 1;  // b가 더 작다(앞에 간다)고 자바에게 알려줌
		        }
		    }
		});
		
		/* 출력 */
		StringBuilder sb = new StringBuilder();
		for(int x : list) {
			sb.append(x).append(" ");
		}
		System.out.print(sb.toString());
	}

}
