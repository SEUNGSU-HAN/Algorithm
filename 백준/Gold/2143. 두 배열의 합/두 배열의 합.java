import java.io.*;
import java.util.*;

public class Main {
	static int T, N, M;
	static int[] A, B;
	static ArrayList<Long> listA, listB;

	public static void main(String[] args) throws Exception{
		/* 셋팅 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		N =Integer.parseInt(br.readLine());
		A = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		M =Integer.parseInt(br.readLine());
		B = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		listA = new ArrayList<>();
		listB = new ArrayList<>();
		
		/* 로직 */
		//A배열의 모든 부분 배열의 합을 구하여 저장
		for(int i=0; i<N; i++) {
			long sum = 0;
			for(int j=i; j<N; j++) {
				sum += A[j];
				listA.add(sum);
			}
		}
		
		//B배열의 모든 부분 배열의 합을 구하여 저장
		for(int i=0; i<M; i++) {
			long sum = 0;
			for(int j=i; j<M; j++) {
				sum += B[j];
				listB.add(sum);
			}
		}
		
		//투포인터 사용하기 위해 두 리스트 오름차순 정렬
		Collections.sort(listA);
		Collections.sort(listB);
		
		int l = 0; //가장 작은 값부터
		int r = listB.size()-1; //가장 큰 값부터
		long count = 0;
		
		while(l < listA.size() && r >= 0) {
			long sumA = listA.get(l);
			long sumB = listB.get(r);
			long sum = sumA+sumB;
			
			if(sum == T) {
				long countA = 0;
				long countB = 0;
				
				//중복 값도 체크해서 세야함
				while(l < listA.size() && listA.get(l) == sumA) {
					l++;
					countA++;
				}
				
				while(r >= 0 && listB.get(r) == sumB) {
					r--;
					countB++;
				}
				
				count += countA*countB; //중복 개수 총 합
			}else if(sum > T) {
				//값을 줄여
				r--;
			}else {
				//값을 늘려
				l++;
			}
			
		}
		
		
		/* 출력 */
		System.out.print(count);
	}

}
